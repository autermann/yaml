/*
 * Copyright 2013 Christian Autermann
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.github.autermann.yaml.YamlNodes;
import com.github.autermann.yaml.util.LinkedListSupplier;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

/**
 * A {@link YamlNode} for {@code !!pairs} mappings.
 *
 * @author Christian Autermann
 */
public class YamlPairsNode extends AbstractYamlMappingNode<YamlPairsNode> {
    /**
     * A {@link ListMultimap} to enable fast access to all values of a key.
     */
    private final ListMultimap<YamlNode, YamlNode> multiMap;
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
        LinkedHashMap<YamlNode, Collection<YamlNode>> map = Maps
                .newLinkedHashMap();
        Supplier<List<YamlNode>> supplier = LinkedListSupplier.instance();
        this.multiMap = Multimaps.newListMultimap(map, supplier);
        this.value = Lists.newLinkedList();
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
        this.multiMap.put(key, value);
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
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlPairsNode copy = getNodeFactory().pairsNode();
        for (Entry<YamlNode, YamlNode> e : entries()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return (T) copy;
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
        return has(key) && Iterables.any(this.multiMap.get(key), YamlNodes.notNullOrMissing());
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
}
