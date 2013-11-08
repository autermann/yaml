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
package com.github.autermann.yaml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;

import com.github.autermann.yaml.nodes.AbstractYamlContainerNode;
import com.github.autermann.yaml.nodes.AbstractYamlScalarNode;
import com.github.autermann.yaml.nodes.YamlBigDecimalNode;
import com.github.autermann.yaml.nodes.YamlBigIntegerNode;
import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlByteNode;
import com.github.autermann.yaml.nodes.YamlDecimalNode;
import com.github.autermann.yaml.nodes.YamlDoubleNode;
import com.github.autermann.yaml.nodes.YamlFloatNode;
import com.github.autermann.yaml.nodes.YamlIntegerNode;
import com.github.autermann.yaml.nodes.YamlIntegralNode;
import com.github.autermann.yaml.nodes.YamlLongNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlSequenceNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlShortNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;
import com.github.autermann.yaml.util.YamlMapNodeSupplier;
import com.github.autermann.yaml.util.YamlOrderedMapNodeSupplier;
import com.github.autermann.yaml.util.YamlPairsNodeSupplier;
import com.google.common.base.Supplier;

/**
 * Factory to create {@link YamlNode}s. The factory will be passed to
 * {@link AbstractYamlContainerNode}s to substitute actual node implementations.
 *
 * @author Christian Autermann
 */
public abstract class YamlNodeFactory {

    /**
     * Creates a new {@link YamlBinaryNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlBinaryNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlBooleanNode} from the specified {@literal value}.
     * If {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlByteNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlShortNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlIntegerNode} from the specified {@literal value}.
     * If @code value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlLongNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * {@literal value}. If {@literal value} is {@literal null} a {@link YamlNullNode} is
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
     * Creates a new {@link YamlFloatNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlDoubleNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * {@literal value}. If {@literal value} is {@literal null} a {@link YamlNullNode} is
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
     * Creates a new {@link YamlTextNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlTimeNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlTimeNode} from the specified {@literal value}. If
     * {@literal value} is {@literal null} a {@link YamlNullNode} is returned.
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
     * Creates a new {@link YamlMapNode}.
     *
     * @return the {@link YamlMapNode}
     */
    public YamlMapNode objectNode() {
        return mapNode();
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
     * Creates a new {@link Supplier} for {@link YamlMapNode}s.
     *
     * @return the {@link Supplier}
     */
    public Supplier<YamlMapNode> mapNodeSupplier() {
        return new YamlMapNodeSupplier(this);
    }

    /**
     * Creates a new {@link Supplier} for {@link YamlOrderedMapNode}s.
     *
     * @return the {@link Supplier}
     */
    public Supplier<YamlOrderedMapNode> orderedMapNodeSupplier() {
        return new YamlOrderedMapNodeSupplier(this);
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
     * @param value the value of the new node (never {@literal null})
     *
     * @return the {@link YamlTextNode}
     */
    protected abstract YamlTextNode createTextNode(String value);

    /**
     * Creates a new {@link YamlBigDecimalNode}.
     *
     * @param value the value of the new node (never {@literal null})
     *
     * @return the {@link YamlBigDecimalNode}
     */
    protected abstract YamlDecimalNode createBigDecimalNode(BigDecimal value);

    /**
     * Creates a new {@link YamlBigIntegerNode}.
     *
     * @param value the value of the new node (never {@literal null})
     *
     * @return the {@link YamlBigIntegerNode}
     */
    protected abstract YamlIntegralNode createBigIntegerNode(BigInteger value);

    /**
     * Creates a new {@link YamlBinaryNode}.
     *
     * @param value the value of the new node (never {@literal null})
     *
     * @return the {@link YamlBinaryNode}
     */
    protected abstract YamlBinaryNode createBinaryNode(byte[] value);

    /**
     * Creates a new {@link YamlTimeNode}.
     *
     * @param value the value of the new node (never {@literal null})
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
     * Creates a new {@link YamlMapNode}.
     *
     * @return the {@link YamlMapNode}
     */
    public abstract YamlMapNode mapNode();

    /**
     * Creates a new {@link YamlOrderedMapNode}.
     *
     * @return the {@link YamlOrderedMapNode}
     */
    public abstract YamlOrderedMapNode orderedMapNode();

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
