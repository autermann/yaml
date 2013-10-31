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

import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.google.common.collect.Maps;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class OrderedMappingNode extends MappingNode {

    public OrderedMappingNode(NodeFactory factory) {
        super(factory, Maps.<Node, Node>newLinkedHashMap());
    }

    @Override
    public boolean isOrderedMapping() {
        return true;
    }

    @Override
    public OrderedMappingNode asOrderedMapping() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.OMAP;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T copy() {
        OrderedMappingNode copy = getNodeFactory().orderedMappingNode();
        for (Entry<Node, Node> e : getNodes().entrySet()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return (T) copy;
    }
}
