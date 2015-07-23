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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Tests for {@code false} {@link YamlBooleanNode}s.
 *
 * @author Christian Autermann
 */
public class YamlBooleanFalseNodeTest extends YamlBooleanNodeTest{
  @Override
    public void testToString() {
        errors.checkThat(instance().toString(), is("false"));
    }

    @Override
    public void testEquals() {
        errors.checkThat(instance(), is(instance()));
        errors.checkThat(instance(), is(not(YamlBooleanNode.of(true))));
    }

    @Override
    protected YamlBooleanNode instance() {
        return YamlBooleanNode.of(false);
    }

    @Override
    public void testBooleanValue() {
        errors.checkThat(instance().booleanValue(), is(false));
    }

    @Override
    public void testAsByteValue_0args() {
        errors.checkThat(instance().asByteValue(), is((byte) 0));
    }

    @Override
    public void testAsByteValue_byte() {
        errors.checkThat(instance().asByteValue((byte) 1), is((byte) 0));
        errors.checkThat(instance().asByteValue((byte) -1), is((byte) 0));
        errors.checkThat(instance().asByteValue((byte) 0), is((byte) 0));
    }

    @Override
    public void testAsBigDecimalValue_0args() {
        errors.checkThat(instance().asBigDecimalValue(), is(BigDecimal.ZERO));
    }

    @Override
    public void testAsBigDecimalValue_BigDecimal() {
        errors.checkThat(instance().asBigDecimalValue(BigDecimal.ONE),
                         is(BigDecimal.ZERO));
        errors.checkThat(instance().asBigDecimalValue(BigDecimal.ZERO),
                         is(BigDecimal.ZERO));
        errors.checkThat(instance().asBigDecimalValue(BigDecimal.TEN),
                         is(BigDecimal.ZERO));
        errors.checkThat(instance().asBigDecimalValue(null),
                         is(BigDecimal.ZERO));
    }

    @Override
    public void testAsLongValue_0args() {
        errors.checkThat(instance().asLongValue(), is(0L));
    }

    @Override
    public void testAsLongValue_long() {
        errors.checkThat(instance().asLongValue(1L), is(0L));
        errors.checkThat(instance().asLongValue(-1L), is(0L));
        errors.checkThat(instance().asLongValue(0L), is(0L));
    }

    @Override
    public void testAsBigIntegerValue_0args() {
        errors.checkThat(instance().asBigIntegerValue(), is(BigInteger.ZERO));
    }

    @Override
    public void testAsBigIntegerValue_BigInteger() {
        errors.checkThat(instance().asBigIntegerValue(BigInteger.ONE),
                         is(BigInteger.ZERO));
        errors.checkThat(instance().asBigIntegerValue(BigInteger.ZERO),
                         is(BigInteger.ZERO));
        errors.checkThat(instance().asBigIntegerValue(BigInteger.TEN),
                         is(BigInteger.ZERO));
        errors.checkThat(instance().asBigIntegerValue(null),
                         is(BigInteger.ZERO));
    }

    @Override
    public void testAsShortValue_0args() {
        errors.checkThat(instance().asShortValue(), is((short) 0));
    }

    @Override
    public void testAsShortValue_short() {
        errors.checkThat(instance().asShortValue((short) 1), is((short) 0));
        errors.checkThat(instance().asShortValue((short) -1), is((short) 0));
        errors.checkThat(instance().asShortValue((short) 0), is((short) 0));
    }

    @Override
    public void testAsFloatValue_0args() {
        errors.checkThat(instance().asFloatValue(), is(0.0f));
    }

    @Override
    public void testAsFloatValue_float() {
        errors.checkThat(instance().asFloatValue(0.0f), is(0.0f));
        errors.checkThat(instance().asFloatValue(1.0f), is(0.0f));
        errors.checkThat(instance().asFloatValue(-1.0f), is(0.0f));
    }

    @Override
    public void testAsDoubleValue_0args() {
        errors.checkThat(instance().asDoubleValue(), is(0.0d));
    }

    @Override
    public void testAsDoubleValue_double() {
        errors.checkThat(instance().asDoubleValue(0.0d), is(0.0d));
        errors.checkThat(instance().asDoubleValue(1.0d), is(0.0d));
        errors.checkThat(instance().asDoubleValue(-1.0d), is(0.0d));
    }

    @Override
    public void testAsBooleanValue_0args() {
        errors.checkThat(instance().asBooleanValue(), is(false));
    }

    @Override
    public void testAsBooleanValue_boolean() {
        errors.checkThat(instance().asBooleanValue(true), is(false));
        errors.checkThat(instance().asBooleanValue(false), is(false));
    }

    @Override
    public void testAsIntValue_0args() {
        errors.checkThat(instance().asIntValue(), is(0));
    }

    @Override
    public void testAsIntValue_int() {
        errors.checkThat(instance().asIntValue(-1), is(0));
        errors.checkThat(instance().asIntValue(0), is(0));
        errors.checkThat(instance().asIntValue(1), is(0));
    }

    @Override
    public void testAsTextValue_0args() {
        errors.checkThat(instance().asTextValue(), is("false"));
    }

    @Override
    public void testAsTextValue_String() {
        errors.checkThat(instance().asTextValue("false"), is("false"));
        errors.checkThat(instance().asTextValue("true"), is("false"));
        errors.checkThat(instance().asTextValue("asdf"), is("false"));
        errors.checkThat(instance().asTextValue(""), is("false"));
        errors.checkThat(instance().asTextValue(null), is("false"));
    }

    @Override
    public void testAsNumberValue_0args() {
        errors.checkThat(instance().asNumberValue(), is((Number) 0));
    }


    @Override
    public void testAsNumberValue_Number() {
        errors.checkThat(instance().asNumberValue(-1), is((Number) 0));
        errors.checkThat(instance().asNumberValue(0), is((Number) 0));
        errors.checkThat(instance().asNumberValue(1), is((Number) 0));
    }
}
