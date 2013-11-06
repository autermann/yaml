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
import java.util.Date;

import org.joda.time.DateTime;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
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
import com.google.common.base.Supplier;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public abstract class YamlNodeFactory {

    public AbstractYamlScalarNode<?> binaryNode(Byte[] value) {
        if (value == null) {
            return nullNode();
        }
        final byte[] bytes = new byte[value.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = value[i].byteValue();
        }
        return binaryNode(bytes);
    }

    public AbstractYamlScalarNode<?> binaryNode(byte[] value) {
        if (value == null) {
            return nullNode();
        }
        return createBinaryNode(value);
    }

    public AbstractYamlScalarNode<?> booleanNode(Boolean value) {
        if (value == null) {
            return nullNode();
        }
        return booleanNode(value.booleanValue());
    }

    public AbstractYamlScalarNode<?> byteNode(Byte value) {
        if (value == null) {
            return nullNode();
        }
        return byteNode(value.byteValue());
    }

    public YamlIntegralNode byteNode(byte value) {
        return longNode((long) value);
    }

    public AbstractYamlScalarNode<?> shortNode(Short value) {
        if (value == null) {
            return nullNode();
        }
        return shortNode(value.shortValue());
    }

    public YamlIntegralNode shortNode(short value) {
        return longNode((long) value);
    }

    public AbstractYamlScalarNode<?> intNode(Integer value) {
        if (value == null) {
            return nullNode();
        }
        return intNode(value.intValue());
    }

    public YamlIntegralNode intNode(int value) {
        return longNode((long) value);
    }

    public AbstractYamlScalarNode<?> longNode(Long value) {
        if (value == null) {
            return nullNode();
        }
        return longNode(value.longValue());
    }

    public YamlIntegralNode longNode(long value) {
        return createIntegralNode(BigInteger.valueOf(value));
    }

    public AbstractYamlScalarNode<?> bigIntegerNode(BigInteger value) {
        if (value == null) {
            return nullNode();
        }
        return createIntegralNode(value);
    }

    public AbstractYamlScalarNode<?> floatNode(Float value) {
        if (value == null) {
            return nullNode();
        }
        return floatNode(value.floatValue());
    }

    public YamlDecimalNode floatNode(float value) {
        return doubleNode((double) value);
    }

    public AbstractYamlScalarNode<?> doubleNode(Double value) {
        if (value == null) {
            return nullNode();
        }
        return doubleNode(value.doubleValue());
    }

    public YamlDecimalNode doubleNode(double value) {
        return createDecimalNode(BigDecimal.valueOf(value));
    }

    public AbstractYamlScalarNode<?> bigDecimalNode(BigDecimal value) {
        if (value == null) {
            return nullNode();
        }
        return createDecimalNode(value);
    }

    public AbstractYamlScalarNode<?> textNode(String value) {
        if (value == null) {
            return nullNode();
        }
        return createTextNode(value);
    }

    public AbstractYamlScalarNode<?> dateTimeNode(Date value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(new DateTime(value));
    }

    public AbstractYamlScalarNode<?> dateTimeNode(DateTime value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(value);
    }

    public YamlMappingNode objectNode() {
        return mappingNode();
    }

    public YamlSequenceNode arrayNode() {
        return sequenceNode();
    }

    public YamlSequenceNode listNode() {
        return sequenceNode();
    }

    public Supplier<YamlMappingNode> mappingNodeSupplier() {
        return new YamlMappingNodeSupplier(this);
    }

    public Supplier<YamlOrderedMappingNode> orderedMappingNodeSupplier() {
        return new YamlOrderedMappingNodeSupplier(this);
    }

    public Supplier<YamlPairsNode> pairsNodeSupplier() {
        return new YamlPairsNodeSupplier(this);
    }

    protected abstract YamlTextNode createTextNode(String value);

    protected abstract YamlDecimalNode createDecimalNode(BigDecimal value);

    protected abstract YamlIntegralNode createIntegralNode(BigInteger value);

    protected abstract YamlBinaryNode createBinaryNode(byte[] value);

    protected abstract YamlTimeNode createDateTimeNode(DateTime value);

    public abstract YamlBooleanNode booleanNode(boolean value);

    public abstract YamlMappingNode mappingNode();

    public abstract YamlOrderedMappingNode orderedMappingNode();

    public abstract YamlPairsNode pairsNode();

    public abstract YamlSequenceNode sequenceNode();

    public abstract YamlSetNode setNode();

    public abstract YamlNullNode nullNode();

    public static YamlNodeFactory getDefault() {
        return DefaultYamlNodeFactory.instance();
    }

    private static class DefaultYamlNodeFactory extends YamlNodeFactory {
        private static final DefaultYamlNodeFactory instance
                = new DefaultYamlNodeFactory();

        private DefaultYamlNodeFactory() {
        }

        @Override
        protected YamlTextNode createTextNode(String value) {
            return new YamlTextNode(value);
        }

        @Override
        protected YamlDecimalNode createDecimalNode(BigDecimal value) {
            return new YamlDecimalNode(value);
        }

        @Override
        protected YamlIntegralNode createIntegralNode(BigInteger value) {
            return new YamlIntegralNode(value);
        }

        @Override
        public YamlMappingNode mappingNode() {
            return new YamlMappingNode(this);
        }

        @Override
        public YamlOrderedMappingNode orderedMappingNode() {
            return new YamlOrderedMappingNode(this);
        }

        @Override
        public YamlPairsNode pairsNode() {
            return new YamlPairsNode(this);
        }

        @Override
        public YamlSequenceNode sequenceNode() {
            return new YamlSequenceNode(this);
        }

        @Override
        public YamlSetNode setNode() {
            return new YamlSetNode(this);
        }

        @Override
        public YamlBooleanNode booleanNode(boolean value) {
            return YamlBooleanNode.of(value);
        }

        @Override
        protected YamlBinaryNode createBinaryNode(byte[] value) {
            return new YamlBinaryNode(value);
        }

        @Override
        public YamlNullNode nullNode() {
            return YamlNullNode.instance();
        }

        @Override
        protected YamlTimeNode createDateTimeNode(DateTime value) {
            return new YamlTimeNode(value);
        }

        public static DefaultYamlNodeFactory instance() {
            return instance;
        }
    }

    private static class YamlPairsNodeSupplier
            implements Supplier<YamlPairsNode> {
        private final YamlNodeFactory factory;

        YamlPairsNodeSupplier(YamlNodeFactory factory) {
            this.factory = factory;
        }

        @Override
        public YamlPairsNode get() {
            return factory.pairsNode();
        }
    }

    private static class YamlOrderedMappingNodeSupplier
            implements Supplier<YamlOrderedMappingNode> {
        private final YamlNodeFactory factory;

        YamlOrderedMappingNodeSupplier(YamlNodeFactory factory) {
            this.factory = factory;
        }

        @Override
        public YamlOrderedMappingNode get() {
            return factory.orderedMappingNode();
        }
    }

    private static class YamlMappingNodeSupplier
            implements Supplier<YamlMappingNode> {
        private final YamlNodeFactory factory;

        YamlMappingNodeSupplier(YamlNodeFactory factory) {
            this.factory = factory;
        }

        @Override
        public YamlMappingNode get() {
            return factory.mappingNode();
        }
    }
}
