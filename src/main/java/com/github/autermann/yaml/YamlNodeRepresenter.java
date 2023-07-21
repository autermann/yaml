/*
 * Copyright 2013-2015 Christian Autermann
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
package com.github.autermann.yaml;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

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

import com.github.autermann.yaml.nodes.YamlBigDecimalNode;
import com.github.autermann.yaml.nodes.YamlBigIntegerNode;
import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlByteNode;
import com.github.autermann.yaml.nodes.YamlDoubleNode;
import com.github.autermann.yaml.nodes.YamlFloatNode;
import com.github.autermann.yaml.nodes.YamlIntegerNode;
import com.github.autermann.yaml.nodes.YamlLongNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlMappingNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlScalarNode;
import com.github.autermann.yaml.nodes.YamlSeqNode;
import com.github.autermann.yaml.nodes.YamlSequenceNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlShortNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;
import com.google.common.io.BaseEncoding;

/**
 * {@link Representer} for {@link YamlNode}s.
 *
 * @author Christian Autermann
 */
public class YamlNodeRepresenter extends Representer {

    /**
     * The encoding used for {@link YamlBinaryNode}s.
     */
    private final BaseEncoding binaryEncoding;
    /**
     * The encoding used for {@link YamlTimeNode}s.
     */
    private final DateTimeFormatter timeEncoding;

    /**
     * Creates a new represent using default {@link DumperOptions}.
     */
    public YamlNodeRepresenter() {
        this(new DumperOptions());
    }

    /**
     * Creates a new representer using the supplied {@link DumperOptions}.
     *
     * @param options the dumper options
     */
    public YamlNodeRepresenter(DumperOptions options) {
        super(options);
        Objects.requireNonNull(options);
        this.timeEncoding = ISODateTimeFormat.dateTime();
        this.binaryEncoding = BaseEncoding.base64()
                .withSeparator(options.getLineBreak().getString(),
                               options.getWidth());
        YamlNodeRepresent represent = new YamlNodeRepresent();
        register(YamlNullNode.class, represent);
        register(YamlBooleanNode.class, represent);
        register(YamlBinaryNode.class, represent);
        register(YamlTextNode.class, represent);
        register(YamlFloatNode.class, represent);
        register(YamlDoubleNode.class, represent);
        register(YamlBigDecimalNode.class, represent);
        register(YamlByteNode.class, represent);
        register(YamlShortNode.class, represent);
        register(YamlIntegerNode.class, represent);
        register(YamlLongNode.class, represent);
        register(YamlBigIntegerNode.class, represent);
        // has to be before YamlMapNode
        register(YamlOrderedMapNode.class, represent);
        register(YamlPairsNode.class, represent);
        register(YamlMapNode.class, represent);
        register(YamlSeqNode.class, represent);
        register(YamlSetNode.class, represent);
        register(YamlNode.class, represent);
    }

    /**
     * Register the {@link Represent} in {@link #representers} and
     * {@link #multiRepresenters}.
     *
     * @param type      the type to register for
     * @param represent the represent
     */
    private void register(Class<? extends YamlNode> type, Represent represent) {
        this.representers.put(type, represent);
        this.multiRepresenters.put(type, represent);
    }

    /**
     * Delegate the representation of {@code value}.
     *
     * @param value the value to represent
     *
     * @return the representation created by the delegate
     */
    private Node delegate(Object value) {
        return representData(value);
    }

    /**
     * Delegate the representation of {@code value} and apply the specified
     * {@link Tag}
     *
     * @param tag   the tag
     * @param value the value to represent
     *
     * @return the representation created by the delegate
     */
    private Node delegate(Tag tag, Object value) {
        Objects.requireNonNull(tag);
        Node node = delegate(value);
        node.setTag(tag);
        return node;
    }

    /**
     * Create a {@link MappingNode} from the specified entries and tag.
     *
     * @param tag     the tag
     * @param mapping the entries
     *
     * @return the mapping node
     */
    private MappingNode delegate(Tag tag,
                                 Iterable<Entry<YamlNode, YamlNode>> mapping) {
        List<NodeTuple> value = new LinkedList<>();
        MappingNode node = new MappingNode(tag, value, FlowStyle.AUTO);
        representedObjects.put(objectToRepresent, node);
        FlowStyle bestStyle = FlowStyle.AUTO;
        for (Map.Entry<?, ?> entry : mapping) {
            Node nodeKey = representData(entry.getKey());
            Node nodeValue = representData(entry.getValue());
            bestStyle = bestStyle(nodeKey, bestStyle);
            bestStyle = bestStyle(nodeValue, bestStyle);
            value.add(new NodeTuple(nodeKey, nodeValue));
        }

        if (getDefaultFlowStyle() != FlowStyle.AUTO) {
            node.setFlowStyle(getDefaultFlowStyle());
        } else {
            node.setFlowStyle(bestStyle);
        }
        return node;
    }

    /**
     * Gets the best style for the supplied node.
     *
     * @param node      the node to check
     * @param bestStyle the current best style
     *
     * @return the new best style
     */
    private FlowStyle bestStyle(Node node, FlowStyle bestStyle) {
        if (node instanceof ScalarNode) {
            ScalarNode scalar = (ScalarNode) node;
            if (scalar.getScalarStyle() == null) {
                return FlowStyle.AUTO;
            }
        }
        return bestStyle;
    }

    /**
     * Representing visitor to represent {@link YamlNode}s.
     */
    private class YamlNodeRepresent
            implements SimpleReturningYamlNodeVisitor<Node>, Represent {

        @Override
        public Node representData(Object data) {
            YamlNode node = (YamlNode) data;
            if (!node.exists()) {
                throw new IllegalArgumentException();
            }
            return node.accept(this);
        }

        @Override
        public Node visitMapping(YamlMappingNode<?> node) {
            return delegate(node.tag(), node.entries());
        }

        @Override
        public Node visitSequence(YamlSequenceNode<?> node) {
            return delegate(node.tag(), node.value());
        }

        @Override
        public Node visitScalar(YamlScalarNode node) {
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
