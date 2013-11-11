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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlTextNode}.
 *
 * @author Christian Autermann
 */
public class YamlTextNodeTest extends AbstractYamlScalarNodeTest {

    /**
     * Tests for {@code null} in constructor.
     */
    public void testConstructorNull() {
        thrown.expect(NullPointerException.class);
        new YamlTextNode(null);
    }

    @Override
    public void testToString() {
        YamlTextNode n = instance();
        errors.checkThat(new YamlTextNode("").toString(), is(""));
        errors.checkThat(n.toString(), is(n.textValue()));
    }

    @Override
    public void testEquals() {
        String s1 = UUID.randomUUID().toString();
        String s2 = UUID.randomUUID().toString();
        errors.checkThat(new YamlTextNode(s1), is(new YamlTextNode(s1)));
        errors.checkThat(new YamlTextNode(s2), is(new YamlTextNode(s2)));
        errors.checkThat(new YamlTextNode(s1), is(not(new YamlTextNode(s2))));
        errors.checkThat(new YamlTextNode(s1).equals(null), is(false));
        errors.checkThat(new YamlTextNode(s1),
                         is(not((YamlNode) factory.arrayNode())));
    }

    @Override
    public void testHashCode() {
        String s1 = UUID.randomUUID().toString();
        String s2 = UUID.randomUUID().toString();
        errors.checkThat(new YamlTextNode(s1).hashCode(),
                         is(new YamlTextNode(s1).hashCode()));
        errors.checkThat(new YamlTextNode(s2).hashCode(),
                         is(new YamlTextNode(s2).hashCode()));
        errors.checkThat(new YamlTextNode(s1).hashCode(),
                         is(not(new YamlTextNode(s2).hashCode())));
    }

    @Override
    public void testTag() {
        errors.checkThat(instance().tag(), is(Tag.STR));
    }

    @Override
    protected YamlTextNode instance() {
        return new YamlTextNode(UUID.randomUUID().toString());
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {

            @Override
            public Void visit(YamlTextNode node) {
                return hasVisited(true);
            }

        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {

            @Override
            public void visit(YamlTextNode node) {
                hasVisited(true);
            }

        };
    }

    @Override
    public void testAsTextValue_0args() {
        YamlTextNode node = instance();
        errors.checkThat(node.asTextValue(), is(node.textValue()));
    }

    @Override
    public void testAsTextValue_String() {
        YamlTextNode node = instance();
        errors.checkThat(node.asTextValue(""), is(node.textValue()));
        errors.checkThat(node.asTextValue("adsf"), is(node.textValue()));
        errors.checkThat(node.asTextValue("null"), is(node.textValue()));
    }

    @Override
    public void testTextValue() {
        String value = UUID.randomUUID().toString();
        YamlNode node = new YamlTextNode(value);
        errors.checkThat(node.textValue(), is(value));
    }

    @Override
    public void testAsBinaryValue_0args() {
        YamlNode node = instance();
        errors.checkThat(node.asBinaryValue(), is(node.textValue().getBytes()));
    }

    @Override
    public void testAsBinaryValue_byteArr() {
        YamlNode node = instance();
        errors.checkThat(node.asBinaryValue(null),
                         is(node.textValue().getBytes()));
        errors.checkThat(node.asBinaryValue(new byte[0]),
                         is(node.textValue().getBytes()));
        errors.checkThat(node.asBinaryValue(new byte[] { 1, 2, 3 }),
                         is(node.textValue().getBytes()));
    }

    @Override
    public void testIsScalar() {
        errors.checkThat(instance().isScalar(), is(true));
    }

    @Override
    public void testIsText() {
        errors.checkThat(instance().isText(), is(true));
    }

    @Override
    public void testAsBooleanValue_0args() {
        YamlTextNode t = new YamlTextNode("true");
        YamlTextNode f = new YamlTextNode("false");
        errors.checkThat(t.asBooleanValue(), is(true));
        errors.checkThat(f.asBooleanValue(), is(false));
        errors.checkThat(instance().asBooleanValue(), is(false));
    }

    @Override
    public void testAsBooleanValue_boolean() {
        YamlTextNode t = new YamlTextNode("true");
        YamlTextNode f = new YamlTextNode("false");
        errors.checkThat(t.asBooleanValue(true), is(true));
        errors.checkThat(t.asBooleanValue(true), is(true));
        errors.checkThat(f.asBooleanValue(true), is(false));
        errors.checkThat(f.asBooleanValue(false), is(false));
        errors.checkThat(instance().asBooleanValue(true), is(false));
        errors.checkThat(instance().asBooleanValue(false), is(false));
    }

    @Override
    public void testValue() {
        YamlTextNode v = instance();
        errors.checkThat(v.value(), is(v.textValue()));
    }

    /**
     * Further tests for {@link YamlTextNode#asByteValue() }.
     */
    @Test
    public void testAsByteValue() {
        errors.checkThat(new YamlTextNode(Byte.toString(Byte.MIN_VALUE))
                .asByteValue(), is(Byte.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asShortValue() }.
     */
    @Test
    public void testAsShortValue() {
        errors.checkThat(new YamlTextNode(Short.toString(Short.MIN_VALUE))
                .asShortValue(), is(Short.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asIntValue() }.
     */
    @Test
    public void testAsIntValue() {
        errors.checkThat(new YamlTextNode(Integer.toString(Integer.MIN_VALUE))
                .asIntValue(), is(Integer.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asLongValue() }.
     */
    @Test
    public void testAsLongValue() {
        errors.checkThat(new YamlTextNode(Long.toString(Long.MIN_VALUE))
                .asLongValue(), is(Long.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asFloatValue() }.
     */
    @Test
    public void testAsFloatValue() {
        errors.checkThat(new YamlTextNode(Float.toString(Float.MIN_VALUE))
                .asFloatValue(), is(Float.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asDoubleValue() }.
     */
    @Test
    public void testAsDoubleValue() {
        errors.checkThat(new YamlTextNode(Double.toString(Double.MIN_VALUE))
                .asDoubleValue(), is(Double.MIN_VALUE));
    }

    /**
     * Further tests for {@link YamlTextNode#asBigIntegerValue() }.
     */
    @Test
    public void testAsBigIntegerValue() {
        errors.checkThat(new YamlTextNode(BigInteger.TEN.toString())
                .asBigIntegerValue(), is(BigInteger.TEN));
    }

    /**
     * Further tests for {@link YamlTextNode#asBigDecimalValue() }.
     */
    @Test
    public void testAsBigDecimalValue() {
        errors.checkThat(new YamlTextNode(BigDecimal.TEN.toString())
                .asBigDecimalValue(), is(BigDecimal.TEN));
    }

    /**
     * Further tests for {@link YamlTextNode#asDateValue() }.
     */
    @Test
    public void testAsDateValue() {
        DateTime d = DateTime.now();
        errors.checkThat(new YamlTextNode(ISODateTimeFormat.dateTime().print(d))
                .asDateValue(), is(d.toDate()));
    }

    /**
     * Further tests for {@link YamlTextNode#asDateTimeValue() }.
     */
    @Test
    public void testAsDateTimeValue() {
        DateTime d = DateTime.now();
        errors.checkThat(new YamlTextNode(ISODateTimeFormat.dateTime().print(d))
                .asDateTimeValue(), is(d));
    }

    /**
     * Further tests for {@link YamlTextNode#asBooleanValue() }.
     */
    @Test
    public void testAsBooleanValue() {
        errors.checkThat(new YamlTextNode("true").asBooleanValue(), is(true));
        errors.checkThat(new YamlTextNode("false").asBooleanValue(), is(false));
    }

}
