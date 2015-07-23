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
package com.github.autermann.yaml.nodes;

import static com.github.autermann.yaml.nodes.YamlNodesMatcher.existingNode;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.yaml.snakeyaml.DumperOptions;

import com.github.autermann.yaml.DefaultYamlNodeFactory;
import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.Yaml;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.google.common.collect.Iterables;

/**
 * Abstract test class for {@link YamlNode}s.
 *
 * @author Christian Autermann
 */
public abstract class AbstractYamlNodeTest {
    /**
     * The default {@link YamlNodeFactory}.
     */
    public final DefaultYamlNodeFactory factory = DefaultYamlNodeFactory
            .create();
    /**
     * {@link ExpectedException} rule.
     */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    /**
     * {@link ErrorCollector} rule.
     */
    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    /**
     * Test for {@link YamlNode#toString() }.
     */
    @Test
    public abstract void testToString();

    /**
     * Test for {@link YamlNode#equals(java.lang.Object) }.
     */
    @Test
    public abstract void testEquals();

    /**
     * Test for {@link YamlNode#hashCode() }.
     */
    @Test
    public abstract void testHashCode();

    /**
     * Test for {@link YamlNode#tag() }.
     */
    @Test
    public abstract void testTag();

    /**
     * Creates a new node of the {@link YamlNode} to test (may be random).
     *
     * @return the new node
     */
    protected abstract YamlNode instance();

    /**
     * Creates a new {@link FailingReturningYamlNodeVisitor} that fails for all
     * nodes but this one.
     *
     * @return the visitor
     */
    protected abstract FailingReturningYamlNodeVisitor returningVisitor();

    /**
     * Creates a new {@link FailingYamlNodeVisitor} that fails for all nodes but
     * this one.
     *
     * @return the visitor
     */
    protected abstract FailingYamlNodeVisitor visitor();

    /**
     * Test for {@link YamlNode#accept(com.github.autermann.yaml.YamlNodeVisitor)
     * }.
     */
    @Test
    public void testAccept_YamlNodeVisitor() {
        FailingYamlNodeVisitor v = visitor();
        instance().accept(v);
        errors.checkThat(v.hasVisited(), is(true));
        thrown.expect(NullPointerException.class);
        YamlNullNode.instance().accept((YamlNodeVisitor) null);
    }

    /**
     * Test for {@link YamlNode#accept(com.github.autermann.yaml.ReturningYamlNodeVisitor)
     * }.
     */
    @Test
    public void testAccept_ReturningYamlNodeVisitor() {
        FailingReturningYamlNodeVisitor v = returningVisitor();
        instance().accept(v);
        assertThat(v.hasVisited(), is(true));
        thrown.expect(NullPointerException.class);
        YamlNullNode.instance().accept((ReturningYamlNodeVisitor<?>) null);
    }

    /**
     * Test for {@link YamlNode#copy() }.
     */
    @Test
    public void testCopy() {
        YamlNode node = instance();
        assertThat(node.copy(), is(node));
    }

    /**
     * Test for {@link YamlNode#asMap() }.
     */
    @Test
    public void testAsMap() {
        assertThat(instance().asMap(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asOrderedMap() }.
     */
    @Test
    public void testAsOrderedMap() {
        assertThat(instance().asOrderedMap(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asPairs() }.
     */
    @Test
    public void testAsPairs() {
        assertThat(instance().asPairs(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asSequence()}.
     */
    @Test
    public void testAsSequence() {
        assertThat(instance().asSequence(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asSet() }.
     */
    @Test
    public void testAsSet() {
        assertThat(instance().asSet(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#exists() }.
     */
    @Test
    public void testExists() {
        assertThat(instance().exists(), is(true));
    }

    /**
     * Test for {@link YamlNode#isContainer() }.
     */
    @Test
    public void testIsContainer() {
        assertThat(instance().isContainer(), is(false));
    }

    /**
     * Test for {@link YamlNode#isMap() }.
     */
    @Test
    public void testIsMap() {
        assertThat(instance().isMap(), is(false));
    }

    /**
     * Test for {@link YamlNode#isOrderedMap() }.
     */
    @Test
    public void testIsOrderedMap() {
        assertThat(instance().isOrderedMap(), is(false));
    }

    /**
     * Test for {@link YamlNode#isPairs() }.
     */
    @Test
    public void testIsPairs() {
        assertThat(instance().isPairs(), is(false));
    }

    /**
     * Test for {@link YamlNode#isSequence() }.
     */
    @Test
    public void testIsSequence() {
        assertThat(instance().isSequence(), is(false));
    }

    /**
     * Test for {@link YamlNode#isSet() }.
     */
    @Test
    public void testIsSet() {
        assertThat(instance().isSet(), is(false));
    }

    /**
     * Test for {@link YamlNode#isScalar() }.
     */
    @Test
    public void testIsScalar() {
        assertThat(instance().isScalar(), is(false));
    }

    /**
     * Test for {@link YamlNode#isBinary() }.
     */
    @Test
    public void testIsBinary() {
        assertThat(instance().isBinary(), is(false));
    }

    /**
     * Test for {@link YamlNode#isBoolean() }.
     */
    @Test
    public void testIsBoolean() {
        assertThat(instance().isBoolean(), is(false));
    }

    /**
     * Test for {@link YamlNode#isNull() }.
     */
    @Test
    public void testIsNull() {
        assertThat(instance().isNull(), is(false));
    }

    /**
     * Test for {@link YamlNode#isNumber() }.
     */
    @Test
    public void testIsNumber() {
        assertThat(instance().isNumber(), is(false));
    }

    /**
     * Test for {@link YamlNode#isDecimal() }.
     */
    @Test
    public void testIsDecimal() {
        assertThat(instance().isDecimal(), is(false));
    }

    /**
     * Test for {@link YamlNode#isFloat() }.
     */
    @Test
    public void testIsFloat() {
        assertThat(instance().isFloat(), is(false));
    }

    /**
     * Test for {@link YamlNode#isDouble() }.
     */
    @Test
    public void testIsDouble() {
        assertThat(instance().isDouble(), is(false));
    }

    /**
     * Test for {@link YamlNode#isBigDecimal() }.
     */
    @Test
    public void testIsBigDecimal() {
        assertThat(instance().isBigDecimal(), is(false));
    }

    /**
     * Test for {@link YamlNode#isIntegral() }.
     */
    @Test
    public void testIsIntegral() {
        assertThat(instance().isIntegral(), is(false));
    }

    /**
     * Test for {@link YamlNode#isBigInteger() }.
     */
    @Test
    public void testIsBigInteger() {
        assertThat(instance().isBigInteger(), is(false));
    }

    /**
     * Test for {@link YamlNode#isLong() }.
     */
    @Test
    public void testIsLong() {
        assertThat(instance().isLong(), is(false));
    }

    /**
     * Test for {@link YamlNode#isInt() }.
     */
    @Test
    public void testIsInt() {
        assertThat(instance().isInt(), is(false));
    }

    /**
     * Test for {@link YamlNode#isShort() }.
     */
    @Test
    public void testIsShort() {
        assertThat(instance().isShort(), is(false));
    }

    /**
     * Test for {@link YamlNode#isByte() }.
     */
    @Test
    public void testIsByte() {
        assertThat(instance().isByte(), is(false));
    }

    /**
     * Test for {@link YamlNode#isText() }.
     */
    @Test
    public void testIsText() {
        assertThat(instance().isText(), is(false));
    }

    /**
     * Test for {@link YamlNode#isTime() }.
     */
    @Test
    public void testIsTime() {
        assertThat(instance().isTime(), is(false));
    }

    /**
     * Test for {@link YamlNode#asBigDecimalValue() }.
     */
    @Test
    public void testAsBigDecimalValue_0args() {
        assertThat(instance().asBigDecimalValue(), is(BigDecimal.ZERO));
    }

    /**
     * Test for {@link YamlNode#asBigDecimalValue(java.math.BigDecimal) }.
     */
    @Test
    public void testAsBigDecimalValue_BigDecimal() {
        errors.checkThat(instance().asBigDecimalValue(null), is(nullValue()));
        errors
                .checkThat(instance().asBigDecimalValue(BigDecimal.ONE), is(BigDecimal.ONE));
    }

    /**
     * Test for {@link YamlNode#bigDecimalValue() }.
     */
    @Test
    public void testBigDecimalValue() {
        assertThat(instance().bigDecimalValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asBigIntegerValue() }.
     */
    @Test
    public void testAsBigIntegerValue_0args() {
        assertThat(instance().asBigIntegerValue(), is(BigInteger.ZERO));
    }

    /**
     * Test for {@link YamlNode#asBigIntegerValue(java.math.BigInteger) }.
     */
    @Test
    public void testAsBigIntegerValue_BigInteger() {
        errors.checkThat(instance().asBigIntegerValue(null), is(nullValue()));
        errors.checkThat(instance().asBigIntegerValue(BigInteger.ONE), is(BigInteger.ONE));
    }

    /**
     * Test for {@link YamlNode#bigIntegerValue() }.
     */
    @Test
    public void testBigIntegerValue() {
        assertThat(instance().bigIntegerValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asBooleanValue() }.
     */
    @Test
    public void testAsBooleanValue_0args() {
        assertThat(instance().asBooleanValue(), is(false));
    }

    /**
     * Test for {@link YamlNode#asBooleanValue(boolean) }.
     */
    @Test
    public void testAsBooleanValue_boolean() {
        errors.checkThat(instance().asBooleanValue(true), is(true));
        errors.checkThat(instance().asBooleanValue(false), is(false));
    }

    /**
     * Test for {@link YamlNode#booleanValue() }.
     */
    @Test
    public void testBooleanValue() {
        assertThat(instance().booleanValue(), is(false));
    }

    /**
     * Test for {@link YamlNode#asByteValue() }.
     */
    @Test
    public void testAsByteValue_0args() {
        assertThat(instance().asByteValue(), is((byte) 0));
    }

    /**
     * Test for {@link YamlNode#asByteValue(byte) }.
     */
    @Test
    public void testAsByteValue_byte() {
        errors.checkThat(instance().asByteValue((byte) 0), is((byte) 0));
        errors.checkThat(instance().asByteValue((byte) -1), is((byte) -1));
        errors.checkThat(instance().asByteValue((byte) 1), is((byte) 1));
        errors.checkThat(instance().asByteValue(Byte.MIN_VALUE), is(Byte.MIN_VALUE));
        errors.checkThat(instance().asByteValue(Byte.MAX_VALUE), is(Byte.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#byteValue() }.
     */
    @Test
    public void testByteValue() {
        assertThat(instance().byteValue(), is((byte) 0));
    }

    /**
     * Test for {@link YamlNode#asBinaryValue() }.
     */
    @Test
    public void testAsBinaryValue_0args() {
        assertThat(instance().asBinaryValue(), is(new byte[0]));
    }

    /**
     * Test for {@link YamlNode#asBinaryValue(byte[]) }.
     */
    @Test
    public void testAsBinaryValue_byteArr() {
        assertThat(instance().asBinaryValue(null), is(nullValue()));
        assertThat(instance().asBinaryValue(new byte[2]), is(new byte[2]));
        assertThat(instance().asBinaryValue(new byte[] { 1, 2, 3 }),
                   is(new byte[] { 1, 2, 3 }));
    }

    /**
     * Test for {@link YamlNode#binaryValue() }.
     */
    @Test
    public void testBinaryValue() {
        assertThat(instance().binaryValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#doubleValue() }.
     */
    @Test
    public void testAsDoubleValue_0args() {
        assertThat(instance().doubleValue(), is(0.0d));
    }

    /**
     * Test for {@link YamlNode#asDoubleValue(double) }.
     */
    @Test
    public void testAsDoubleValue_double() {
        errors.checkThat(instance().asDoubleValue(0.0d), is(0.0d));
        errors.checkThat(instance().asDoubleValue(-1.0d), is(-1.0d));
        errors.checkThat(instance().asDoubleValue(1.0d), is(1.0d));
        errors.checkThat(instance().asDoubleValue(Double.MIN_VALUE),
                         is(Double.MIN_VALUE));
        errors.checkThat(instance().asDoubleValue(Double.MAX_VALUE),
                         is(Double.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#doubleValue() }.
     */
    @Test
    public void testDoubleValue() {
        assertThat(instance().doubleValue(), is(0.0d));
    }

    /**
     * Test for {@link YamlNode#floatValue() }.
     */
    @Test
    public void testAsFloatValue_0args() {
        assertThat(instance().asFloatValue(), is(0.0f));
    }

    /**
     * Test for {@link YamlNode#asFloatValue(float) }.
     */
    @Test
    public void testAsFloatValue_float() {
        errors.checkThat(instance().asFloatValue(0.0f), is(0.0f));
        errors.checkThat(instance().asFloatValue(-1.0f), is(-1.0f));
        errors.checkThat(instance().asFloatValue(1.0f), is(1.0f));
        errors.checkThat(instance().asFloatValue(Float.MIN_VALUE),
                         is(Float.MIN_VALUE));
        errors.checkThat(instance().asFloatValue(Float.MAX_VALUE),
                         is(Float.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#floatValue() }.
     */
    @Test
    public void testFloatValue() {
        assertThat(instance().floatValue(), is(0.0f));
    }

    /**
     * Test for {@link YamlNode#asIntValue() }.
     */
    @Test
    public void testAsIntValue_0args() {
        errors.checkThat(instance().asIntValue(), is(0));
    }

    /**
     * Test for {@link YamlNode#asIntValue(int) }.
     */
    @Test
    public void testAsIntValue_int() {
        errors.checkThat(instance().asIntValue(0), is(0));
        errors.checkThat(instance().asIntValue(-1), is(-1));
        errors.checkThat(instance().asIntValue(1), is(1));
        errors.checkThat(instance().asIntValue(Integer.MIN_VALUE),
                         is(Integer.MIN_VALUE));
        errors.checkThat(instance().asIntValue(Integer.MAX_VALUE),
                         is(Integer.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#intValue() }.
     */
    @Test
    public void testIntValue() {
        assertThat(instance().intValue(), is(0));
    }

    /**
     * Test for {@link YamlNode#asLongValue() }.
     */
    @Test
    public void testAsLongValue_0args() {
        assertThat(instance().asLongValue(), is(0l));
    }

    /**
     * Test for {@link YamlNode#asLongValue(long) }.
     */
    @Test
    public void testAsLongValue_long() {
        errors.checkThat(instance().asLongValue((long) 0), is((long) 0));
        errors.checkThat(instance().asLongValue((long) -1), is((long) -1));
        errors.checkThat(instance().asLongValue((long) 1), is((long) 1));
        errors.checkThat(instance().asLongValue(Long.MIN_VALUE),
                         is(Long.MIN_VALUE));
        errors.checkThat(instance().asLongValue(Long.MAX_VALUE),
                         is(Long.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#longValue() }.
     */
    @Test
    public void testLongValue() {
        assertThat(instance().longValue(), is(0l));
    }

    /**
     * Test for {@link YamlNode#asNumberValue() }.
     */
    @Test
    public void testAsNumberValue_0args() {
        assertThat(instance().asNumberValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asNumberValue(java.lang.Number) }.
     */
    @Test
    public void testAsNumberValue_Number() {
        errors.checkThat(instance().asNumberValue(0), is((Number) 0));
        errors.checkThat(instance().asNumberValue(-1), is((Number) (-1)));
        errors.checkThat(instance().asNumberValue(1), is((Number) 1));
        errors.checkThat(instance().asNumberValue(0.0d), is((Number) 0.0d));
        errors.checkThat(instance().asNumberValue(null), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#numberValue() }.
     */
    @Test
    public void testNumberValue() {
        assertThat(instance().numberValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asShortValue() }.
     */
    @Test
    public void testAsShortValue_0args() {
        assertThat(instance().asShortValue(), is((short) 0));
    }

    /**
     * Test for {@link YamlNode#asShortValue(short) }.
     */
    @Test
    public void testAsShortValue_short() {
        errors.checkThat(instance().asShortValue((short) 0), is((short) 0));
        errors.checkThat(instance().asShortValue((short) -1), is((short) -1));
        errors.checkThat(instance().asShortValue((short) 1), is((short) 1));
        errors.checkThat(instance().asShortValue(Short.MIN_VALUE),
                         is(Short.MIN_VALUE));
        errors.checkThat(instance().asShortValue(Short.MAX_VALUE),
                         is(Short.MAX_VALUE));
    }

    /**
     * Test for {@link YamlNode#shortValue() }.
     */
    @Test
    public void testShortValue() {
        assertThat(instance().shortValue(), is((short) 0));
    }

    /**
     * Test for {@link YamlNode#asTextValue() }.
     */
    @Test
    public void testAsTextValue_0args() {
        assertThat(instance().asTextValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asTextValue(java.lang.String) }.
     */
    @Test
    public void testAsTextValue_String() {
        errors.checkThat(instance().asTextValue("asdf"), is("asdf"));
        errors.checkThat(instance().asTextValue(""), is(""));
        errors.checkThat(instance().asTextValue(null), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#textValue() }.
     */
    @Test
    public void testTextValue() {
        assertThat(instance().textValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asDateTimeValue() }.
     */
    @Test
    public void testAsDateTimeValue_0args() {
        assertThat(instance().asDateTimeValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asDateTimeValue(org.joda.time.DateTime) }.
     */
    @Test
    public void testAsDateTimeValue_DateTime() {
        DateTime d = DateTime.now();
        errors.checkThat(instance().asDateTimeValue(d), is(d));
        errors.checkThat(instance().asDateTimeValue(null), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#dateTimeValue() }.
     */
    @Test
    public void testDateTimeValue() {
        assertThat(instance().dateTimeValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asDateValue() }.
     */
    @Test
    public void testAsDateValue_0args() {
        assertThat(instance().asDateValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#asDateValue(java.util.Date) }.
     */
    @Test
    public void testAsDateValue_Date() {
        Date d = new Date();
        errors.checkThat(instance().asDateValue(d), is(d));
        errors.checkThat(instance().asDateValue(null), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#dateValue() }.
     */
    @Test
    public void testDateValue() {
        assertThat(instance().dateValue(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#dump() }.
     */
    @Test
    public void testDump_0args() {
        YamlNode i = instance();
        assertThat(i.dump(), is(new Yaml().dump(i)));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.Writer) }.
     */
    @Test
    public void testDump_Writer() {
        StringWriter a = new StringWriter();
        StringWriter b = new StringWriter();
        YamlNode i = instance();
        i.dump(a);
        new Yaml().dump(i, b);
        assertThat(a.toString(), is(b.toString()));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.OutputStream) }.
     */
    @Test
    public void testDump_OutputStream() {
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        YamlNode i = instance();
        i.dump(a);
        new Yaml().dump(i, b);
        assertThat(a.toByteArray(), is(b.toByteArray()));
    }

    /**
     * Test for {@link YamlNode#dump(org.yaml.snakeyaml.DumperOptions) }.
     */
    @Test
    public void testDump_DumperOptions() {
        YamlNode i = instance();
        DumperOptions options = new DumperOptions();
        assertThat(i.dump(options), is(new Yaml(options).dump(i)));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.Writer, org.yaml.snakeyaml.DumperOptions)
     * }.
     * }
     */
    @Test
    public void testDump_Writer_DumperOptions() {
        StringWriter a = new StringWriter();
        StringWriter b = new StringWriter();
        YamlNode i = instance();
        DumperOptions options = new DumperOptions();
        i.dump(a, options);
        new Yaml(options).dump(i, b);
        assertThat(a.toString(), is(b.toString()));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.OutputStream, org.yaml.snakeyaml.DumperOptions)
     * }.
     * }
     */
    @Test
    public void testDump_OutputStream_DumperOptions() {
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        YamlNode i = instance();
        DumperOptions options = new DumperOptions();
        i.dump(a, options);
        new Yaml(options).dump(i, b);
        assertThat(a.toByteArray(), is(b.toByteArray()));
    }

    /**
     * Test for {@link YamlNode#dump(com.github.autermann.yaml.Yaml) }.
     */
    @Test
    public void testDump_Yaml() {
        YamlNode i = instance();
        Yaml yaml = new Yaml();
        assertThat(i.dump(yaml), is(yaml.dump(i)));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.Writer, com.github.autermann.yaml.Yaml)
     * }.
     * }
     */
    @Test
    public void testDump_Writer_Yaml() {
        StringWriter a = new StringWriter();
        StringWriter b = new StringWriter();
        YamlNode i = instance();
        Yaml yaml = new Yaml();
        i.dump(a, yaml);
        yaml.dump(i, b);
        assertThat(a.toString(), is(b.toString()));
    }

    /**
     * Test for {@link YamlNode#dump(java.io.OutputStream, com.github.autermann.yaml.Yaml)
     * }.
     * }
     */
    @Test
    public void testDump_OutputStream_Yaml() {
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        YamlNode i = instance();
        Yaml yaml = new Yaml();
        i.dump(a, yaml);
        yaml.dump(i, b);
        assertThat(a.toByteArray(), is(b.toByteArray()));
    }

    /**
     * Test for {@link YamlNode#isEmpty() }.
     */
    @Test
    public void testIsEmpty() {
        assertThat(instance().isEmpty(), is(true));
    }

    /**
     * Test for {@link YamlNode#size() }.
     */
    @Test
    public void testSize() {
        assertThat(instance().size(), is(0));
    }

    /**
     * Test for {@link YamlNode#path(int) }.
     */
    @Test
    public void testPath_int() {
        errors.checkThat(instance().path(-1), is(not(existingNode())));
        errors.checkThat(instance().path(0), is(not(existingNode())));
        errors.checkThat(instance().path(1), is(not(existingNode())));
        errors.checkThat(instance().path(Integer.MIN_VALUE),
                         is(not(existingNode())));
        errors.checkThat(instance().path(Integer.MAX_VALUE),
                         is(not(existingNode())));
    }

    /**
     * Test for {@link YamlNode#get(int) }.
     */
    @Test
    public void testGet_int() {
        errors.checkThat(instance().get(-1), is(nullValue()));
        errors.checkThat(instance().get(0), is(nullValue()));
        errors.checkThat(instance().get(1), is(nullValue()));
        errors.checkThat(instance().get(Integer.MIN_VALUE), is(nullValue()));
        errors.checkThat(instance().get(Integer.MAX_VALUE), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#get(java.lang.String) }.
     */
    @Test
    public void testGet_String() {
        errors.checkThat(instance().get(""), is(nullValue()));
        errors.checkThat(instance().get((String) null), is(nullValue()));
        errors.checkThat(instance().get("test"), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#get(com.github.autermann.yaml.YamlNode) }.
     */
    @Test
    public void testGet_YamlNode() {
        YamlNodeFactory f = YamlNodeFactory.createDefault();
        errors.checkThat(instance().get(f.bigDecimalNode(BigDecimal.ZERO)),
                         is(nullValue()));
        errors.checkThat(instance().get(f.bigIntegerNode(BigInteger.ZERO)),
                         is(nullValue()));
        errors.checkThat(instance().get(f.binaryNode(new byte[0])),
                         is(nullValue()));
        errors.checkThat(instance().get(f.booleanNode(true)), is(nullValue()));
        errors.checkThat(instance().get(f.byteNode((byte) 0)), is(nullValue()));
        errors.checkThat(instance().get(f.dateTimeNode(new Date())),
                         is(nullValue()));
        errors.checkThat(instance().get(f.dateTimeNode(DateTime.now())),
                         is(nullValue()));
        errors.checkThat(instance().get(f.doubleNode(0)), is(nullValue()));
        errors.checkThat(instance().get(f.floatNode(0)), is(nullValue()));
        errors.checkThat(instance().get(f.intNode(0)), is(nullValue()));
        errors.checkThat(instance().get(f.longNode(0)), is(nullValue()));
        errors.checkThat(instance().get(f.mapNode()), is(nullValue()));
        errors.checkThat(instance().get(f.nullNode()), is(nullValue()));
        errors.checkThat(instance().get(f.orderedMapNode()), is(nullValue()));
        errors.checkThat(instance().get(f.pairsNode()), is(nullValue()));
        errors.checkThat(instance().get(f.sequenceNode()), is(nullValue()));
        errors.checkThat(instance().get(f.setNode()), is(nullValue()));
        errors.checkThat(instance().get(f.shortNode((short) 0)),
                         is(nullValue()));
        errors.checkThat(instance().get(f.textNode("")), is(nullValue()));
        errors.checkThat(instance().get(f.textNode("asdf")), is(nullValue()));
    }

    /**
     * Test for {@link YamlNode#path(java.lang.String) }.
     */
    @Test
    public void testPath_String() {
        errors.checkThat(instance().path(""), is(not(existingNode())));
        errors.checkThat(instance().path((String) null),
                         is(not(existingNode())));
        errors.checkThat(instance().path("test"), is(not(existingNode())));
    }

    /**
     * Test for {@link YamlNode#path(com.github.autermann.yaml.YamlNode) }.
     */
    @Test
    public void testPath_YamlNode() {
        YamlNodeFactory f = YamlNodeFactory.createDefault();
        errors.checkThat(instance().path(f.bigDecimalNode(BigDecimal.ZERO)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.bigIntegerNode(BigInteger.ZERO)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.binaryNode(new byte[0])),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.booleanNode(true)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.byteNode((byte) 0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.dateTimeNode(new Date())),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.dateTimeNode(DateTime.now())),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.doubleNode(0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.floatNode(0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.intNode(0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.longNode(0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.mapNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.nullNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.orderedMapNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.pairsNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.sequenceNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.setNode()),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.shortNode((short) 0)),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.textNode("")),
                         is(not(existingNode())));
        errors.checkThat(instance().path(f.textNode("asdf")),
                         is(not(existingNode())));
    }

    /**
     * Test for {@link YamlNode#has(int) }.
     */
    @Test
    public void testHas_int() {
        errors.checkThat(instance().has(-1), is(false));
        errors.checkThat(instance().has(0), is(false));
        errors.checkThat(instance().has(1), is(false));
        errors.checkThat(instance().has(Integer.MIN_VALUE), is(false));
        errors.checkThat(instance().has(Integer.MAX_VALUE), is(false));
    }

    /**
     * Test for {@link YamlNode#has(java.lang.String) }.
     */
    @Test
    public void testHas_String() {
        errors.checkThat(instance().has(""), is(false));
        errors.checkThat(instance().has((String) null), is(false));
        errors.checkThat(instance().has("test"), is(false));
    }

    /**
     * Test for {@link YamlNode#has(com.github.autermann.yaml.YamlNode) }.
     */
    @Test
    public void testHas_YamlNode() {
        YamlNodeFactory f = YamlNodeFactory.createDefault();
        errors.checkThat(instance().has(f.bigDecimalNode(BigDecimal.ZERO)),
                         is(false));
        errors.checkThat(instance().has(f.bigIntegerNode(BigInteger.ZERO)),
                         is(false));
        errors.checkThat(instance().has(f.binaryNode(new byte[0])), is(false));
        errors.checkThat(instance().has(f.booleanNode(true)), is(false));
        errors.checkThat(instance().has(f.byteNode((byte) 0)), is(false));
        errors.checkThat(instance().has(f.dateTimeNode(new Date())), is(false));
        errors.checkThat(instance().has(f.dateTimeNode(DateTime.now())),
                         is(false));
        errors.checkThat(instance().has(f.doubleNode(0)), is(false));
        errors.checkThat(instance().has(f.floatNode(0)), is(false));
        errors.checkThat(instance().has(f.intNode(0)), is(false));
        errors.checkThat(instance().has(f.longNode(0)), is(false));
        errors.checkThat(instance().has(f.mapNode()), is(false));
        errors.checkThat(instance().has(f.nullNode()), is(false));
        errors.checkThat(instance().has(f.orderedMapNode()), is(false));
        errors.checkThat(instance().has(f.pairsNode()), is(false));
        errors.checkThat(instance().has(f.sequenceNode()), is(false));
        errors.checkThat(instance().has(f.setNode()), is(false));
        errors.checkThat(instance().has(f.shortNode((short) 0)), is(false));
        errors.checkThat(instance().has(f.textNode("")), is(false));
        errors.checkThat(instance().has(f.textNode("asdf")), is(false));
    }

    /**
     * Test for {@link YamlNode#hasNotNull(int) }.
     */
    @Test
    public void testHasNotNull_int() {
        errors.checkThat(instance().hasNotNull(-1), is(false));
        errors.checkThat(instance().hasNotNull(0), is(false));
        errors.checkThat(instance().hasNotNull(1), is(false));
        errors.checkThat(instance().hasNotNull(Integer.MIN_VALUE), is(false));
        errors.checkThat(instance().hasNotNull(Integer.MAX_VALUE), is(false));
    }

    /**
     * Test for {@link YamlNode#hasNotNull(java.lang.String) }.
     */
    @Test
    public void testHasNotNull_String() {
        errors.checkThat(instance().hasNotNull(""), is(false));
        errors.checkThat(instance().hasNotNull((String) null), is(false));
        errors.checkThat(instance().hasNotNull("test"), is(false));
    }

    /**
     * Test for {@link YamlNode#hasNotNull(com.github.autermann.yaml.YamlNode)
     * }.
     */
    @Test
    public void testHasNotNull_YamlNode() {
        YamlNodeFactory f = YamlNodeFactory.createDefault();
        errors.checkThat(instance()
                .hasNotNull(f.bigDecimalNode(BigDecimal.ZERO)),
                         is(false));
        errors.checkThat(instance()
                .hasNotNull(f.bigIntegerNode(BigInteger.ZERO)), is(false));
        errors.checkThat(instance().hasNotNull(f.binaryNode(new byte[0])),
                         is(false));
        errors.checkThat(instance().hasNotNull(f.booleanNode(true)), is(false));
        errors.checkThat(instance().hasNotNull(f.byteNode((byte) 0)),
                         is(false));
        errors.checkThat(instance().hasNotNull(f.dateTimeNode(new Date())),
                         is(false));
        errors.checkThat(instance().hasNotNull(f.dateTimeNode(DateTime.now())),
                         is(false));
        errors.checkThat(instance().hasNotNull(f.doubleNode(0)), is(false));
        errors.checkThat(instance().hasNotNull(f.floatNode(0)), is(false));
        errors.checkThat(instance().hasNotNull(f.intNode(0)), is(false));
        errors.checkThat(instance().hasNotNull(f.longNode(0)), is(false));
        errors.checkThat(instance().hasNotNull(f.mapNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.nullNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.orderedMapNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.pairsNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.sequenceNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.setNode()), is(false));
        errors.checkThat(instance().hasNotNull(f.shortNode((short) 0)),
                         is(false));
        errors.checkThat(instance().hasNotNull(f.textNode("")), is(false));
        errors.checkThat(instance().hasNotNull(f.textNode("asdf")), is(false));
    }

    /**
     * Test for {@link YamlNode#iterator() }.
     */
    @Test
    public void testIterator() {
        YamlNode n = instance();
        errors.checkThat(Iterables.size(n), is(1));
        errors.checkThat(Iterables.getOnlyElement(n), is(n));
    }
}
