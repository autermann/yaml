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

import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlNumberNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMissingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;

public interface YamlNode {
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
     * {@link  #asBigDecimalValue()}: {@code BigDecimal.ZERO}.
     */
    BigDecimal DEFAULT_BIG_DECIMAL_VALUE = BigDecimal.ZERO;
    /**
     * The default value returned by {@link #bigIntegerValue()} and
     * {@link  #asBigIntegerValue()}: {@code BigInteger.ZERO}.
     */
    BigInteger DEFAULT_BIG_INTEGER_VALUE = BigInteger.ZERO;
    /**
     * The default value returned by {@link #binaryValue()} and
     * {@link  #asBinaryValue()}: {@code null}.
     */
    byte[] DEFAULT_BINARY_VALUE = null;
    /**
     * The default value returned by {@link #numberValue()} and
     * {@link  #asNumberValue()}: {@code null}.
     */
    Number DEFAULT_NUMBER_VALUE = null;
    /**
     * The default value returned by {@link #textValue()} and
     * {@link  #asTextValue()}: {@code null}.
     */
    String DEFAULT_TEXT_VALUE = null;
    /**
     * The default value returned by {@link #dateTimeValue()} and
     * {@link  #asDateTimeValue()}: {@code null}.
     */
    DateTime DEFAULT_DATE_TIME_VALUE = null;
    /**
     * The default value returned by {@link #dateValue()} and
     * {@link  #asDateValue()}: {@code null}.
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
     * @see YamlMappingNode
     * @see YamlOrderedMappingNode
     * @see YamlPairsNode
     */
    boolean isContainer();

    /**
     * Checks if this node is a mapping node.
     *
     * @return if this is a mapping node
     *
     * @see YamlMappingNode
     * @see YamlOrderedMappingNode
     * @see #asMapping()
     */
    boolean isMapping();

    /**
     * Checks if this node is ordered mapping node.
     *
     * @return if this is ordered mapping node
     *
     * @see YamlOrderedMappingNode
     * @see #asOrderedMapping()
     */
    boolean isOrderedMapping();

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
     * Checks if this node is a {@code boolean} node.
     *
     * @return if this is a {@code boolean} node
     *
     * @see YamlBooleanNode
     * @see #booleanValue()
     */
    boolean isBoolean();

    /**
     * Checks if this node is a {@code null} node.
     *
     * @return if this is a {@code null} node
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
     * Checks if this node is integral node.
     *
     * @return if this is a integral node
     *
     * @see YamlIntegralNode
     * @see #bigIntegerValue()
     * @see #byteValue()
     * @see #shortValue()
     * @see #intValue()
     * @see #longValue()
     *
     */
    boolean isIntegral();

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
     * Converts this node into a {@link YamlMappingNode} if applicable.
     *
     * @return a mapping node or {@code null}
     *
     * @see #isMapping()
     * @see YamlMappingNode
     */
    YamlMappingNode asMapping();

    /**
     * Converts this node into a {@link YamlMappingNode} if applicable.
     *
     * @return a mapping node or {@code null}
     *
     * @see #isOrderedMapping()
     * @see YamlOrderedMappingNode
     */
    YamlOrderedMappingNode asOrderedMapping();

    /**
     * Converts this node into a {@link YamlPairsNode} if applicable.
     *
     * @return a pairs node or {@code null}
     *
     * @see #isPairs()
     * @see YamlPairsNode
     */
    YamlPairsNode asPairs();

    /**
     * Converts this node into a {@link YamlSequenceNode} if applicable.
     *
     * @return a sequence node or {@code null}
     *
     * @see #isSequence()
     * @see YamlSequenceNode
     */
    YamlSequenceNode asSequence();

    /**
     * Converts this node into a {@link YamlSetNode} if applicable.
     *
     * @return a set node or {@code null}
     *
     * @see #isSet()
     * @see YamlSetNode
     */
    YamlSetNode asSet();

    BigDecimal asBigDecimalValue();

    BigDecimal asBigDecimalValue(BigDecimal defaultValue);

    BigDecimal bigDecimalValue();

    BigInteger asBigIntegerValue();

    BigInteger asBigIntegerValue(BigInteger defaultValue);

    BigInteger bigIntegerValue();

    boolean asBooleanValue();

    boolean asBooleanValue(boolean defaultValue);

    boolean booleanValue();

    byte asByteValue();

    byte asByteValue(byte defaultValue);

    byte byteValue();

    byte[] asBinaryValue();

    byte[] asBinaryValue(byte[] defaultValue);

    byte[] binaryValue();

    double asDoubleValue();

    double asDoubleValue(double defaultValue);

    double doubleValue();

    float asFloatValue();

    float asFloatValue(float defaultValue);

    float floatValue();

    int asIntValue();

    int asIntValue(int defaultValue);

    int intValue();

    long asLongValue();

    long asLongValue(long defaultValue);

    long longValue();

    Number asNumberValue();

    Number asNumberValue(Number defaultValue);

    Number numberValue();

    short asShortValue();

    short asShortValue(short defaultValue);

    short shortValue();

    String asTextValue();

    String asTextValue(String defaultValue);

    String textValue();

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@link #DEFAULT_DATE_TIME_VALUE} if this node can not be converted to a
     * {@code DateTime}.
     *
     * @return the {@code DateTime} or {@link #DEFAULT_DATE_TIME_VALUE}
     */
    DateTime asDateTimeValue();

    /**
     * Tries to convert the value of this node to a {@link DateTime}. Returns
     * {@code defaultValue} if this node can not be converted to a
     * {@code DateTime}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@code DateTime} or {@code defaultValue}
     */
    DateTime asDateTimeValue(DateTime defaultValue);

    /**
     * Returns the {@link DateTime} value of this node or
     * {@link #DEFAULT_DATE_TIME_VALUE} if this is not a time node.
     *
     * @return the {@code DateTime} or {@link #DEFAULT_DATE_TIME_VALUE}
     *
     * @see #isTime()
     */
    DateTime dateTimeValue();

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@link #DEFAULT_DATE_VALUE} if this node can not be converted to a
     * {@code Date}.
     *
     * @return the {@code Date} or {@link #DEFAULT_DATE_VALUE}
     */
    Date asDateValue();

    /**
     * Tries to convert the value of this node to a {@link Date}. Returns
     * {@code defaultValue} if this node can not be converted to a {@code Date}.
     *
     * @param defaultValue the default value if this node can not be converted
     *
     * @return the {@code Date} or {@code defaultValue}
     */
    Date asDateValue(Date defaultValue);

    /**
     * Returns the {@link Date} value of this node or
     * {@link #DEFAULT_DATE_VALUE} if this is not a time node.
     *
     * @return the {@code Date} or {@link #DEFAULT_DATE_VALUE}
     *
     * @see #isTime()
     */
    Date dateValue();

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
     * equivalent to {@code yaml.dump(this)}.
     *
     * @param yaml the {@link Yaml}
     *
     * @return the string representation of this node
     */
    String dump(Yaml yaml);

    /**
     * Dumps this node to a writer using the supplied {@link Yaml}. This is
     * equivalent to {@code yaml.dump(this, output)}.
     *
     * @param output the writer
     * @param yaml   the {@link Yaml}
     */
    void dump(Writer output, Yaml yaml);

    /**
     * Dumps this node to a output stream using the supplied {@link Yaml}. This
     * is equivalent to {@code yaml.dump(this, output)}.
     *
     * @param output the output stream
     * @param yaml   the {@link Yaml}
     */
    void dump(OutputStream output, Yaml yaml);

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object o);
}
