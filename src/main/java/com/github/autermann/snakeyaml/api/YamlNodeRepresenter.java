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
package com.github.autermann.snakeyaml.api;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import com.github.autermann.snakeyaml.api.YamlNode.AbstractReturningVisitor;
import com.github.autermann.snakeyaml.api.YamlNode.ReturningVisitor;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class YamlNodeRepresenter extends Representer {

    public YamlNodeRepresenter() {
        YamlNodeRepresentingVisitor visitor = new YamlNodeRepresentingVisitor();
        YamlNodeRepresent represent = new YamlNodeRepresent(visitor);
        representers.put(YamlNullNode.class, represent);
        representers.put(YamlTextNode.class, represent);
        representers.put(YamlBinaryNode.class, represent);
        representers.put(YamlBooleanNode.class, represent);
        representers.put(YamlDecimalNode.class, represent);
        representers.put(YamlIntegralNode.class, represent);
        representers.put(YamlMappingNode.class, represent);
        representers.put(YamlOrderedMappingNode.class, represent);
        representers.put(YamlPairsNode.class, represent);
        representers.put(YamlSequenceNode.class, represent);
        representers.put(YamlSetNode.class, represent);
        multiRepresenters.put(YamlNode.class, represent);
    }

    private Node delegate(Object value) {
        return representData(value);
    }

    private Node delegate(Tag tag, Object value) {
        Preconditions.checkNotNull(tag);
        Node node = delegate(value);
        node.setTag(tag);
        return node;
    }

    private Node delegate(Tag tag, Iterable<Entry<YamlNode, YamlNode>> mapping) {
        List<NodeTuple> value = Lists.newLinkedList();
        MappingNode node = new MappingNode(tag, value, null);
        representedObjects.put(objectToRepresent, node);
        boolean bestStyle = true;
        for (Map.Entry<?, ?> entry : mapping) {
            Node nodeKey = representData(entry.getKey());
            Node nodeValue = representData(entry.getValue());
            bestStyle = bestStyle(nodeKey, bestStyle);
            bestStyle = bestStyle(nodeValue, bestStyle);
            value.add(new NodeTuple(nodeKey, nodeValue));
        }

        if (getDefaultFlowStyle() != FlowStyle.AUTO) {
            node.setFlowStyle(getDefaultFlowStyle().getStyleBoolean());
        } else {
            node.setFlowStyle(bestStyle);
        }
        return node;
    }

    private boolean bestStyle(Node node, boolean bestStyle) {
        if (node instanceof ScalarNode) {
            ScalarNode scalar = (ScalarNode) node;
            if (scalar.getStyle() == null) {
                return false;
            }
        }
        return bestStyle;
    }

    private class YamlNodeRepresent implements Represent {
        private final ReturningVisitor<Node> visitor;

        YamlNodeRepresent(ReturningVisitor<Node> visitor) {
            this.visitor = visitor;
        }

        @Override
        public Node representData(Object data) {
            return ((YamlNode) data).accept(visitor);
        }
    }

    private class YamlNodeRepresentingVisitor extends AbstractReturningVisitor<Node> {

        @Override
        protected Node visitMapping(AbstractYamlMappingNode<?> node) {
            return delegate(node.tag(), node.entries());
        }

        @Override
        protected Node visitSequence(AbstractYamlSequenceNode<?> node) {
            return delegate(node.tag(), node.value());
        }

        @Override
        protected Node visitScalar(AbstractYamlScalarNode<?> node) {
            return delegate(node.tag(), node.value());
        }

        @Override
        public Node visit(YamlTimeNode node) {
            return delegate(node.tag(), ISODateTimeFormat.dateTime().print(node.value()));
        }

        @Override
        public Node visit(YamlBinaryNode node) {
            return delegate(node.tag(), BaseEncoding.base64().withSeparator("\n", 80).encode(node.value()));
        }

    }
}
