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

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.google.common.base.Supplier;
import com.google.common.io.BaseEncoding;

/**
 * TODO JavaDoc
 *
 * @author Chrissetian Ausetermann <c.ausetermann@52norseth.org>
 */
public class YamlNodeConstructor extends SafeConstructor {
    private final DateTimeFormatter timeEncoding;
    private final YamlNodeFactory nodeFactory;
    private final BaseEncoding binaryEncoding;

    public YamlNodeConstructor() {
        this(YamlNodeFactory.getDefault(), new DumperOptions());
    }

    public YamlNodeConstructor(DumperOptions dumperOptions) {
        this(YamlNodeFactory.getDefault(), dumperOptions);
    }

    public YamlNodeConstructor(YamlNodeFactory nodeFactory) {
        this(nodeFactory, new DumperOptions());
    }

    public YamlNodeConstructor(YamlNodeFactory nodeFactory,
                               DumperOptions options) {
        checkNotNull(options);
        this.nodeFactory = checkNotNull(nodeFactory);
        this.timeEncoding = ISODateTimeFormat.dateTime();
        this.binaryEncoding = BaseEncoding.base64()
                .withSeparator(options.getLineBreak().getString(),
                               options.getWidth());
        this.yamlConstructors.put(Tag.MAP, new YamlMappingNodeConstruct(this.nodeFactory.mappingNodeSupplier()));
        this.yamlConstructors.put(Tag.OMAP, new YamlMappingNodeConstruct(this.nodeFactory.orderedMappingNodeSupplier()));
        this.yamlConstructors.put(Tag.PAIRS, new YamlMappingNodeConstruct(this.nodeFactory.pairsNodeSupplier()));
        this.yamlConstructors.put(Tag.SEQ, new YamlSequenceNodeConstruct());
        this.yamlConstructors.put(Tag.SET, new YamlSetNodeConstruct());
        this.yamlConstructors.put(Tag.BINARY, new YamlBinaryNodeConstruct());
        this.yamlConstructors.put(Tag.BOOL, new YamlBooleanNodeConstruct());
        this.yamlConstructors.put(Tag.FLOAT, new YamlDecimalNodeConstruct());
        this.yamlConstructors.put(Tag.INT, new YamlIntegralConstruct());
        this.yamlConstructors.put(Tag.NULL, new YamlNullNodeConstruct());
        this.yamlConstructors.put(Tag.STR, new YamlTextNodeConstruct());
        this.yamlConstructors.put(Tag.TIMESTAMP, new YamlTimeNodeConstruct());
    }

    private abstract class AbstractScalarConstruct extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            return construct(((ScalarNode) node).getValue());
        }

        protected abstract AbstractYamlScalarNode<?> construct(String value);
    }

    private class YamlNullNodeConstruct extends AbstractScalarConstruct {
        @Override
        public AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.nullNode();
        }
    }

    private class YamlTextNodeConstruct extends AbstractScalarConstruct {
        @Override
        public AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.textNode(value);
        }
    }

    private class YamlDecimalNodeConstruct extends AbstractScalarConstruct {
        @Override
        public AbstractYamlScalarNode<?> construct(String value) {
            String v = value.toLowerCase().replaceAll("_", "");
            if (v.equals(".inf")) {
                return nodeFactory.doubleNode(Double.POSITIVE_INFINITY);
            } else if (v.equals(".nan")) {
                return nodeFactory.doubleNode(Double.NaN);
            } else if (v.equals("-.inf")) {
                return nodeFactory.doubleNode(Double.NEGATIVE_INFINITY);
            }
            return nodeFactory.bigDecimalNode(new BigDecimal(value));
        }
    }

    private class YamlIntegralConstruct extends AbstractScalarConstruct {
        @Override
        public AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.bigIntegerNode(new BigInteger(value));
        }
    }

    private class YamlBooleanNodeConstruct extends AbstractScalarConstruct {
        @Override
        public AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.booleanNode(Boolean.valueOf(value));
        }
    }

    private class YamlBinaryNodeConstruct extends AbstractScalarConstruct {
        @Override
        protected AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.createBinaryNode(binaryEncoding.decode(value));
        }
    }

    private class YamlTimeNodeConstruct extends AbstractScalarConstruct {
        @Override
        protected AbstractYamlScalarNode<?> construct(String value) {
            return nodeFactory.dateTimeNode(timeEncoding.parseDateTime(value));
        }
    }

    private class YamlMappingNodeConstruct extends AbstractConstruct {
        private final Supplier<? extends AbstractYamlMappingNode<?>> supplier;

        YamlMappingNodeConstruct(
                Supplier<? extends AbstractYamlMappingNode<?>> supplier) {
            this.supplier = supplier;
        }

        @Override
        public YamlNode construct(Node node) {
            AbstractYamlMappingNode<?> mapping = supplier.get();
            MappingNode mnode = (MappingNode) node;
            for (NodeTuple tuple : mnode.getValue()) {
                mapping.put(
                        (YamlNode) constructObject(tuple.getKeyNode()),
                        (YamlNode) constructObject(tuple.getValueNode()));
            }
            return mapping;
        }
    }

    private class YamlSequenceNodeConstruct extends AbstractConstruct {
        @Override
        public YamlSequenceNode construct(Node node) {
            YamlSequenceNode seq = nodeFactory.sequenceNode();
            for (Object o : constructSequence((SequenceNode) node)) {
                seq.add((YamlNode) o);
            }
            return seq;
        }
    }

    private class YamlSetNodeConstruct extends AbstractConstruct {
        @Override
        public YamlSetNode construct(Node node) {
            YamlSetNode set = nodeFactory.setNode();
            for (Object o : constructSet((MappingNode) node)) {
                set.add((YamlNode) o);
            }
            return set;
        }
    }
}
