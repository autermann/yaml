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
package com.github.autermann.snakeyaml.api.nodes;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.ReturningYamlNodeVisitor;
import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.YamlNodeVisitor;
import com.github.autermann.snakeyaml.api.YamlNodes;
import com.github.autermann.snakeyaml.api.util.LinkedListSupplier;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlPairsNode extends AbstractYamlMappingNode<YamlPairsNode> {
    private final ListMultimap<YamlNode, YamlNode> multiMap;
    private final List<Entry<YamlNode, YamlNode>> value;

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
        value().add(Maps.immutableEntry(key, value));
        this.asMap().put(key, value);
        return this;
    }

    @Override
    public boolean has(YamlNode key) {
        return this.asMap().containsKey(key) && !get(key).isEmpty();
    }

    @Override
    public boolean hasNotNull(YamlNode key) {
        return has(key) && Iterables.any(get(key), YamlNodes.notNullOrMissing());
    }

    protected List<YamlNode> get(YamlNode key) {
        return this.asMap().get(key);
    }

    protected List<Entry<YamlNode, YamlNode>> value() {
        return this.value;
    }

    @Override
    public int size() {
        return value().size();
    }

    @Override
    public boolean isEmpty() {
        return value().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof YamlPairsNode) {
            YamlPairsNode that = (YamlPairsNode) o;
            return this.value().equals(that.value());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value().hashCode();
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
        return Collections.unmodifiableCollection(value());
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    protected ListMultimap<YamlNode, YamlNode> asMap() {
        return multiMap;
    }
}
