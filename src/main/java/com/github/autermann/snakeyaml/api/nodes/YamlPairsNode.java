/*
 * Copyright (C) 2013 Christian Autermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.autermann.snakeyaml.api.nodes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.YamlNodes;
import com.github.autermann.snakeyaml.api.util.LinkedListSupplier;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlPairsNode extends AbstractYamlMappingNode<YamlPairsNode> {
    private final ListMultimap<YamlNode, YamlNode> value;

    public YamlPairsNode(YamlNodeFactory factory) {
        super(factory);
        LinkedHashMap<YamlNode, Collection<YamlNode>> map = Maps
                .newLinkedHashMap();
        Supplier<List<YamlNode>> supplier = LinkedListSupplier.instance();
        this.value = Multimaps.newListMultimap(map, supplier);
    }

    @Override
    public boolean isPairs() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.PAIRS;
    }

    @Override
    public YamlPairsNode put(YamlNode key, YamlNode value) {
        // small protected adding this to a collection added to this still works
        if (key == this || value == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        value().put(key, value);
        return this;
    }

    @Override
    public boolean has(YamlNode key) {
        return value().containsKey(key) && !get(key).isEmpty();
    }

    @Override
    public boolean hasNotNull(YamlNode key) {
        return has(key) && Iterables.any(get(key), YamlNodes.notNullOrMissing());
    }

    protected List<YamlNode> get(YamlNode key) {
        return value().get(key);
    }

    protected ListMultimap<YamlNode, YamlNode> value() {
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
            return value().equals(((YamlPairsNode) o).value());
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
        return value().entries();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
