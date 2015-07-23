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

import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlMissingNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlSeqNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.google.common.collect.Iterators;

/**
 * Interface for a generic YAML node.
 *
 * @author Christian Autermann
 */
public interface YamlNode extends Iterable<YamlNode> {
    /**
     * The default value returned by {@link #booleanValue()} and
     * {@link  #asBooleanValue()}: {@value}.
     */
    boolean DEFAULT_BOOLEAN_VALUE = false;
    /**
     * The default value returned by {@link #byteValue()} and
     * {@link  #asByteValue()}: {@value}.
     */
    byte DEFAULT_BYTE_VALUE = (byte) 0;
    /**
     * The default value returned by {@link #shortValue()} and
     * {@link  #asShortValue()}: {@value}.
     */
    short DEFAULT_SHORT_VALUE = (short) 0;
    /**
     * The default value returned by {@link #intValue()} and
     * {@link  #asIntValue()}: {@value}.
     */
    int DEFAULT_INTEGER_VALUE = 0;
    /**
     * The default value returned by {@link #longValue()} and
     * {@link  #asLongValue()}: {@value}.
     */
    long DEFAULT_LONG_VALUE = 0L;
    /**
     * The default value returned by {@link #floatValue()} and
     * {@link  #asFloatValue()}: {@value}.
     */
    float DEFAULT_FLOAT_VALUE = 0.0f;
    /**
     * The default value returned by {@link #doubleValue()} and
     * {@link  #asDoubleValue()}: {@value}.
     */
    double DEFAULT_DOUBLE_VALUE = 0.0d;
    /**
     * The default value returned by {@link #bigDecimalValue()} and
     * {@link  #asBigDecimalValue()}: {@link BigDecimal#ZERO}.
     */
    BigDecimal DEFAULT_BIG_DECIMAL_VALUE = BigDecimal.ZERO;
    /**
     * The default value returned by {@link #bigIntegerValue()} and
     * {@link  #asBigIntegerValue()}: {@link BigInteger#ZERO}.
     */
    BigInteger DEFAULT_BIG_INTEGER_VALUE = BigInteger.ZERO;

    /**
     * Checks if this node does not exists (if it is not a
     * {@link com.github.autermann.yaml.nodes.YamlIntegerNode}.
     *
     * @return if this node exists
     */
    default boolean exists() {
        return true;
    }

    /**
     * Checks if this node is a container node.
     *
     * @return if this is a container node
     *
     * @see YamlSeqNode
     * @see YamlSetNode
     * @see YamlMapNode
     * @see YamlOrderedMapNode
     * @see YamlPairsNode
     */
    default boolean isContainer() {
        return false;
    }

    /**
     * Checks if this node is a mapping node.
     *
     * @return if this is a mapping node
     *
     * @see YamlMapNode
     * @see YamlOrderedMapNode
     * @see #asMap()
     */
    default boolean isMap() {
        return false;
    }

    /**
     * Checks if this node is ordered mapping node.
     *
     * @return if this is ordered mapping node
     *
     * @see YamlOrderedMapNode
     * @see #asOrderedMap()
     */
    default boolean isOrderedMap() {
        return false;
    }

    /**
     * Checks if this node is pairs node.
     *
     * @return if this is pairs node
     *
     * @see YamlPairsNode
     * @see #asPairs()
     */
    default boolean isPairs() {
        return false;
    }

    /**
     * Checks if this node is a sequence node.
     *
     * @return if this is a sequence node
     *
     * @see YamlSeqNode
     * @see #asSequence()
     */
    default boolean isSequence() {
        return false;
    }

    /**
     * Checks if this node is a set node.
     *
     * @return if this is a set node
     *
     * @see YamlSetNode
     * @see #asSet()
     */
    default boolean isSet() {
        return false;
    }

    /**
     * Checks if this node is a scalar node.
     *
     * @return if this is a scalar node
     */
    default boolean isScalar() {
        return false;
    }

    /**
     * Checks if this node is a binary node.
     *
     * @return if this is a binary node
     *
     * @see com.github.autermann.yaml.nodes.YamlBinaryNode
     * @see #binaryValue()
     */
    default boolean isBinary() {
        return false;
    }

    /**
     * Checks if this node is a {@code boolean} node.
     *
     * @return if this is a {@code boolean} node
     *
     * @see com.github.autermann.yaml.nodes.YamlBooleanNode
     * @see #booleanValue()
     */
    default boolean isBoolean() {
        return false;
    }

    /**
     * Checks if this node is a {@code null} node.
     *
     * @return if this is a {@code null} node
     *
     * @see com.github.autermann.yaml.nodes.YamlNullNode
     *
     */
    default boolean isNull() {
        return false;
    }

    /**
     * Checks if this node is number node.
     *
     * @return if this is a number node
     *
     * @see com.github.autermann.yaml.nodes.YamlNumberNode
     * @see #numberValue()
     */
    default boolean isNumber() {
        return false;
    }

    /**
     * Checks if this node is a decimal node.
     *
     * @return if this is a decimal node
     *
     * @see com.github.autermann.yaml.nodes.YamlDecimalNode
     * @see #bigDecimalValue()
     * @see #doubleValue()
     * @see #floatValue()
     */
    default boolean isDecimal() {
        return false;
    }

    /**
     * Checks if this node is {@code float} node.
     *
     * @return if this is a {@code float} node
     *
     * @see #floatValue()
     * @see #doubleValue()
     * @see #bigDecimalValue()
     */
    default boolean isFloat() {
        return false;
    }

    /**
     * Checks if this node is {@code double} node.
     *
     * @return if this is a {@code double} node
     *
     * @see #doubleValue()
     * @see #bigDecimalValue()
     */
    default boolean isDouble() {
        return false;
    }

    /**
     * Checks if this node is {@code BigDecimal} node.
     *
     * @return if this is a {@code BigDecimal} node
     *
     * @see #bigDecimalValue()
     */
    default boolean isBigDecimal() {
        return false;
    }

    /**
     * Checks if this node is integral node.
     *
     * @return if this is a integral node
     *
     * @see com.github.autermann.yaml.nodes.YamlIntegralNode
     * @see com.github.autermann.yaml.nodes.YamlBigIntegerNode
     * @see com.github.autermann.yaml.nodes.YamlLongNode
     * @see com.github.autermann.yaml.nodes.YamlIntegerNode
     * @see com.github.autermann.yaml.nodes.YamlShortNode
     * @see com.github.autermann.yaml.nodes.YamlByteNode
     */
    default boolean isIntegral() {
        return false;
    }

    /**
     * Checks if this node is {@code byte} node.
     *
     * @return if this is a {@code byte} node
     *
     * @see #bigIntegerValue()
     */
    default boolean isBigInteger() {
        return false;
    }

    /**
     * Checks if this node is {@code long} node.
     *
     * @return if this is a {@code long} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     *
     */
    default boolean isLong() {
        return false;
    }

    /**
     * Checks if this node is {@code int} node.
     *
     * @return if this is a {@code int} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     */
    default boolean isInt() {
        return false;
    }

    /**
     * Checks if this node is {@code short} node.
     *
     * @return if this is a {@code short} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     * @see #shortValue()
     */
    default boolean isShort() {
        return false;
    }

    /**
     * Checks if this node is {@code byte} node.
     *
     * @return if this is a {@code byte} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     * @see #shortValue()
     * @see #byteValue()
     */
    default boolean isByte() {
        return false;
    }

    /**
     * Checks if this node is a text node.
     *
     * @return if this a text node
     *
     * @see com.github.autermann.yaml.nodes.YamlTextNode
     * @see #textValue()
     */
    default boolean isText() {
        return false;
    }

    /**
     * Checks if this node is date/time node.
     *
     * @return if this is a time node
     *
     * @see com.github.autermann.yaml.nodes.YamlTimeNode
     * @see #dateTimeValue()
     * @see #dateValue()
     */
    default boolean isTime() {
        return false;
    }

    /**
     * Converts this node into a {@link YamlMapNode} if applicable.
     *
     * @return a mapping node or {@code null}
     *
     * @see #isMap()
     * @see YamlMapNode
     */
    default YamlMapNode asMap() {
        return null;
    }

    /**
     * Converts this node into a {@link YamlMapNode} if applicable.
     *
     * @return a mapping node or {@code null}
     *
     * @see #isOrderedMap()
     * @see YamlOrderedMapNode
     */
    default YamlOrderedMapNode asOrderedMap() {
        return null;
    }

    /**
     * Converts this node into a {@link YamlPairsNode} if applicable.
     *
     * @return a pairs node or {@code null}
     *
     * @see #isPairs()
     * @see YamlPairsNode
     */
    default YamlPairsNode asPairs() {
        return null;
    }

    /**
     * Converts this node into a {@link YamlSeqNode} if applicable.
     *
     * @return a sequence node or {@code null}
     *
     * @see #isSequence()
     * @see YamlSeqNode
     */
    default YamlSeqNode asSequence() {
        return null;
    }

    /**
     * Converts this node into a {@link YamlSetNode} if applicable.
     *
     * @return a set node or {@code null}
     *
     * @see #isSet()
     * @see YamlSetNode
     */
    default YamlSetNode asSet() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@code BigDecimal}. Returns
     * {@link BigDecimal#ZERO} if this node can not be converted to a
     * {@code BigDecimal}.
     *
     * @return the {@code BigDecimal} or {@link BigDecimal#ZERO}
     */
    default BigDecimal asBigDecimalValue() {
        return asBigDecimalValue(DEFAULT_BIG_DECIMAL_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code BigDecimal}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code BigDecimal}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code BigDecimal} or {@code defaultValue}
     */
    default BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code BigDecimal} value of this node or
     * {@link BigDecimal#ZERO} if this is not a BigDecimal node.
     *
     * @return the {@code BigDecimal} or {@link BigDecimal#ZERO}
     *
     * @see #isDecimal()
     */
    default BigDecimal bigDecimalValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@code BigInteger}. Returns
     * {@link BigInteger#ZERO} if this node can not be converted to a
     * {@code BigInteger}.
     *
     * @return the {@code BigInteger} or {@link BigInteger#ZERO}
     */
    default BigInteger asBigIntegerValue() {
        return asBigIntegerValue(DEFAULT_BIG_INTEGER_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code BigInteger}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code BigInteger}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code BigInteger} or {@code defaultValue}
     */
    default BigInteger asBigIntegerValue(BigInteger defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code BigInteger} value of this node or
     * {@code null} if this is not a BigInteger node.
     *
     * @return the {@code BigInteger} or {@code null}
     *
     * @see #isIntegral()
     */
    default BigInteger bigIntegerValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@code boolean}. Returns
     * {@value #DEFAULT_BOOLEAN_VALUE} if this node can not be converted to a
     * {@code boolean}.
     *
     * @return the {@code boolean} or {@value #DEFAULT_BOOLEAN_VALUE}
     */
    default boolean asBooleanValue() {
        return asBooleanValue(DEFAULT_BOOLEAN_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code boolean}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code boolean}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code boolean} or {@code defaultValue}
     */
    default boolean asBooleanValue(boolean defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code boolean} value of this node or
     * {@value #DEFAULT_BOOLEAN_VALUE} if this is not a boolean node.
     *
     * @return the {@code boolean} or {@value #DEFAULT_BOOLEAN_VALUE}
     *
     * @see #isBoolean()
     */
    default boolean booleanValue() {
        return DEFAULT_BOOLEAN_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code byte}. Returns
     * {@value #DEFAULT_BYTE_VALUE} if this node can not be converted to a
     * {@code byte}.
     *
     * @return the {@code byte} or {@value #DEFAULT_BYTE_VALUE}
     */
    default byte asByteValue() {
        return asByteValue(DEFAULT_BYTE_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code byte}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code byte}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code byte} or {@code defaultValue}
     */
    default byte asByteValue(byte defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code byte} value of this node or
     * {@value #DEFAULT_BYTE_VALUE} if this is not a byte node.
     *
     * @return the {@code byte} or {@value #DEFAULT_BYTE_VALUE}
     *
     * @see #isIntegral()
     */
    default byte byteValue() {
        return DEFAULT_BYTE_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code byte[]}. Returns
     * {@code null} if this node can not be converted to a {@code byte[]}.
     *
     * @return the {@code byte[]} or {@code null}
     */
    default byte[] asBinaryValue() {
        return asBinaryValue(DEFAULT_BINARY_VALUE);
    }
    static final byte[] DEFAULT_BINARY_VALUE = new byte[0];

    /**
     * Tries to convert the value of this node to a {@code byte[]}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code byte[]}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code byte[]} or {@code defaultValue}
     */
    default byte[] asBinaryValue(byte[] defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code byte[]} value of this node or {@code null} if this is
     * not a binary node.
     *
     * @return the {@code byte[]} or {@code null}
     *
     * @see #isBinary()
     */
    default byte[] binaryValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@code double}. Returns
     * {@value #DEFAULT_DOUBLE_VALUE} if this node can not be converted to a
     * {@code double}.
     *
     * @return the {@code double} or {@value #DEFAULT_DOUBLE_VALUE}
     */
    default double asDoubleValue() {
        return asDoubleValue(DEFAULT_DOUBLE_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code double}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code double}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code double} or {@code defaultValue}
     */
    default double asDoubleValue(double defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code double} value of this node or
     * {@value #DEFAULT_DOUBLE_VALUE} if this is not a double node.
     *
     * @return the {@code double} or {@value #DEFAULT_DOUBLE_VALUE}
     *
     * @see #isDecimal()
     */
    default double doubleValue() {
        return DEFAULT_DOUBLE_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code float}. Returns
     * {@value #DEFAULT_FLOAT_VALUE} if this node can not be converted to a
     * {@code float}.
     *
     * @return the {@code float} or {@value #DEFAULT_FLOAT_VALUE}
     */
    default float asFloatValue() {
        return asFloatValue(DEFAULT_FLOAT_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code float}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code float}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code float} or {@code defaultValue}
     */
    default float asFloatValue(float defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code float} value of this node or
     * {@value #DEFAULT_FLOAT_VALUE} if this is not a float node.
     *
     * @return the {@code float} or {@value #DEFAULT_FLOAT_VALUE}
     *
     * @see #isDecimal()
     */
    default float floatValue() {
        return DEFAULT_FLOAT_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code int}. Returns
     * {@value #DEFAULT_INTEGER_VALUE} if this node can not be converted to a
     * {@code int}.
     *
     * @return the {@code int} or {@value #DEFAULT_INTEGER_VALUE}
     */
    default int asIntValue() {
        return asIntValue(DEFAULT_INTEGER_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code int}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code int}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code int} or {@code defaultValue}
     */
    default int asIntValue(int defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code int} value of this node or
     * {@value #DEFAULT_INTEGER_VALUE} if this is not a integer node.
     *
     * @return the {@code int} or {@value #DEFAULT_INTEGER_VALUE}
     *
     * @see #isIntegral()
     */
    default int intValue() {
        return DEFAULT_INTEGER_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code long}. Returns
     * {@value #DEFAULT_LONG_VALUE} if this node can not be converted to a
     * {@code long}.
     *
     * @return the {@code long} or {@value #DEFAULT_LONG_VALUE}
     */
    default long asLongValue() {
        return asLongValue(DEFAULT_LONG_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code long}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code long}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code long} or {@code defaultValue}
     */
    default long asLongValue(long defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code long} value of this node or
     * {@value #DEFAULT_LONG_VALUE} if this is not a long node.
     *
     * @return the {@code long} or {@value #DEFAULT_LONG_VALUE}
     *
     * @see #isIntegral()
     */
    default long longValue() {
        return DEFAULT_LONG_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@code Number}. Returns
     * {@code null} if this node can not be converted to a {@code Number}.
     *
     * @return the {@code Number} or {@code null}
     */
    default Number asNumberValue() {
        return asNumberValue(null);
    }

    /**
     * Tries to convert the value of this node to a {@code Number}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code Number}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code Number} or {@code defaultValue}
     */
    default Number asNumberValue(Number defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code Number} value of this node or {@code null} if this is
     * not a numerical node.
     *
     * @return the {@code Number} or {@code null}
     *
     * @see #isNumber()
     */
    default Number numberValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@code short}. Returns
     * {@value #DEFAULT_SHORT_VALUE} if this node can not be converted to a
     * {@code short}.
     *
     * @return the {@code short} or {@value #DEFAULT_SHORT_VALUE}
     */
    default short asShortValue() {
        return asShortValue(DEFAULT_SHORT_VALUE);
    }

    /**
     * Tries to convert the value of this node to a {@code short}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code short}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code short} or {@code defaultValue}
     */
    default short asShortValue(short defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@code short} value of this node or
     * {@value #DEFAULT_SHORT_VALUE} if this is not a short node.
     *
     * @return the {@code short} or {@value #DEFAULT_SHORT_VALUE}
     *
     * @see #isIntegral()
     */
    default short shortValue() {
        return DEFAULT_SHORT_VALUE;
    }

    /**
     * Tries to convert the value of this node to a {@link String}. Returns
     * {@code null} if this node can not be converted to a {@code String}.
     *
     * @return the {@code String} or {@code null}
     */
    default String asTextValue() {
        return asTextValue(null);
    }

    /**
     * Tries to convert the value of this node to a {@code String}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code String}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@code String} or {@code defaultValue}
     */
    default String asTextValue(String defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@link String} value of this node or {@code null} if this is
     * not a text node.
     *
     * @return the {@code String} or {@code null}
     *
     * @see #isText()
     */
    default String textValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@code null} if this node can not be converted to a
     * {@code DateTime}.
     *
     * @return the {@code DateTime} or {@code null}
     */
    default DateTime asDateTimeValue() {
        return asDateTimeValue(null);
    }

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code DateTime}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@code DateTime} or {@code defaultValue}
     */
    default DateTime asDateTimeValue(DateTime defaultValue) {
       return defaultValue;
    }

    /**
     * Returns the {@link DateTime} value of this node or {@code null} if this
     * is not a time node.
     *
     * @return the {@code DateTime} or {@code null}
     *
     * @see #isTime()
     */
    default DateTime dateTimeValue() {
        return null;
    }

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@code null} if this node can not be converted to a {@code Date}.
     *
     * @return the {@code Date} or {@code null}
     */
    default Date asDateValue() {
        return asDateValue(null);
    }

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@code defaultValue} if this node can not be converted to a {@code Date}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@code Date} or {@code defaultValue}
     */
    default Date asDateValue(Date defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the {@link Date} value of this node or {@code null} if this is
     * not a time node.
     *
     * @return the {@code Date} or {@code null}
     *
     * @see #isTime()
     */
    default Date dateValue() {
        return null;
    }

    /**
     * Gets the size of this node if it is a container node.
     *
     * @return the size
     *
     * @see #isContainer()
     */
    default int size() {
        return 0;
    }

    /**
     * Checks if this container node is empty if it is a container node.
     *
     * @return {@code true} if it is empty, else {@code false}
     *
     * * @see #isContainer()
     */
    default boolean isEmpty() {
        return true;
    }

    /**
     * Get the {@link YamlNode} at the specified position or with the specified
     * key. If it does not exist {@code null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified index or key, or
     *         {@code null}
     */
    default YamlNode get(int key) {
        return null;
    }

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist
     * {@code null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or {@code null}
     */
    default YamlNode get(String key) {
        return null;
    }

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist
     * {@code null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or {@code null}
     */
    default YamlNode get(YamlNode key) {
        return null;
    }

    /**
     * Get the {@link YamlNode} at the specified position or with the specified
     * key. If it does not exist a
     * {@link com.github.autermann.yaml.nodes.YamlIntegerNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified index or key, or a
     *         {@link com.github.autermann.yaml.nodes.YamlIntegerNode}.
     */
    default YamlNode path(int key) {
        return YamlMissingNode.instance();
    }

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist a
     * {@link com.github.autermann.yaml.nodes.YamlIntegerNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or a
     *         {@link com.github.autermann.yaml.nodes.YamlIntegerNode}.
     */
    default YamlNode path(String key) {
        return YamlMissingNode.instance();
    }

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist a
     * {@link com.github.autermann.yaml.nodes.YamlIntegerNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or a
     *         {@link com.github.autermann.yaml.nodes.YamlIntegerNode}.
     */
    default YamlNode path(YamlNode key) {
        return YamlMissingNode.instance();
    }

    /**
     * Checks if a {@link YamlNode} with the specified key or index exists.
     *
     * @param key the key or index
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean has(int key) {
        return false;
    }

    /**
     * Checks if a {@link YamlNode} with the specified key exists.
     *
     * @param key the key
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean has(String key) {
        return false;
    }

    /**
     * Checks if a {@link YamlNode} with the specified key exists.
     *
     * @param key the key
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean has(YamlNode key) {
        return false;
    }

    /**
     * Checks if a {@link YamlNode} with the specified key or index exists and
     * is not a {@link com.github.autermann.yaml.nodes.YamlNullNode}.
     *
     * @param key the key or index
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean hasNotNull(int key) {
        return false;
    }

    /**
     * Checks if a {@link YamlNode} with the specified key exists and is not a
     * {@link com.github.autermann.yaml.nodes.YamlNullNode}.
     *
     * @param key the key
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean hasNotNull(String key) {
        return false;
    }

    /**
     * Checks if a {@link YamlNode} with the specified key exists and is not a
     * {@link com.github.autermann.yaml.nodes.YamlNullNode}.
     *
     * @param key the key
     *
     * @return {@code true} if the node exists, else {@code false}
     */
    default boolean hasNotNull(YamlNode key) {
        return false;
    }

    /**
     * Create a (deep) copy of this node.
     *
     * @return the copy
     */
    YamlNode copy();

    /**
     * Lets a visitor visit this node.
     *
     * @param visitor the non-{@code null} visitor
     *
     * @see YamlNodeVisitor
     * @see #accept(ReturningYamlNodeVisitor)
     */
    void accept(YamlNodeVisitor visitor);

    /**
     * Lets a visitor visit this node.
     *
     * @param <T>     the return type of the visitor
     * @param visitor the non-{@code null} visitor
     *
     * @return the returned value of the visitor
     *
     * @see ReturningYamlNodeVisitor
     * @see #accept(YamlNodeVisitor)
     */
    <T> T accept(ReturningYamlNodeVisitor<T> visitor);

    /**
     * Gets the YAML tag of this node.
     *
     * @return the tag
     */
    Tag tag();

    /**
     * Dumps this node to a string with default options.
     *
     * @return the string representation of this node
     *
     * @see Yaml
     */
    default String dump() {
        return new Yaml().dump(this);
    }

    /**
     * Dumps this node to a writer with default options.
     *
     * @param output the writer
     *
     * @see Yaml
     */
    default void dump(Writer output) {
        new Yaml().dump(this, output);
    }

    /**
     * Dumps this node to a output stream with default options.
     *
     * @param output the output stream
     *
     * @see Yaml
     */
    default void dump(OutputStream output) {
        dump(output, new Yaml());
    }

    /**
     * Dumps this node to a string using the supplied options.
     *
     * @param options the options
     *
     * @return the string representation of this node
     *
     * @see Yaml
     */
    default String dump(DumperOptions options) {
        return dump(new Yaml(options));
    }

    /**
     * Dumps this node to a writer using the supplied options.
     *
     * @param output  the writer
     * @param options the options
     *
     * @see Yaml
     */
    default void dump(Writer output, DumperOptions options) {
        dump(output, new Yaml(options));
    }

    /**
     * Dumps this node to a output stream using the supplied options.
     *
     * @param output  the output stream
     * @param options the options
     *
     * @see Yaml
     */
    default void dump(OutputStream output, DumperOptions options) {
        new Yaml(options).dump(this, output);
    }

    /**
     * Dumps this node to a string using the supplied {@link Yaml}. This is
     * equivalent to {@code yaml.dump(this)}.
     *
     * @param yaml the {@link Yaml}
     *
     * @return the string representation of this node
     */
    default String dump(Yaml yaml) {
        return yaml.dump(this);
    }

    /**
     * Dumps this node to a writer using the supplied {@link Yaml}. This is
     * equivalent to {@code yaml.dump(this, output)}.
     *
     * @param output the writer
     * @param yaml   the {@link Yaml}
     */
    default void dump(Writer output, Yaml yaml) {
        yaml.dump(this, output);
    }

    /**
     * Dumps this node to a output stream using the supplied {@link Yaml}. This
     * is equivalent to {@code yaml.dump(this, output)}.
     *
     * @param output the output stream
     * @param yaml   the {@link Yaml}
     */
    default void dump(OutputStream output, Yaml yaml) {
        yaml.dump(this, output);
    }

    @Override
    default Iterator<YamlNode> iterator() {
        return Iterators.<YamlNode>singletonIterator(this);
    }

    /**
     * Creates a stream of nodes for this {@code YamlNode}.
     *
     * @return the stream
     */
    default Stream<YamlNode> stream() {
        return Stream.of(this);
    }

}
