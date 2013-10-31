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

import java.util.List;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.google.common.collect.Lists;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class SequenceNode extends AbstractSequenceNode<SequenceNode> {
    private final List<Node> nodes;

    public SequenceNode(NodeFactory factory) {
        this(factory, Lists.<Node>newArrayList());
    }

    public SequenceNode(NodeFactory factory, List<Node> seq) {
        super(factory);
        this.nodes = checkNotNull(seq);
    }

    @Override
    public boolean isSequence() {
        return true;
    }

    @Override
    public SequenceNode asSequence() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.SEQ;
    }

    @Override
    protected List<Node> getNodes() {
        return nodes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T copy() {
        SequenceNode copy = getNodeFactory().sequenceNode();
        for (Node node  : this) {
            copy.add(node.copy());
        }
        return (T) copy;
    }


}
