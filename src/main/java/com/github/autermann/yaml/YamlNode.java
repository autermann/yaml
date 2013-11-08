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

import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.nodes.AbstractYamlNumberNode;
import com.github.autermann.yaml.nodes.YamlBigIntegerNode;
import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlByteNode;
import com.github.autermann.yaml.nodes.YamlDecimalNode;
import com.github.autermann.yaml.nodes.YamlIntegerNode;
import com.github.autermann.yaml.nodes.YamlIntegralNode;
import com.github.autermann.yaml.nodes.YamlLongNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlMissingNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlSequenceNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlShortNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;

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
    long DEFAULT_LONG_VALUE = 0l;
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
     * The default value returned by {@link #binaryValue()} and
     * {@link  #asBinaryValue()}: {@literal null}.
     */
    byte[] DEFAULT_BINARY_VALUE = null;
    /**
     * The default value returned by {@link #numberValue()} and
     * {@link  #asNumberValue()}: {@literal null}.
     */
    Number DEFAULT_NUMBER_VALUE = null;
    /**
     * The default value returned by {@link #textValue()} and
     * {@link  #asTextValue()}: {@literal null}.
     */
    String DEFAULT_TEXT_VALUE = null;
    /**
     * The default value returned by {@link #dateTimeValue()} and
     * {@link  #asDateTimeValue()}: {@literal null}.
     */
    DateTime DEFAULT_DATE_TIME_VALUE = null;
    /**
     * The default value returned by {@link #dateValue()} and
     * {@link  #asDateValue()}: {@literal null}.
     */
    Date DEFAULT_DATE_VALUE = null;

    /**
     * Checks if this node does not exists (if it is not a
     * {@link YamlMissingNode}.
     *
     * @return if this node exists
     */
    boolean exists();

    /**
     * Checks if this node is a container node.
     *
     * @return if this is a container node
     *
     * @see YamlSequenceNode
     * @see YamlSetNode
     * @see YamlMapNode
     * @see YamlOrderedMapNode
     * @see YamlPairsNode
     */
    boolean isContainer();

    /**
     * Checks if this node is a mapping node.
     *
     * @return if this is a mapping node
     *
     * @see YamlMapNode
     * @see YamlOrderedMapNode
     * @see #asMap()
     */
    boolean isMap();

    /**
     * Checks if this node is ordered mapping node.
     *
     * @return if this is ordered mapping node
     *
     * @see YamlOrderedMapNode
     * @see #asOrderedMap()
     */
    boolean isOrderedMap();

    /**
     * Checks if this node is pairs node.
     *
     * @return if this is pairs node
     *
     * @see YamlPairsNode
     * @see #asPairs()
     */
    boolean isPairs();

    /**
     * Checks if this node is a sequence node.
     *
     * @return if this is a sequence node
     *
     * @see YamlSequenceNode
     * @see #asSequence()
     */
    boolean isSequence();

    /**
     * Checks if this node is a set node.
     *
     * @return if this is a set node
     *
     * @see YamlSetNode
     * @see #asSet()
     */
    boolean isSet();

    /**
     * Checks if this node is a scalar node.
     *
     * @return if this is a scalar node
     */
    boolean isScalar();

    /**
     * Checks if this node is a binary node.
     *
     * @return if this is a binary node
     *
     * @see YamlBinaryNode
     * @see #binaryValue()
     */
    boolean isBinary();

    /**
     * Checks if this node is a {@literal boolean} node.
     *
     * @return if this is a {@literal boolean} node
     *
     * @see YamlBooleanNode
     * @see #booleanValue()
     */
    boolean isBoolean();

    /**
     * Checks if this node is a {@literal null} node.
     *
     * @return if this is a {@literal null} node
     *
     * @see YamlNullNode
     *
     */
    boolean isNull();

    /**
     * Checks if this node is number node.
     *
     * @return if this is a number node
     *
     * @see AbstractYamlNumberNode
     * @see #numberValue()
     */
    boolean isNumber();

    /**
     * Checks if this node is a decimal node.
     *
     * @return if this is a decimal node
     *
     * @see YamlDecimalNode
     * @see #bigDecimalValue()
     * @see #doubleValue()
     * @see #floatValue()
     */
    boolean isDecimal();

    /**
     * Checks if this node is {@literal float} node.
     *
     * @return if this is a {@literal float} node
     *
     * @see #floatValue()
     * @see #doubleValue()
     * @see #bigDecimalValue()
     */
    boolean isFloat();

    /**
     * Checks if this node is {@literal double} node.
     *
     * @return if this is a {@literal double} node
     *
     * @see #doubleValue()
     * @see #bigDecimalValue()
     */
    boolean isDouble();

    /**
     * Checks if this node is {@literal BigDecimal} node.
     *
     * @return if this is a {@literal BigDecimal} node
     *
     * @see #bigDecimalValue()
     */
    boolean isBigDecimal();

    /**
     * Checks if this node is integral node.
     *
     * @return if this is a integral node
     *
     * @see YamlIntegralNode
     * @see YamlBigIntegerNode
     * @see YamlLongNode
     * @see YamlIntegerNode
     * @see YamlShortNode
     * @see YamlByteNode
     */
    boolean isIntegral();

    /**
     * Checks if this node is {@literal byte} node.
     *
     * @return if this is a {@literal byte} node
     *
     * @see #bigIntegerValue()
     */
    boolean isBigInteger();

    /**
     * Checks if this node is {@literal long} node.
     *
     * @return if this is a {@literal long} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     *
     */
    boolean isLong();

    /**
     * Checks if this node is {@literal int} node.
     *
     * @return if this is a {@literal int} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     */
    boolean isInt();

    /**
     * Checks if this node is {@literal short} node.
     *
     * @return if this is a {@literal short} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     * @see #shortValue()
     */
    boolean isShort();

    /**
     * Checks if this node is {@literal byte} node.
     *
     * @return if this is a {@literal byte} node
     *
     * @see #bigIntegerValue()
     * @see #longValue()
     * @see #intValue()
     * @see #shortValue()
     * @see #byteValue()
     */
    boolean isByte();

    /**
     * Checks if this node is a text node.
     *
     * @return if this a text node
     *
     * @see YamlTextNode
     * @see #textValue()
     */
    boolean isText();

    /**
     * Checks if this node is date/time node.
     *
     * @return if this is a time node
     *
     * @see YamlTimeNode
     * @see #dateTimeValue()
     * @see #dateValue()
     */
    boolean isTime();

    /**
     * Converts this node into a {@link YamlMapNode} if applicable.
     *
     * @return a mapping node or {@literal null}
     *
     * @see #isMap()
     * @see YamlMapNode
     */
    YamlMapNode asMap();

    /**
     * Converts this node into a {@link YamlMapNode} if applicable.
     *
     * @return a mapping node or {@literal null}
     *
     * @see #isOrderedMap()
     * @see YamlOrderedMapNode
     */
    YamlOrderedMapNode asOrderedMap();

    /**
     * Converts this node into a {@link YamlPairsNode} if applicable.
     *
     * @return a pairs node or {@literal null}
     *
     * @see #isPairs()
     * @see YamlPairsNode
     */
    YamlPairsNode asPairs();

    /**
     * Converts this node into a {@link YamlSequenceNode} if applicable.
     *
     * @return a sequence node or {@literal null}
     *
     * @see #isSequence()
     * @see YamlSequenceNode
     */
    YamlSequenceNode asSequence();

    /**
     * Converts this node into a {@link YamlSetNode} if applicable.
     *
     * @return a set node or {@literal null}
     *
     * @see #isSet()
     * @see YamlSetNode
     */
    YamlSetNode asSet();

    /**
     * Tries to convert the value of this node to a {@literal BigDecimal}. Returns
     * {@link BigDecimal#ZERO} if this node can not be converted to a
     * {@literal BigDecimal}.
     *
     * @return the {@literal BigDecimal} or {@link BigDecimal#ZERO}
     */
    BigDecimal asBigDecimalValue();

    /**
     * Tries to convert the value of this node to a {@literal BigDecimal}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal BigDecimal}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal BigDecimal} or {@literal defaultValue}
     */
    BigDecimal asBigDecimalValue(BigDecimal defaultValue);

    /**
     * Returns the {@literal BigDecimal} value of this node or
     * {@link BigDecimal#ZERO} if this is not a BigDecimal node.
     *
     * @return the {@literal BigDecimal} or {@link BigDecimal#ZERO}
     *
     * @see #isDecimal()
     */
    BigDecimal bigDecimalValue();

    /**
     * Tries to convert the value of this node to a {@literal BigInteger}. Returns
     * {@link BigInteger#ZERO} if this node can not be converted to a
     * {@literal BigInteger}.
     *
     * @return the {@literal BigInteger} or {@link BigInteger#ZERO}
     */
    BigInteger asBigIntegerValue();

    /**
     * Tries to convert the value of this node to a {@literal BigInteger}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal BigInteger}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal BigInteger} or {@literal defaultValue}
     */
    BigInteger asBigIntegerValue(BigInteger defaultValue);

    /**
     * Returns the {@literal BigInteger} value of this node or
     * {@link BigInteger#ZERO} if this is not a BigInteger node.
     *
     * @return the {@literal BigInteger} or {@link BigInteger#ZERO}
     *
     * @see #isIntegral()
     */
    BigInteger bigIntegerValue();

    /**
     * Tries to convert the value of this node to a {@literal boolean}. Returns
     * {@value #DEFAULT_BOOLEAN_VALUE} if this node can not be converted to a
     * {@literal boolean}.
     *
     * @return the {@literal boolean} or {@value #DEFAULT_BOOLEAN_VALUE}
     */
    boolean asBooleanValue();

    /**
     * Tries to convert the value of this node to a {@literal boolean}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal boolean}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal boolean} or {@literal defaultValue}
     */
    boolean asBooleanValue(boolean defaultValue);

    /**
     * Returns the {@literal boolean} value of this node or
     * {@value #DEFAULT_BOOLEAN_VALUE} if this is not a boolean node.
     *
     * @return the {@literal boolean} or {@value #DEFAULT_BOOLEAN_VALUE}
     *
     * @see #isBoolean()
     */
    boolean booleanValue();

    /**
     * Tries to convert the value of this node to a {@literal byte}. Returns
     * {@value #DEFAULT_BYTE_VALUE} if this node can not be converted to a
     * {@literal byte}.
     *
     * @return the {@literal byte} or {@value #DEFAULT_BYTE_VALUE}
     */
    byte asByteValue();

    /**
     * Tries to convert the value of this node to a {@literal byte}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal byte}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal byte} or {@literal defaultValue}
     */
    byte asByteValue(byte defaultValue);

    /**
     * Returns the {@literal byte} value of this node or
     * {@value #DEFAULT_BYTE_VALUE} if this is not a byte node.
     *
     * @return the {@literal byte} or {@value #DEFAULT_BYTE_VALUE}
     *
     * @see #isIntegral()
     */
    byte byteValue();

    /**
     * Tries to convert the value of this node to a {@literal byte[]}. Returns
     * {@literal null} if this node can not be converted to a {@literal byte[]}.
     *
     * @return the {@literal byte[]} or {@literal null}
     */
    byte[] asBinaryValue();

    /**
     * Tries to convert the value of this node to a {@literal byte[]}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal byte[]}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal byte[]} or {@literal defaultValue}
     */
    byte[] asBinaryValue(byte[] defaultValue);

    /**
     * Returns the {@literal byte[]} value of this node or {@literal null} if this is
     * not a binary node.
     *
     * @return the {@literal byte[]} or {@literal null}
     *
     * @see #isBinary()
     */
    byte[] binaryValue();

    /**
     * Tries to convert the value of this node to a {@literal double}. Returns
     * {@value #DEFAULT_DOUBLE_VALUE} if this node can not be converted to a
     * {@literal double}.
     *
     * @return the {@literal double} or {@value #DEFAULT_DOUBLE_VALUE}
     */
    double asDoubleValue();

    /**
     * Tries to convert the value of this node to a {@literal double}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal double}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal double} or {@literal defaultValue}
     */
    double asDoubleValue(double defaultValue);

    /**
     * Returns the {@literal double} value of this node or
     * {@value #DEFAULT_DOUBLE_VALUE} if this is not a double node.
     *
     * @return the {@literal double} or {@value #DEFAULT_DOUBLE_VALUE}
     *
     * @see #isDecimal()
     */
    double doubleValue();

    /**
     * Tries to convert the value of this node to a {@literal float}. Returns
     * {@value #DEFAULT_FLOAT_VALUE} if this node can not be converted to a
     * {@literal float}.
     *
     * @return the {@literal float} or {@value #DEFAULT_FLOAT_VALUE}
     */
    float asFloatValue();

    /**
     * Tries to convert the value of this node to a {@literal float}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal float}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal float} or {@literal defaultValue}
     */
    float asFloatValue(float defaultValue);

    /**
     * Returns the {@literal float} value of this node or
     * {@value #DEFAULT_FLOAT_VALUE} if this is not a float node.
     *
     * @return the {@literal float} or {@value #DEFAULT_FLOAT_VALUE}
     *
     * @see #isDecimal()
     */
    float floatValue();

    /**
     * Tries to convert the value of this node to a {@literal int}. Returns
     * {@value #DEFAULT_INTEGER_VALUE} if this node can not be converted to a
     * {@literal int}.
     *
     * @return the {@literal int} or {@value #DEFAULT_INTEGER_VALUE}
     */
    int asIntValue();

    /**
     * Tries to convert the value of this node to a {@literal int}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal int}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal int} or {@literal defaultValue}
     */
    int asIntValue(int defaultValue);

    /**
     * Returns the {@literal int} value of this node or
     * {@value #DEFAULT_INTEGER_VALUE} if this is not a integer node.
     *
     * @return the {@literal int} or {@value #DEFAULT_INTEGER_VALUE}
     *
     * @see #isIntegral()
     */
    int intValue();

    /**
     * Tries to convert the value of this node to a {@literal long}. Returns
     * {@value #DEFAULT_LONG_VALUE} if this node can not be converted to a
     * {@literal long}.
     *
     * @return the {@literal long} or {@value #DEFAULT_LONG_VALUE}
     */
    long asLongValue();

    /**
     * Tries to convert the value of this node to a {@literal long}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal long}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal long} or {@literal defaultValue}
     */
    long asLongValue(long defaultValue);

    /**
     * Returns the {@literal long} value of this node or
     * {@value #DEFAULT_LONG_VALUE} if this is not a long node.
     *
     * @return the {@literal long} or {@value #DEFAULT_LONG_VALUE}
     *
     * @see #isIntegral()
     */
    long longValue();

    /**
     * Tries to convert the value of this node to a {@literal Number}. Returns
     * {@literal null} if this node can not be converted to a {@literal Number}.
     *
     * @return the {@literal Number} or {@literal null}
     */
    Number asNumberValue();

    /**
     * Tries to convert the value of this node to a {@literal Number}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal Number}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal Number} or {@literal defaultValue}
     */
    Number asNumberValue(Number defaultValue);

    /**
     * Returns the {@literal Number} value of this node or {@literal null} if this is
     * not a numerical node.
     *
     * @return the {@literal Number} or {@literal null}
     *
     * @see #isNumber()
     */
    Number numberValue();

    /**
     * Tries to convert the value of this node to a {@literal short}. Returns
     * {@value #DEFAULT_SHORT_VALUE} if this node can not be converted to a
     * {@literal short}.
     *
     * @return the {@literal short} or {@value #DEFAULT_SHORT_VALUE}
     */
    short asShortValue();

    /**
     * Tries to convert the value of this node to a {@literal short}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal short}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal short} or {@literal defaultValue}
     */
    short asShortValue(short defaultValue);

    /**
     * Returns the {@literal short} value of this node or
     * {@value #DEFAULT_SHORT_VALUE} if this is not a short node.
     *
     * @return the {@literal short} or {@value #DEFAULT_SHORT_VALUE}
     *
     * @see #isIntegral()
     */
    short shortValue();

    /**
     * Tries to convert the value of this node to a {@link String}. Returns
     * {@literal null} if this node can not be converted to a {@literal String}.
     *
     * @return the {@literal String} or {@literal null}
     */
    String asTextValue();

    /**
     * Tries to convert the value of this node to a {@literal String}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal String}.
     *
     * @param defaultValue the default value to return if this node can not be
     *                     converted
     *
     * @return the {@literal String} or {@literal defaultValue}
     */
    String asTextValue(String defaultValue);

    /**
     * Returns the {@link String} value of this node or {@literal null} if this is
     * not a text node.
     *
     * @return the {@literal String} or {@literal null}
     *
     * @see #isText()
     */
    String textValue();

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@literal null} if this node can not be converted to a
     * {@literal DateTime}.
     *
     * @return the {@literal DateTime} or {@literal null}
     */
    DateTime asDateTimeValue();

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@literal defaultValue} if this node can not be converted to a
     * {@literal DateTime}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@literal DateTime} or {@literal defaultValue}
     */
    DateTime asDateTimeValue(DateTime defaultValue);

    /**
     * Returns the {@link DateTime} value of this node or {@literal null} if this
     * is not a time node.
     *
     * @return the {@literal DateTime} or {@literal null}
     *
     * @see #isTime()
     */
    DateTime dateTimeValue();

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@literal null} if this node can not be converted to a {@literal Date}.
     *
     * @return the {@literal Date} or {@literal null}
     */
    Date asDateValue();

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@literal defaultValue} if this node can not be converted to a {@literal Date}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@literal Date} or {@literal defaultValue}
     */
    Date asDateValue(Date defaultValue);

    /**
     * Returns the {@link Date} value of this node or {@literal null} if this is
     * not a time node.
     *
     * @return the {@literal Date} or {@literal null}
     *
     * @see #isTime()
     */
    Date dateValue();

    /**
     * Gets the size of this node if it is a container node.
     *
     * @return the size
     *
     * @see #isContainer()
     */
    int size();

    /**
     * Checks if this container node is empty if it is a container node.
     *
     * @return {@literal true} if it is empty, else {@literal false}
     *
     * * @see #isContainer()
     */
    boolean isEmpty();

    /**
     * Get the {@link YamlNode} at the specified position or with the specified
     * key. If it does not exist {@literal null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified index or key, or
     *         {@literal null}
     */
    YamlNode get(int key);

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist
     * {@literal null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or {@literal null}
     */
    YamlNode get(String key);

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist
     * {@literal null} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or {@literal null}
     */
    YamlNode get(YamlNode key);

    /**
     * Get the {@link YamlNode} at the specified position or with the specified
     * key. If it does not exist a {@link YamlMissingNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified index or key, or a
     *         {@link YamlMissingNode}.
     */
    YamlNode path(int key);

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist a
     * {@link YamlMissingNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or a
     *         {@link YamlMissingNode}.
     */
    YamlNode path(String key);

    /**
     * Get the {@link YamlNode} with the specified key. If it does not exist a
     * {@link YamlMissingNode} is returned.
     *
     * @param key the key or index
     *
     * @return the {@link YamlNode} with the specified key, or a
     *         {@link YamlMissingNode}.
     */
    YamlNode path(YamlNode key);

    /**
     * Checks if a {@link YamlNode} with the specified key or index exists.
     *
     * @param key the key or index
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean has(int key);

    /**
     * Checks if a {@link YamlNode} with the specified key exists.
     *
     * @param key the key
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean has(String key);

    /**
     * Checks if a {@link YamlNode} with the specified key exists.
     *
     * @param key the key
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean has(YamlNode key);

    /**
     * Checks if a {@link YamlNode} with the specified key or index exists and
     * is not a {@link YamlNullNode}.
     *
     * @param key the key or index
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean hasNotNull(int key);

    /**
     * Checks if a {@link YamlNode} with the specified key exists and is not a
     * {@link YamlNullNode}.
     *
     * @param key the key
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean hasNotNull(String key);

    /**
     * Checks if a {@link YamlNode} with the specified key exists and is not a
     * {@link YamlNullNode}.
     *
     * @param key the key
     *
     * @return {@literal true} if the node exists, else {@literal false}
     */
    boolean hasNotNull(YamlNode key);

    /**
     * Create a (deep) copy of this node.
     *
     * @param <T> the type of this node
     *
     * @return the copy
     */
    <T extends YamlNode> T copy();

    /**
     * Lets a visitor visit this node.
     *
     * @param visitor the non-{@literal null} visitor
     *
     * @see YamlNodeVisitor
     * @see #accept(ReturningYamlNodeVisitor)
     */
    void accept(YamlNodeVisitor visitor);

    /**
     * Lets a visitor visit this node.
     *
     * @param <T>     the return type of the visitor
     * @param visitor the non-{@literal null} visitor
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
    String dump();

    /**
     * Dumps this node to a writer with default options.
     *
     * @param output the writer
     *
     * @see Yaml
     */
    void dump(Writer output);

    /**
     * Dumps this node to a output stream with default options.
     *
     * @param output the output stream
     *
     * @see Yaml
     */
    void dump(OutputStream output);

    /**
     * Dumps this node to a string using the supplied options.
     *
     * @param options the options
     *
     * @return the string representation of this node
     *
     * @see Yaml
     */
    String dump(DumperOptions options);

    /**
     * Dumps this node to a writer using the supplied options.
     *
     * @param output  the writer
     * @param options the options
     *
     * @see Yaml
     */
    void dump(Writer output, DumperOptions options);

    /**
     * Dumps this node to a output stream using the supplied options.
     *
     * @param output  the output stream
     * @param options the options
     *
     * @see Yaml
     */
    void dump(OutputStream output, DumperOptions options);

    /**
     * Dumps this node to a string using the supplied {@link Yaml}. This is
     * equivalent to {@literal yaml.dump(this)}.
     *
     * @param yaml the {@link Yaml}
     *
     * @return the string representation of this node
     */
    String dump(Yaml yaml);

    /**
     * Dumps this node to a writer using the supplied {@link Yaml}. This is
     * equivalent to {@literal yaml.dump(this, output)}.
     *
     * @param output the writer
     * @param yaml   the {@link Yaml}
     */
    void dump(Writer output, Yaml yaml);

    /**
     * Dumps this node to a output stream using the supplied {@link Yaml}. This
     * is equivalent to {@literal yaml.dump(this, output)}.
     *
     * @param output the output stream
     * @param yaml   the {@link Yaml}
     */
    void dump(OutputStream output, Yaml yaml);
}
