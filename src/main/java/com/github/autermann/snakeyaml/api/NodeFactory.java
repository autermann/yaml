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

import java.math.BigDecimal;
import java.math.BigInteger;

import com.github.autermann.snakeyaml.api.collection.MappingNode;
import com.github.autermann.snakeyaml.api.collection.OrderedMappingNode;
import com.github.autermann.snakeyaml.api.collection.PairsNode;
import com.github.autermann.snakeyaml.api.collection.SequenceNode;
import com.github.autermann.snakeyaml.api.collection.SetNode;
import com.github.autermann.snakeyaml.api.scalar.BinaryNode;
import com.github.autermann.snakeyaml.api.scalar.BooleanNode;
import com.github.autermann.snakeyaml.api.scalar.DecimalNode;
import com.github.autermann.snakeyaml.api.scalar.IntegralNode;
import com.github.autermann.snakeyaml.api.scalar.NullNode;
import com.github.autermann.snakeyaml.api.scalar.ScalarNode;
import com.github.autermann.snakeyaml.api.scalar.TextNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public abstract class NodeFactory {

    public ScalarNode binaryNode(Byte[] value) {
        if (value == null) {
            return nullNode();
        }
        final byte[] bytes = new byte[value.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = value[i].byteValue();
        }
        return binaryNode(bytes);
    }

    public ScalarNode binaryNode(byte[] value) {
        if (value == null) {
            return nullNode();
        }
        return createBinaryNode(value);
    }

    public ScalarNode booleanNode(Boolean value) {
        if (value == null) {
            return nullNode();
        }
        return booleanNode(value.booleanValue());
    }

    public ScalarNode byteNode(Byte value) {
        if (value == null) {
            return nullNode();
        }
        return byteNode(value.byteValue());
    }

    public ScalarNode byteNode(byte value) {
        return longNode((long) value);
    }

    public ScalarNode shortNode(Short value) {
        if (value == null) {
            return nullNode();
        }
        return shortNode(value.shortValue());
    }

    public ScalarNode shortNode(short value) {
        return longNode((long) value);
    }

    public ScalarNode intNode(Integer value) {
        if (value == null) {
            return nullNode();
        }
        return intNode(value.intValue());
    }

    public ScalarNode intNode(int value) {
        return longNode((long) value);
    }

    public ScalarNode longNode(Long value) {
        if (value == null) {
            return nullNode();
        }
        return longNode(value.longValue());
    }

    public ScalarNode longNode(long value) {
        return bigIntegerNode(BigInteger.valueOf(value));
    }

    public ScalarNode bigIntegerNode(BigInteger value) {
        if (value == null) {
            return nullNode();
        }
        return createIntegralNode(value);
    }

    public ScalarNode floatNode(Float value) {
        if (value == null) {
            return nullNode();
        }
        return floatNode(value.floatValue());
    }

    public ScalarNode floatNode(float value) {
        return doubleNode((double) value);
    }

    public ScalarNode doubleNode(Double value) {
        if (value == null) {
            return nullNode();
        }
        return doubleNode(value.doubleValue());
    }

    public ScalarNode doubleNode(double value) {
        return bigDecimalNode(BigDecimal.valueOf(value));
    }

    public ScalarNode bigDecimalNode(BigDecimal value) {
        if (value == null) {
            return nullNode();
        }
        return createDecimalNode(value);
    }

    public ScalarNode textNode(String value) {
        if (value == null) {
            return nullNode();
        }
        return createTextNode(value);
    }

    protected abstract TextNode createTextNode(String value);

    protected abstract DecimalNode createDecimalNode(BigDecimal value);

    protected abstract IntegralNode createIntegralNode(BigInteger value);

    protected abstract BinaryNode createBinaryNode(byte[] value);

    public abstract BooleanNode booleanNode(boolean value);

    public MappingNode objectNode() {
        return mappingNode();
    }

    public abstract MappingNode mappingNode();

    public abstract OrderedMappingNode orderedMappingNode();

    public abstract PairsNode pairsNode();

    public SequenceNode arrayNode() {
        return sequenceNode();
    }

    public SequenceNode listNode() {
        return sequenceNode();
    }

    public abstract SequenceNode sequenceNode();

    public abstract SetNode setNode();

    public abstract NullNode nullNode();

    public static NodeFactory getDefault() {
        return DefaultNodeFactory.instance();
    }

    private static class DefaultNodeFactory extends NodeFactory {
        private static final DefaultNodeFactory instance
                = new DefaultNodeFactory();

        private DefaultNodeFactory() {
        }

        @Override
        protected TextNode createTextNode(String value) {
            return new TextNode(value);
        }

        @Override
        protected DecimalNode createDecimalNode(BigDecimal value) {
            return new DecimalNode(value);
        }

        @Override
        protected IntegralNode createIntegralNode(BigInteger value) {
            return new IntegralNode(value);
        }

        @Override
        public MappingNode mappingNode() {
            return new MappingNode(this);
        }

        @Override
        public OrderedMappingNode orderedMappingNode() {
            return new OrderedMappingNode(this);
        }

        @Override
        public PairsNode pairsNode() {
            return new PairsNode(this);
        }

        @Override
        public SequenceNode sequenceNode() {
            return new SequenceNode(this);
        }

        @Override
        public SetNode setNode() {
            return new SetNode(this);
        }

        @Override
        public BooleanNode booleanNode(boolean value) {
            return BooleanNode.of(value);
        }

        @Override
        protected BinaryNode createBinaryNode(byte[] value) {
            return new BinaryNode(value);
        }

        @Override
        public NullNode nullNode() {
            return NullNode.instance();
        }

        public static DefaultNodeFactory instance() {
            return instance;
        }

    }
}
