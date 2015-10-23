/*
 * Copyright 2013-2015 Christian Autermann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.autermann.yaml.nodes;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.github.autermann.yaml.YamlNodes;
import com.google.common.collect.Iterators;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;

/**
 * A {@link YamlNode} for {@code !!pairs} mappings.
 *
 * @author Christian Autermann
 */
public class YamlPairsNode extends YamlMappingNode<YamlPairsNode> {
    /**
     * A {@link ListMultimap} to enable fast access to all values of a key.
     */
    private final Map<YamlNode, List<YamlNode>> multiMap;
    /**
     * A {@link List} of all entries to maintain insertion order.
     */
    private final List<Entry<YamlNode, YamlNode>> value;

    /**
     * Creates a new {@link YamlPairsNode}.
     *
     * @param factory the factory to create children with
     */
    public YamlPairsNode(YamlNodeFactory factory) {
        super(factory);
        this.multiMap = new HashMap<>();
        this.value = new LinkedList<>();
    }

    @Override
    public boolean isPairs() {
        return true;
    }

    @Override
    public YamlPairsNode asPairs() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.PAIRS;
    }

    @Override
    public YamlPairsNode put(YamlNode key, YamlNode value) {
        // small protection adding this to a collection added to this still works
        if (key == this || value == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        this.value.add(Maps.immutableEntry(key, value));
        this.multiMap.computeIfAbsent(key, k -> new LinkedList<>()).add(value);
        return this;
    }

    @Override
    public int size() {
        return this.value.size();
    }

    @Override
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof YamlPairsNode) {
            YamlPairsNode that = (YamlPairsNode) o;
            return this.value.equals(that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public YamlPairsNode copy() {
        YamlPairsNode copy = getNodeFactory().pairsNode();
        for (Entry<YamlNode, YamlNode> e : entries()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return copy;
    }

    @Override
    public Collection<Entry<YamlNode, YamlNode>> entries() {
        return Collections.unmodifiableCollection(this.value);
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean has(YamlNode key) {
        return this.multiMap.containsKey(key) &&
               !this.multiMap.get(key).isEmpty();
    }

    @Override
    public boolean hasNotNull(YamlNode key) {
        return this.multiMap.getOrDefault(key, Collections.emptyList())
                .stream().filter(Objects::nonNull)
                .anyMatch(n -> !n.isNull() && n.exists());
    }

    @Override
    public YamlNode path(YamlNode key) {
        List<YamlNode> nodes = this.multiMap.get(YamlNodes.nullToNode(key));
        if (nodes == null) {
            return YamlMissingNode.instance();
        }
        return getNodeFactory().sequenceNode().addAll(nodes);
    }

    @Override
    public Iterator<YamlNode> iterator() {
        return Iterators.unmodifiableIterator(this.multiMap.keySet().iterator());
    }

    @Override
    public Spliterator<YamlNode> spliterator() {
        return Spliterators.spliterator(iterator(), size(), Spliterator.SIZED |
                                                            Spliterator.SUBSIZED |
                                                            Spliterator.ORDERED |
                                                            Spliterator.NONNULL);
    }
}
