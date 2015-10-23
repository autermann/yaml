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
import java.util.HashMap;
import java.util.Iterator;
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

/**
 * A {@link YamlNode} for {@code !!map} mappings.
 *
 * @author Christian Autermann
 */
public class YamlMapNode extends YamlMappingNode<YamlMapNode> {
    /**
     * The children of this mapping.
     */
    private final Map<YamlNode, YamlNode> value;

    /**
     * Creates a new {@link YamlMapNode} with the specified backing map.
     *
     * @param factory the factory to create children with
     * @param nodes   the backing map
     */
    protected YamlMapNode(YamlNodeFactory factory,
                              Map<YamlNode, YamlNode> nodes) {
        super(factory);
        this.value = Objects.requireNonNull(nodes);
    }

    /**
     * Creates a new {@link YamlMapNode}.
     *
     * @param factory the factory to create children with
     */
    public YamlMapNode(YamlNodeFactory factory) {
        this(factory, new HashMap<>());
    }

    @Override
    public YamlMapNode put(YamlNode key, YamlNode value) {
        // small protected adding this to a collection added to this still works
        if (key == this || value == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        this.value.put(YamlNodes.nullToNode(key), YamlNodes.nullToNode(value));
        return this;
    }

    @Override
    public boolean isMap() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.MAP;
    }

    @Override
    public YamlMapNode asMap() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlMapNode &&
               this.value.equals(((YamlMapNode) o).value);
    }

    @Override
    public YamlMapNode copy() {
        YamlMapNode copy = getNodeFactory().mapNode();
        for (Entry<YamlNode, YamlNode> e : entries()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return copy;
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
    public Collection<Entry<YamlNode, YamlNode>> entries() {
        return this.value.entrySet();
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
    public YamlNode path(YamlNode key) {
        return YamlNodes
                .nullToMissing(this.value.get(YamlNodes.nullToNode(key)));
    }

    @Override
    public Iterator<YamlNode> iterator() {
        return Iterators.unmodifiableIterator(this.value.keySet().iterator());
    }

    @Override
    public Spliterator<YamlNode> spliterator() {
        return Spliterators.spliterator(iterator(), size(), Spliterator.SIZED |
                                                            Spliterator.SUBSIZED |
                                                            Spliterator.NONNULL);
    }
}
