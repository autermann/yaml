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
package com.github.autermann.snakeyaml.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

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

public class YamlNodeRepresenter extends Representer {

    private final BaseEncoding binaryEncoding;
    private final DateTimeFormatter timeEncoding;

    public YamlNodeRepresenter() {
        this(new DumperOptions());
    }

    public YamlNodeRepresenter(DumperOptions options) {
        checkNotNull(options);
        this.timeEncoding = ISODateTimeFormat.dateTime();
        this.binaryEncoding = BaseEncoding.base64()
                .withSeparator(options.getLineBreak().getString(),
                               options.getWidth());
        YamlNodeRepresent represent = new YamlNodeRepresent();
        register(YamlNullNode.class, represent);
        register(YamlBooleanNode.class, represent);
        register(YamlBinaryNode.class, represent);
        register(YamlTextNode.class, represent);
        register(YamlDecimalNode.class, represent);
        register(YamlIntegralNode.class, represent);
        // has to be before YamlMappingNode
        register(YamlOrderedMappingNode.class, represent);
        register(YamlPairsNode.class, represent);
        register(YamlMappingNode.class, represent);
        register(YamlSequenceNode.class, represent);
        register(YamlSetNode.class, represent);
        register(YamlNode.class, represent);
    }

    private void register(Class<? extends YamlNode> type, Represent represent) {
        this.representers.put(type, represent);
        this.multiRepresenters.put(type, represent);
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

    private class YamlNodeRepresent extends AbstractReturningYamlNodeVisitor<Node>
            implements Represent {

        @Override
        public Node representData(Object data) {
            return ((YamlNode) data).accept(this);
        }

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
            return delegate(node.tag(), timeEncoding.print(node.value()));
        }

        @Override
        public Node visit(YamlBinaryNode node) {
            return delegate(node.tag(), binaryEncoding.encode(node.value()));
        }
    }
}
