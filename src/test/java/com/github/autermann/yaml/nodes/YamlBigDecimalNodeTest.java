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
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlBigDecimalNode}s.
 *
 * @author Christian Autermann
 */
public class YamlBigDecimalNodeTest extends AbstractYamlScalarNodeTest {

    /**
     * Test {@code null} in {@link YamlBigDecimalNode} constructor.
     */
    @Test
    public void testNullConstructor() {
        thrown.expect(NullPointerException.class);
        new YamlBigDecimalNode(null);
    }


    @Override
    public void testValue() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.value(), is((Object) node.bigDecimalValue()));;
    }

    @Override
    protected YamlBigDecimalNode instance() {
        return new YamlBigDecimalNode(randomBigDecimal());
    }

    @Override
    public void testToString() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.toString(),
                         is(node.bigDecimalValue().toString()));
    }

    @Override
    public void testEquals() {
        BigDecimal value = randomBigDecimal();
        errors.checkThat(new YamlBigDecimalNode(value),
                         is(new YamlBigDecimalNode(value)));
        errors.checkThat(new YamlBigDecimalNode(value).equals(null), is(false));
        errors.checkThat(new YamlBigDecimalNode(value),
                         is(not(new YamlBigDecimalNode(value.add(BigDecimal.ONE)))));
        errors.checkThat(new YamlBigDecimalNode(value),
                         is(not((YamlNode) factory.arrayNode())));
    }

    @Override
    public void testHashCode() {
        BigDecimal value = randomBigDecimal();
        errors.checkThat(new YamlBigDecimalNode(value).hashCode(),
                         is(value.hashCode()));
    }

    @Override
    public void testTag() {
        errors.checkThat(instance().tag(), is(Tag.FLOAT));
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlDecimalNode node) {
                return hasVisited(true);
            }
        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlDecimalNode node) {
                hasVisited(true);
            }
        };
    }

    @Override
    public void testIsDecimal() {
        assertThat(instance().isDecimal(), is(true));
    }

    @Override
    public void testIsBigDecimal() {
        assertThat(instance().isBigDecimal(), is(true));
    }

    @Override
    public void testIsNumber() {
        assertThat(instance().isNumber(), is(true));
    }

    @Override
    public void testIsScalar() {
        assertThat(instance().isScalar(), is(true));
    }

    @Override
    public void testNumberValue() {
        BigDecimal v = randomBigDecimal();
        assertThat(new YamlBigDecimalNode(v).numberValue(), is((Number) v));
    }

    @Override
    public void testBigDecimalValue() {
        BigDecimal v = randomBigDecimal();
        assertThat(new YamlBigDecimalNode(v).bigDecimalValue(), is(v));
    }

    @Override
    public void testAsTextValue_0args() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asTextValue(), is(String.valueOf(v)));
    }

    @Override
    public void testAsTextValue_String() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asTextValue(null), is(String.valueOf(v)));
        errors.checkThat(node.asTextValue(""), is(String.valueOf(v)));
        errors.checkThat(node.asTextValue("asdf"), is(String.valueOf(v)));
    }

    @Override
    public void testAsBigDecimalValue_0args() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asBigDecimalValue(), is(v));
    }

    @Override
    public void testAsBigDecimalValue_BigDecimal() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ONE), is(v));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.TEN), is(v));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ZERO), is(v));
    }

    @Override
    public void testAsFloatValue_0args() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asFloatValue(), is(v.floatValue()));
    }

    @Override
    public void testAsFloatValue_float() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asFloatValue(0.0f), is(v.floatValue()));
        errors.checkThat(node.asFloatValue(1.0f), is(v.floatValue()));
        errors.checkThat(node.asFloatValue(-1.0f), is(v.floatValue()));
    }

    @Override
    public void testAsDoubleValue_0args() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asDoubleValue(), is(v.doubleValue()));
    }

    @Override
    public void testAsDoubleValue_double() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asDoubleValue(0.0d), is(v.doubleValue()));
        errors.checkThat(node.asDoubleValue(1.0d), is(v.doubleValue()));
        errors.checkThat(node.asDoubleValue(-1.0d), is(v.doubleValue()));
    }

    @Override
    public void testAsNumberValue_0args() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asNumberValue(), is((Number) v));
    }

    @Override
    public void testAsNumberValue_Number() {
        BigDecimal v = randomBigDecimal();
        YamlBigDecimalNode node = new YamlBigDecimalNode(v);
        errors.checkThat(node.asNumberValue(1), is((Number) v));
        errors.checkThat(node.asNumberValue(null), is((Number) v));
        errors.checkThat(node.asNumberValue(0.0d), is((Number) v));
        errors.checkThat(node.asNumberValue(1.0d), is((Number) v));
        errors.checkThat(node.asNumberValue(-1.0d), is((Number) v));
    }

    @Override
    public void testAsIntValue_0args() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asIntValue(),
                         is(node.numberValue().intValue()));
    }

    @Override
    public void testAsIntValue_int() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asIntValue(1),
                         is(node.numberValue().intValue()));
        errors.checkThat(node.asIntValue(0),
                         is(node.numberValue().intValue()));
        errors.checkThat(node.asIntValue(-1),
                         is(node.numberValue().intValue()));
    }

    @Override
    public void testAsByteValue_0args() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asByteValue(),
                         is(node.numberValue().byteValue()));
    }

    @Override
    public void testAsByteValue_byte() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asByteValue((byte) 0),
                         is(node.numberValue().byteValue()));
        errors.checkThat(node.asByteValue((byte) 1),
                         is(node.numberValue().byteValue()));
        errors.checkThat(node.asByteValue((byte) -1),
                         is(node.numberValue().byteValue()));
    }

    @Override
    public void testAsShortValue_0args() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asShortValue(),
                         is(node.numberValue().shortValue()));
    }

    @Override
    public void testAsShortValue_short() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asShortValue((short) 1),
                         is(node.numberValue().shortValue()));
        errors.checkThat(node.asShortValue((short) 0),
                         is(node.numberValue().shortValue()));
        errors.checkThat(node.asShortValue((short) -1),
                         is(node.numberValue().shortValue()));
    }

    @Override
    public void testAsLongValue_0args() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asLongValue(),
                         is(node.numberValue().longValue()));
    }

    @Override
    public void testAsLongValue_long() {
        YamlBigDecimalNode node = instance();
        errors.checkThat(node.asLongValue(0),
                         is(node.numberValue().longValue()));
        errors.checkThat(node.asLongValue(1),
                         is(node.numberValue().longValue()));
        errors.checkThat(node.asLongValue(-1),
                         is(node.numberValue().longValue()));
    }

}
