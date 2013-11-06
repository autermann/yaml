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

import java.util.LinkedHashSet;
import java.util.Set;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlSetNode extends AbstractYamlSequenceNode<YamlSetNode> {
    private final LinkedHashSet<YamlNode> nodes;

    public YamlSetNode(YamlNodeFactory factory) {
        super(factory);
        this.nodes =  Sets.newLinkedHashSet();
    }

    @Override
    public boolean isSet() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.SET;
    }

    @Override
    public YamlSetNode asSet() {
        return this;
    }

    @Override
    public Set<YamlNode> value() {
        return this.nodes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlSetNode copy = getNodeFactory().setNode();
        for (YamlNode node : this) {
            copy.add(node.copy());
        }
        return (T) copy;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public YamlNode path(int i) {
        if (i < 0 || i >= size()) {
            return YamlMissingNode.instance();
        }
        return Iterables.get(value(), i);
    }

    @Override
    public YamlNode get(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return Iterables.get(value(), i);
    }
}
