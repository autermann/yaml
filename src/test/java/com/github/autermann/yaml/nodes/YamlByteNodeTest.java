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
package com.github.autermann.yaml.nodes;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlByteNode}s.
 *
 * @author Christian Autermann
 */
public class YamlByteNodeTest extends AbstractYamlScalarNodeTest {

    @Override
    public void testToString() {
        YamlByteNode instance = instance();
        errors.checkThat(instance.toString(),
                         is(Byte.valueOf(instance.byteValue()).toString()));
    }

    @Override
    public void testEquals() {
        byte b = randomByte();
        errors.checkThat(new YamlByteNode(b), is(new YamlByteNode(b)));
        errors.checkThat(new YamlByteNode(b),
                         is(not(new YamlByteNode(another(b)))));
        errors.checkThat((YamlNode) new YamlByteNode(b),
                         is(not((YamlNode) YamlNullNode.instance())));
    }

    @Override
    public void testHashCode() {
        YamlByteNode instance = instance();
        errors.checkThat(instance.hashCode(),
                         is(Byte.valueOf(instance.byteValue()).hashCode()));
    }

    @Override
    public void testTag() {
        errors.checkThat(instance().tag(), is(Tag.INT));
    }

    @Override
    protected YamlByteNode instance() {
        return new YamlByteNode(randomByte());
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlIntegralNode node) {
                return hasVisited(true);
            }
        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlIntegralNode node) {
                hasVisited(true);
            }

        };
    }

    @Override
    public void testIsByte() {
        errors.checkThat(instance().isByte(), is(true));
    }

    @Override
    public void testIsIntegral() {
        errors.checkThat(instance().isIntegral(), is(true));
    }

    @Override
    public void testIsShort() {
        errors.checkThat(instance().isShort(), is(true));
    }

    @Override
    public void testIsInt() {
        errors.checkThat(instance().isInt(), is(true));
    }

    @Override
    public void testIsLong() {
        errors.checkThat(instance().isLong(), is(true));
    }

    @Override
    public void testIsBigInteger() {
        errors.checkThat(instance().isBigInteger(), is(true));
    }

    @Override
    public void testIsScalar() {
        errors.checkThat(instance().isScalar(), is(true));
    }

    @Override
    public void testIsNumber() {
        errors.checkThat(instance().isNumber(), is(true));
    }

    @Override
    public void testByteValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.byteValue(), is(b));
    }

    @Override
    public void testShortValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.shortValue(), is((short) b));
    }

    @Override
    public void testIntValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.intValue(), is((int) b));
    }

    @Override
    public void testLongValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.longValue(), is((long) b));
    }

    @Override
    public void testBigIntegerValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.bigIntegerValue(),
                         is(BigInteger.valueOf((long) b)));
    }

    @Override
    public void testAsByteValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asByteValue(), is(b));
    }

    @Override
    public void testAsByteValue_byte() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asByteValue((byte) 0), is(b));
        errors.checkThat(node.asByteValue((byte) 1), is(b));
        errors.checkThat(node.asByteValue((byte) -1), is(b));
    }

    @Override
    public void testAsShortValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asShortValue(), is((short) b));
    }

    @Override
    public void testAsShortValue_short() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asShortValue((short) 0), is((short) b));
        errors.checkThat(node.asShortValue((short) 1), is((short) b));
        errors.checkThat(node.asShortValue((short) -1), is((short) b));
    }

    @Override
    public void testAsIntValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asIntValue(), is((int) b));
    }

    @Override
    public void testAsIntValue_int() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asIntValue(0), is((int) b));
        errors.checkThat(node.asIntValue(1), is((int) b));
        errors.checkThat(node.asIntValue(-1), is((int) b));
    }

    @Override
    public void testAsLongValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asLongValue(), is((long) b));
    }

    @Override
    public void testAsLongValue_long() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asLongValue(0), is((long) b));
        errors.checkThat(node.asLongValue(1), is((long) b));
        errors.checkThat(node.asLongValue(-1), is((long) b));
    }

    @Override
    public void testAsBigIntegerValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asBigIntegerValue(),
                         is(BigInteger.valueOf((long) b)));
    }

    @Override
    public void testAsBigIntegerValue_BigInteger() {
         byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        BigInteger v = BigInteger.valueOf((long) b);
        errors.checkThat(node.asBigIntegerValue(BigInteger.ONE), is(v));
        errors.checkThat(node.asBigIntegerValue(BigInteger.ZERO), is(v));
        errors.checkThat(node.asBigIntegerValue(BigInteger.TEN), is(v));
    }



    @Override
    public void testAsFloatValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asFloatValue(), is((float) b));
    }

    @Override
    public void testAsFloatValue_float() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asFloatValue(0.0f), is((float) b));
        errors.checkThat(node.asFloatValue(1.0f), is((float) b));
        errors.checkThat(node.asFloatValue(-1.0f), is((float) b));
    }

    @Override
    public void testAsDoubleValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asDoubleValue(), is((double) b));
    }

    @Override
    public void testAsDoubleValue_double() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asDoubleValue(0.0d), is((double) b));
        errors.checkThat(node.asDoubleValue(1.0d), is((double) b));
        errors.checkThat(node.asDoubleValue(-1.0d), is((double) b));
    }

    @Override
    public void testAsNumberValue_0args() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asNumberValue(), is((Number) b));
    }

    @Override
    public void testAsNumberValue_Number() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.asNumberValue((Number) 0), is((Number) b));
        errors.checkThat(node.asNumberValue((Number) 1), is((Number) b));
        errors.checkThat(node.asNumberValue((Number) (-1)), is((Number) b));
    }

    @Override
    public void testNumberValue() {
        byte b = randomByte();
        YamlNode node = new YamlByteNode(b);
        errors.checkThat(node.numberValue(), is((Number) b));
    }

    @Override
    public void testAsDateTimeValue_0args() {
        YamlByteNode instance = instance();
        errors.checkThat(instance.asDateTimeValue(),
                         is(new DateTime(instance.asLongValue())));
    }

    @Override
    public void testAsDateTimeValue_DateTime() {
        YamlByteNode instance = instance();
        DateTime v = new DateTime(instance.asLongValue());
        errors.checkThat(instance.asDateTimeValue(null), is(v));
        errors.checkThat(instance.asDateTimeValue(DateTime.now()), is(v));
    }


    @Override
    public void testAsDateValue_0args() {
        YamlByteNode instance = instance();
        errors.checkThat(instance.asDateValue(),
                         is(new Date(instance.asLongValue())));
    }

    @Override
    public void testAsDateValue_Date() {
        YamlByteNode instance = instance();
        Date v = new Date(instance.asLongValue());
        errors.checkThat(instance.asDateValue(new Date()), is(v));
        errors.checkThat(instance.asDateValue(null), is(v));
    }

    @Override
    public void testAsTextValue_0args() {
        YamlByteNode instance = instance();
        errors.checkThat(instance.asTextValue(),
                         is(String.valueOf(instance.byteValue())));
    }

    @Override
    public void testAsTextValue_String() {
        YamlByteNode instance = instance();
        String v = String.valueOf(instance.byteValue());
        errors.checkThat(instance.asTextValue(""), is(v));
        errors.checkThat(instance.asTextValue(null), is(v));
        errors.checkThat(instance.asTextValue("asdf"), is(v));
    }

    @Override
    public void testValue() {
        YamlByteNode node = instance();
        errors.checkThat(node.value(), is(node.byteValue()));
    }
}
