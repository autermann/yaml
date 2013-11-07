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

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlContainerNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBigDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBigIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlByteNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDoubleNode;
import com.github.autermann.snakeyaml.api.nodes.YamlFloatNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlLongNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlShortNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;
import com.google.common.base.Supplier;

/**
 * Factory to create {@link YamlNode}s. The factory will be passed to
 * {@link AbstractYamlContainerNode}s to substitute actual node implementations.
 *
 * @author Christian Autermann
 */
public abstract class YamlNodeFactory {

    /**
     * Creates a new {@link YamlBinaryNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBinaryNode} or {@link YamlNullNode}
     */
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

    /**
     * Creates a new {@link YamlBinaryNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBinaryNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> binaryNode(byte[] value) {
        if (value == null) {
            return nullNode();
        }
        return createBinaryNode(value);
    }

    /**
     * Creates a new {@link YamlBooleanNode} from the specified {@code value}.
     * If {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBooleanNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> booleanNode(Boolean value) {
        if (value == null) {
            return nullNode();
        }
        return booleanNode(value.booleanValue());
    }

    /**
     * Creates a new {@link YamlByteNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlByteNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> byteNode(Byte value) {
        if (value == null) {
            return nullNode();
        }
        return byteNode(value.byteValue());
    }

    /**
     * Creates a new {@link YamlShortNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlShortNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> shortNode(Short value) {
        if (value == null) {
            return nullNode();
        }
        return shortNode(value.shortValue());
    }

    /**
     * Creates a new {@link YamlIntegerNode} from the specified {@code value}.
     * If @code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlIntegerNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> intNode(Integer value) {
        if (value == null) {
            return nullNode();
        }
        return intNode(value.intValue());
    }

    /**
     * Creates a new {@link YamlLongNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlLongNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> longNode(Long value) {
        if (value == null) {
            return nullNode();
        }
        return longNode(value.longValue());
    }

    /**
     * Creates a new {@link YamlBigIntegerNode} from the specified
     * {@code value}. If {@code value} is {@code null} a {@link YamlNullNode} is
     * returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBigIntegerNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> bigIntegerNode(BigInteger value) {
        if (value == null) {
            return nullNode();
        }
        return createBigIntegerNode(value);
    }

    /**
     * Creates a new {@link YamlFloatNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlFloatNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> floatNode(Float value) {
        if (value == null) {
            return nullNode();
        }
        return floatNode(value.floatValue());
    }

    /**
     * Creates a new {@link YamlDoubleNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlDoubleNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> doubleNode(Double value) {
        if (value == null) {
            return nullNode();
        }
        return doubleNode(value.doubleValue());
    }

    /**
     * Creates a new {@link YamlBigDecimalNode} from the specified
     * {@code value}. If {@code value} is {@code null} a {@link YamlNullNode} is
     * returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBigDecimalNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> bigDecimalNode(BigDecimal value) {
        if (value == null) {
            return nullNode();
        }
        return createBigDecimalNode(value);
    }

    /**
     * Creates a new {@link YamlTextNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTextNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> textNode(String value) {
        if (value == null) {
            return nullNode();
        }
        return createTextNode(value);
    }

    /**
     * Creates a new {@link YamlTimeNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTimeNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> dateTimeNode(Date value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(new DateTime(value));
    }

    /**
     * Creates a new {@link YamlTimeNode} from the specified {@code value}. If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTimeNode} or {@link YamlNullNode}
     */
    public AbstractYamlScalarNode<?> dateTimeNode(DateTime value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(value);
    }

    /**
     * Creates a new {@link YamlMappingNode}.
     *
     * @return the {@link YamlMappingNode}
     */
    public YamlMappingNode objectNode() {
        return mappingNode();
    }

    /**
     * Creates a new {@link YamlSequenceNode}.
     *
     * @return the {@link YamlSequenceNode}
     */
    public YamlSequenceNode arrayNode() {
        return sequenceNode();
    }

    /**
     * Creates a new {@link YamlSequenceNode}.
     *
     * @return the {@link YamlSequenceNode}
     */
    public YamlSequenceNode listNode() {
        return sequenceNode();
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlMappingNode}s.
     *
     * @return the {@link Supplier}
     */
    public Supplier<YamlMappingNode> mappingNodeSupplier() {
        return new YamlMappingNodeSupplier(this);
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlOrderedMappingNode}s.
     *
     * @return the {@link Supplier}
     */
    public Supplier<YamlOrderedMappingNode> orderedMappingNodeSupplier() {
        return new YamlOrderedMappingNodeSupplier(this);
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlPairsNode}s.
     *
     * @return the {@link Supplier}
     */
    public Supplier<YamlPairsNode> pairsNodeSupplier() {
        return new YamlPairsNodeSupplier(this);
    }

    /**
     * Creates a new {@link YamlTextNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlTextNode}
     */
    protected abstract YamlTextNode createTextNode(String value);

    /**
     * Creates a new {@link YamlBigDecimalNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlBigDecimalNode}
     */
    protected abstract YamlDecimalNode createBigDecimalNode(BigDecimal value);

    /**
     * Creates a new {@link YamlBigIntegerNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlBigIntegerNode}
     */
    protected abstract YamlIntegralNode createBigIntegerNode(BigInteger value);

    /**
     * Creates a new {@link YamlBinaryNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlBinaryNode}
     */
    protected abstract YamlBinaryNode createBinaryNode(byte[] value);

    /**
     * Creates a new {@link YamlTimeNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlTimeNode}
     */
    protected abstract YamlTimeNode createDateTimeNode(DateTime value);

    /**
     * Creates a new {@link YamlBooleanNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlBooleanNode}
     */
    public abstract YamlBooleanNode booleanNode(boolean value);

    /**
     * Creates a new {@link YamlByteNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlByteNode}
     */
    public abstract YamlIntegralNode byteNode(byte value);

    /**
     * Creates a new {@link YamlShortNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlShortNode}
     */
    public abstract YamlIntegralNode shortNode(short value);

    /**
     * Creates a new {@link YamlIntegerNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlIntegerNode}
     */
    public abstract YamlIntegralNode intNode(int value);

    /**
     * Creates a new {@link YamlLongNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlLongNode}
     */
    public abstract YamlIntegralNode longNode(long value);

    /**
     * Creates a new {@link YamlFloatNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlFloatNode}
     */
    public abstract YamlDecimalNode floatNode(float value);

    /**
     * Creates a new {@link YamlDoubleNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlDoubleNode}
     */
    public abstract YamlDecimalNode doubleNode(double value);

    /**
     * Creates a new {@link YamlMappingNode}.
     *
     * @return the {@link YamlMappingNode}
     */
    public abstract YamlMappingNode mappingNode();

    /**
     * Creates a new {@link YamlOrderedMappingNode}.
     *
     * @return the {@link YamlOrderedMappingNode}
     */
    public abstract YamlOrderedMappingNode orderedMappingNode();

    /**
     * Creates a new {@link YamlPairsNode}.
     *
     * @return the {@link YamlPairsNode}
     */
    public abstract YamlPairsNode pairsNode();

    /**
     * Creates a new {@link YamlSequenceNode}.
     *
     * @return the {@link YamlSequenceNode}
     */
    public abstract YamlSequenceNode sequenceNode();

    /**
     * Creates a new {@link YamlSetNode}.
     *
     * @return the {@link YamlSetNode}
     */
    public abstract YamlSetNode setNode();

    /**
     * Creates a new {@link YamlNullNode}.
     *
     * @return the {@link YamlNullNode}
     */
    public abstract YamlNullNode nullNode();

    /**
     * Creates a new {@link DefaultYamlNodeFactory}.
     *
     * @return the {@link DefaultYamlNodeFactory}
     */
    public static DefaultYamlNodeFactory createDefault() {
        return DefaultYamlNodeFactory.create();
    }
}
