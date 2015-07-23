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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Supplier;

import org.joda.time.DateTime;

import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlDecimalNode;
import com.github.autermann.yaml.nodes.YamlIntegralNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlScalarNode;
import com.github.autermann.yaml.nodes.YamlSeqNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;

/**
 * Factory to create {@link YamlNode}s. The factory will be passed to
 * {@link com.github.autermann.yaml.nodes.YamlContainerNode}s to substitute
 * actual node implementations.
 *
 * @author Christian Autermann
 */
public interface YamlNodeFactory {

    /**
     * Creates a new {@link YamlBinaryNode} from the specified {@code value}.
     * If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBinaryNode} or {@link YamlNullNode}
     */
    default YamlScalarNode binaryNode(Byte[] value) {
        if (value == null) {
            return nullNode();
        }
        final byte[] bytes = new byte[value.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = value[i];
        }
        return binaryNode(bytes);
    }

    /**
     * Creates a new {@link YamlBinaryNode} from the specified {@code value}.
     * If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBinaryNode} or {@link YamlNullNode}
     */
    default YamlScalarNode binaryNode(byte[] value) {
        if (value == null) {
            return nullNode();
        }
        return createBinaryNode(value);
    }

    /**
     * Creates a new {@link YamlBooleanNode} from the specified
     * {@code value}.
     * If {@code value} is {@code null} a {@link YamlNullNode} is
     * returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlBooleanNode} or {@link YamlNullNode}
     */
    default YamlScalarNode booleanNode(Boolean value) {
        if (value == null) {
            return nullNode();
        }
        return booleanNode(value.booleanValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlByteNode} from
     * the specified {@code value}. If {@code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlByteNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode byteNode(Byte value) {
        if (value == null) {
            return nullNode();
        }
        return byteNode(value.byteValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlShortNode} from
     * the specified {@code value}. If {@code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlShortNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode shortNode(Short value) {
        if (value == null) {
            return nullNode();
        }
        return shortNode(value.shortValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlIntegerNode}
     * from the specified {@code value}. If @code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlIntegerNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode intNode(Integer value) {
        if (value == null) {
            return nullNode();
        }
        return intNode(value.intValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlLongNode} from
     * the specified {@code value}. If {@code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlLongNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode longNode(Long value) {
        if (value == null) {
            return nullNode();
        }
        return longNode(value.longValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlBigIntegerNode}
     * from the specified {@code value}. If {@code value} is
     * {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlBigIntegerNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode bigIntegerNode(BigInteger value) {
        if (value == null) {
            return nullNode();
        }
        return createBigIntegerNode(value);
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlFloatNode} from
     * the specified {@code value}. If {@code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlFloatNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode floatNode(Float value) {
        if (value == null) {
            return nullNode();
        }
        return floatNode(value.floatValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlDoubleNode} from
     * the specified {@code value}. If {@code value} is {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlDoubleNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode doubleNode(Double value) {
        if (value == null) {
            return nullNode();
        }
        return doubleNode(value.doubleValue());
    }

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlBigDecimalNode}
     * from the specified {@code value}. If {@code value} is
     * {@code null} a
     * {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link com.github.autermann.yaml.nodes.YamlBigDecimalNode} or
     *         {@link YamlNullNode}
     */
    default YamlScalarNode bigDecimalNode(BigDecimal value) {
        if (value == null) {
            return nullNode();
        }
        return createBigDecimalNode(value);
    }

    /**
     * Creates a new {@link YamlTextNode} from the specified {@code value}.
     * If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTextNode} or {@link YamlNullNode}
     */
    default YamlScalarNode textNode(String value) {
        if (value == null) {
            return nullNode();
        }
        return createTextNode(value);
    }

    /**
     * Creates a new {@link YamlTimeNode} from the specified {@code value}.
     * If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTimeNode} or {@link YamlNullNode}
     */
    default YamlScalarNode dateTimeNode(Date value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(new DateTime(value));
    }

    /**
     * Creates a new {@link YamlTimeNode} from the specified {@code value}.
     * If
     * {@code value} is {@code null} a {@link YamlNullNode} is returned.
     *
     * @param value the value of the node
     *
     * @return a {@link YamlTimeNode} or {@link YamlNullNode}
     */
    default YamlScalarNode dateTimeNode(DateTime value) {
        if (value == null) {
            return nullNode();
        }
        return createDateTimeNode(value);
    }

    /**
     * Creates a new {@link YamlMapNode}.
     *
     * @return the {@link YamlMapNode}
     */
    default YamlMapNode objectNode() {
        return mapNode();
    }

    /**
     * Creates a new {@link YamlSeqNode}.
     *
     * @return the {@link YamlSeqNode}
     */
    default YamlSeqNode arrayNode() {
        return sequenceNode();
    }

    /**
     * Creates a new {@link YamlSeqNode}.
     *
     * @return the {@link YamlSeqNode}
     */
    default YamlSeqNode listNode() {
        return sequenceNode();
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlMapNode}s.
     *
     * @return the {@link Supplier}
     */
    @Deprecated
    default Supplier<YamlMapNode> mapNodeSupplier() {
        return this::mapNode;
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlOrderedMapNode}s.
     *
     * @return the {@link Supplier}
     */
    @Deprecated
    default Supplier<YamlOrderedMapNode> orderedMapNodeSupplier() {
        return this::orderedMapNode;
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlPairsNode}s.
     *
     * @return the {@link Supplier}
     */
    @Deprecated
    default Supplier<YamlPairsNode> pairsNodeSupplier() {
        return this::pairsNode;
    }

    /**
     * Creates a new {@link YamlTextNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlTextNode}
     */
    YamlTextNode createTextNode(String value)
            throws NullPointerException;

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlBigDecimalNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlBigDecimalNode}
     */
    YamlDecimalNode createBigDecimalNode(BigDecimal value)
            throws NullPointerException;

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlBigIntegerNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlBigIntegerNode}
     */
    YamlIntegralNode createBigIntegerNode(BigInteger value)
            throws NullPointerException;

    /**
     * Creates a new {@link YamlBinaryNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlBinaryNode}
     */
    YamlBinaryNode createBinaryNode(byte[] value)
            throws NullPointerException;

    /**
     * Creates a new {@link YamlTimeNode}.
     *
     * @param value the value of the new node (never {@code null})
     *
     * @return the {@link YamlTimeNode}
     */
    YamlTimeNode createDateTimeNode(DateTime value)
            throws NullPointerException;

    /**
     * Creates a new {@link YamlBooleanNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link YamlBooleanNode}
     */
    YamlBooleanNode booleanNode(boolean value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlByteNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlByteNode}
     */
    YamlIntegralNode byteNode(byte value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlShortNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlShortNode}
     */
    YamlIntegralNode shortNode(short value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlIntegerNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlIntegerNode}
     */
    YamlIntegralNode intNode(int value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlLongNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlLongNode}
     */
    YamlIntegralNode longNode(long value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlFloatNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlFloatNode}
     */
    YamlDecimalNode floatNode(float value);

    /**
     * Creates a new {@link com.github.autermann.yaml.nodes.YamlDoubleNode}.
     *
     * @param value the value of the new node
     *
     * @return the {@link com.github.autermann.yaml.nodes.YamlDoubleNode}
     */
    YamlDecimalNode doubleNode(double value);

    /**
     * Creates a new {@link YamlMapNode}.
     *
     * @return the {@link YamlMapNode}
     */
    YamlMapNode mapNode();

    /**
     * Creates a new {@link YamlOrderedMapNode}.
     *
     * @return the {@link YamlOrderedMapNode}
     */
    YamlOrderedMapNode orderedMapNode();

    /**
     * Creates a new {@link YamlPairsNode}.
     *
     * @return the {@link YamlPairsNode}
     */
    YamlPairsNode pairsNode();

    /**
     * Creates a new {@link YamlSeqNode}.
     *
     * @return the {@link YamlSeqNode}
     */
    YamlSeqNode sequenceNode();

    /**
     * Creates a new {@link YamlSetNode}.
     *
     * @return the {@link YamlSetNode}
     */
    YamlSetNode setNode();

    /**
     * Creates a new {@link YamlNullNode}.
     *
     * @return the {@link YamlNullNode}
     */
    YamlNullNode nullNode();

    /**
     * Creates a new {@link DefaultYamlNodeFactory}.
     *
     * @return the {@link DefaultYamlNodeFactory}
     */
    public static DefaultYamlNodeFactory createDefault() {
        return DefaultYamlNodeFactory.create();
    }
}
