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

    public AbstractYamlScalarNode<?> shortNode(Short value) {
        if (value == null) {
            return nullNode();
        }
        return shortNode(value.shortValue());
    }

    public AbstractYamlScalarNode<?> intNode(Integer value) {
        if (value == null) {
            return nullNode();
        }
        return intNode(value.intValue());
    }

    public AbstractYamlScalarNode<?> longNode(Long value) {
        if (value == null) {
            return nullNode();
        }
        return longNode(value.longValue());
    }

    public AbstractYamlScalarNode<?> bigIntegerNode(BigInteger value) {
        if (value == null) {
            return nullNode();
        }
        return createBigIntegerNode(value);
    }

    public AbstractYamlScalarNode<?> floatNode(Float value) {
        if (value == null) {
            return nullNode();
        }
        return floatNode(value.floatValue());
    }

    public AbstractYamlScalarNode<?> doubleNode(Double value) {
        if (value == null) {
            return nullNode();
        }
        return doubleNode(value.doubleValue());
    }

    public AbstractYamlScalarNode<?> bigDecimalNode(BigDecimal value) {
        if (value == null) {
            return nullNode();
        }
        return createBigDecimalNode(value);
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

    protected abstract YamlDecimalNode createBigDecimalNode(BigDecimal value);

    protected abstract YamlIntegralNode createBigIntegerNode(BigInteger value);

    protected abstract YamlBinaryNode createBinaryNode(byte[] value);

    protected abstract YamlTimeNode createDateTimeNode(DateTime value);

    public abstract YamlBooleanNode booleanNode(boolean value);

    public abstract YamlIntegralNode byteNode(byte value);

    public abstract YamlIntegralNode shortNode(short value);

    public abstract YamlIntegralNode intNode(int value);

    public abstract YamlIntegralNode longNode(long value);

    public abstract YamlDecimalNode floatNode(float value);

    public abstract YamlDecimalNode doubleNode(double value);

    public abstract YamlMappingNode mappingNode();

    public abstract YamlOrderedMappingNode orderedMappingNode();

    public abstract YamlPairsNode pairsNode();

    public abstract YamlSequenceNode sequenceNode();

    public abstract YamlSetNode setNode();

    public abstract YamlNullNode nullNode();

    public static DefaultYamlNodeFactory createDefault() {
        return DefaultYamlNodeFactory.create();
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
