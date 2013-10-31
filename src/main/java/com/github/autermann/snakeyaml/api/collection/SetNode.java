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
package com.github.autermann.snakeyaml.api.collection;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.google.common.collect.Sets;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class SetNode extends AbstractSequenceNode<SetNode> {
    private final Set<Node> nodes;

    public SetNode(NodeFactory factory, Set<Node> nodes) {
        super(factory);
        this.nodes = checkNotNull(nodes);
    }

    public SetNode(NodeFactory factory) {
        this(factory, Sets.<Node>newLinkedHashSet());
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
    public SetNode asSet() {
        return this;
    }

    @Override
    protected Set<Node> getNodes() {
        return this.nodes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T copy() {
        SetNode copy = getNodeFactory().setNode();
        for (Node node : this) {
            copy.add(node.copy());
        }
        return (T) copy;
    }

}
