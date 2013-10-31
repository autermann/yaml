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

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.github.autermann.snakeyaml.api.Nodes;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.collect.Maps;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class MappingNode extends AbstractMappingNode<MappingNode> {
    private static final MapJoiner JOINER
            = Joiner.on(", ").withKeyValueSeparator(" = ");
    private final Map<Node, Node> nodes;

    protected MappingNode(NodeFactory factory, Map<Node, Node> nodes) {
        super(factory);
        this.nodes = checkNotNull(nodes);
    }

    public MappingNode(NodeFactory factory) {
        this(factory, Maps.<Node, Node>newHashMap());
    }

    @Override
    public MappingNode put(Node key, Node value) {
        getNodes().put(Nodes.nullToNode(key), Nodes.nullToNode(value));
        return this;
    }

    @Override
    public boolean has(Node key) {
        return getNodes().containsKey(Nodes.nullToNode(key));
    }

    @Override
    public boolean hasNotNull(Node key) {
        Node k = Nodes.nullToNode(key);
        return has(k) && !getNodes().get(k).isNull();
    }

    public Node get(String key) {
        return get(getNodeFactory().textNode(key));
    }

    public Node get(Node key) {
        return getNodes().get(Nodes.nullToNode(key));
    }

    public Node path(String key) {
        return path(getNodeFactory().textNode(key));
    }

    public Node path(Node key) {
        return Nodes.nullToMissing(get(Nodes.nullToNode(key)));
    }

    @Override
    public boolean isMapping() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.MAP;
    }

    @Override
    public MappingNode asMapping() {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        JOINER.appendTo(builder.append("["), getNodes()).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNodes());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MappingNode &&
               getNodes().equals(((MappingNode) o).getNodes());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T copy() {
        MappingNode copy = getNodeFactory().mappingNode();
        for (Entry<Node, Node> e : getNodes().entrySet()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return (T) copy;
    }

    protected Map<Node, Node> getNodes() {
        return nodes;
    }

    @Override
    public int size() {
        return getNodes().size();
    }
}
