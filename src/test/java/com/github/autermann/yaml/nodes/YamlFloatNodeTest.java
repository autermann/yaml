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

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlFloatNode}s.
 *
 * @author Christian Autermann
 */
public class YamlFloatNodeTest extends AbstractYamlScalarNodeTest {
    @Override
    public void testValue() {
        YamlFloatNode node = instance();
        errors.checkThat(node.value(), is((Object) node.floatValue()));;
    }

    @Override
    protected YamlFloatNode instance() {
        return new YamlFloatNode(randomFloat());
    }

    @Override
    public void testToString() {
        YamlFloatNode node = instance();
        errors.checkThat(node.toString(), is(String.valueOf(node.floatValue())));
    }

    @Override
    public void testEquals() {
        float value = randomFloat();
        errors.checkThat(new YamlFloatNode(value), is(new YamlFloatNode(value)));
        errors.checkThat(new YamlFloatNode(value).equals(null), is(false));
        errors.checkThat(new YamlFloatNode(value),
                         is(not(new YamlFloatNode(value + 1.0f))));
        errors.checkThat(new YamlFloatNode(value),
                         is(not((YamlNode) factory.arrayNode())));
    }

    @Override
    public void testHashCode() {
        float value = randomFloat();
        errors.checkThat(new YamlFloatNode(value).hashCode(), is(Float
                .valueOf(value).hashCode()));
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
    public void testIsFloat() {
        assertThat(instance().isFloat(), is(true));
    }

    @Override
    public void testIsDouble() {
        assertThat(instance().isDouble(), is(true));
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
    public void testFloatValue() {
        float v = randomFloat();
        assertThat(new YamlFloatNode(v).floatValue(), is(v));
    }

    @Override
    public void testDoubleValue() {
        float v = randomFloat();
        assertThat(new YamlFloatNode(v).doubleValue(), is((double) v));
    }

    @Override
    public void testNumberValue() {
        float v = randomFloat();
        assertThat(new YamlFloatNode(v).numberValue(), is((Number) v));
    }

    @Override
    public void testBigDecimalValue() {
        float v = randomFloat();
        assertThat(new YamlFloatNode(v).bigDecimalValue(),
                   is(BigDecimal.valueOf((double) v)));
    }

    @Override
    public void testAsTextValue_0args() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asTextValue(), is(String.valueOf(v)));
    }

    @Override
    public void testAsTextValue_String() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asTextValue(null), is(String.valueOf(v)));
        errors.checkThat(node.asTextValue(""), is(String.valueOf(v)));
        errors.checkThat(node.asTextValue("asdf"), is(String.valueOf(v)));
    }

    @Override
    public void testAsBigDecimalValue_0args() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asBigDecimalValue(),
                         is(BigDecimal.valueOf((double) v)));
    }

    @Override
    public void testAsBigDecimalValue_BigDecimal() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        BigDecimal bdv = BigDecimal.valueOf((double) v);
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ONE), is(bdv));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.TEN), is(bdv));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ZERO), is(bdv));
    }

    @Override
    public void testAsFloatValue_0args() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asFloatValue(), is(v));
    }

    @Override
    public void testAsFloatValue_float() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asFloatValue(0.0f), is(v));
        errors.checkThat(node.asFloatValue(1.0f), is(v));
        errors.checkThat(node.asFloatValue(-1.0f), is(v));
    }

    @Override
    public void testAsDoubleValue_0args() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asDoubleValue(), is((double) v));
    }

    @Override
    public void testAsDoubleValue_double() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asDoubleValue(0.0d), is((double) v));
        errors.checkThat(node.asDoubleValue(1.0d), is((double) v));
        errors.checkThat(node.asDoubleValue(-1.0d), is((double) v));
    }

    @Override
    public void testAsNumberValue_0args() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asNumberValue(), is((Number) v));
    }

    @Override
    public void testAsNumberValue_Number() {
        float v = randomFloat();
        YamlFloatNode node = new YamlFloatNode(v);
        errors.checkThat(node.asNumberValue(1), is((Number) v));
        errors.checkThat(node.asNumberValue(null), is((Number) v));
        errors.checkThat(node.asNumberValue(0.0f), is((Number) v));
        errors.checkThat(node.asNumberValue(1.0f), is((Number) v));
        errors.checkThat(node.asNumberValue(-1.0f), is((Number) v));
    }

    @Override
    public void testAsIntValue_0args() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asIntValue(),
                         is(node.numberValue().intValue()));
    }

    @Override
    public void testAsIntValue_int() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asIntValue(1),
                         is(node.numberValue().intValue()));
        errors.checkThat(node.asIntValue(0),
                         is(node.numberValue().intValue()));
        errors.checkThat(node.asIntValue(-1),
                         is(node.numberValue().intValue()));
    }

    @Override
    public void testAsByteValue_0args() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asByteValue(),
                         is(node.numberValue().byteValue()));
    }

    @Override
    public void testAsByteValue_byte() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asByteValue((byte) 0),
                         is(node.numberValue().byteValue()));
        errors.checkThat(node.asByteValue((byte) 1),
                         is(node.numberValue().byteValue()));
        errors.checkThat(node.asByteValue((byte) -1),
                         is(node.numberValue().byteValue()));
    }

    @Override
    public void testAsShortValue_0args() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asShortValue(),
                         is(node.numberValue().shortValue()));
    }

    @Override
    public void testAsShortValue_short() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asShortValue((short) 1),
                         is(node.numberValue().shortValue()));
        errors.checkThat(node.asShortValue((short) 0),
                         is(node.numberValue().shortValue()));
        errors.checkThat(node.asShortValue((short) -1),
                         is(node.numberValue().shortValue()));
    }

    @Override
    public void testAsLongValue_0args() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asLongValue(),
                         is(node.numberValue().longValue()));
    }

    @Override
    public void testAsLongValue_long() {
        YamlFloatNode node = instance();
        errors.checkThat(node.asLongValue(0),
                         is(node.numberValue().longValue()));
        errors.checkThat(node.asLongValue(1),
                         is(node.numberValue().longValue()));
        errors.checkThat(node.asLongValue(-1),
                         is(node.numberValue().longValue()));
    }
}
