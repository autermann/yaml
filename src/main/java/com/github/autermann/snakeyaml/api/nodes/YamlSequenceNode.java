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

import java.util.List;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.google.common.collect.Lists;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlSequenceNode extends AbstractYamlSequenceNode<YamlSequenceNode> {
    private final List<YamlNode> nodes;

    public YamlSequenceNode(YamlNodeFactory factory) {
        super(factory);
        this.nodes = Lists.newArrayList();
    }

    @Override
    public boolean isSequence() {
        return true;
    }

    @Override
    public YamlSequenceNode asSequence() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.SEQ;
    }

    @Override
    public List<YamlNode> value() {
        return nodes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlSequenceNode copy = getNodeFactory().sequenceNode();
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
    public YamlNode path(int index) {
        if (index < 0 || index >= size()) {
            return YamlMissingNode.instance();
        }
        return value().get(index);
    }

    @Override
    public YamlNode get(int index) {
        return value().get(index);
    }

}
