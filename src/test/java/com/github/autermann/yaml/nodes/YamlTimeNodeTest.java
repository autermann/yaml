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
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlTimeNode}s.
 *
 * @author Christian Autermann
 */
public class YamlTimeNodeTest extends AbstractYamlScalarNodeTest {

    /**
     * Tests {@code null} constructor.
     */
    @Test
    public void testConstructorNull() {
        thrown.expect(NullPointerException.class);
        new YamlTimeNode(null);
    }

    @Override
    public void testToString() {
        DateTime v = randomDateTime();
        errors.checkThat(new YamlTimeNode(v).toString(),
                         is(ISODateTimeFormat.dateTime().print(v)));
    }

    @Override
    public void testEquals() {
        DateTime value = randomDateTime();
        errors.checkThat(new YamlTimeNode(value),
                         is(new YamlTimeNode(value)));
        errors.checkThat(new YamlTimeNode(value).equals(null), is(false));
        errors.checkThat(new YamlTimeNode(value),
                         is(not(new YamlTimeNode(randomDateTime()))));
        errors.checkThat(new YamlTimeNode(value),
                         is(not((YamlNode) factory.arrayNode())));
    }

    @Override
    public void testHashCode() {
        DateTime v = randomDateTime();
        errors.checkThat(new YamlTimeNode(v).hashCode(), is(v.hashCode()));
    }

    @Override
    public void testTag() {
        errors.checkThat(instance().tag(), is(Tag.TIMESTAMP));
    }

    @Override
    protected YamlTimeNode instance() {
        return new YamlTimeNode(randomDateTime());
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlTimeNode node) {
                return hasVisited(true);
            }
        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlTimeNode node) {
                hasVisited(true);
            }
        };
    }

    @Override
    public void testValue() {
        DateTime v = randomDateTime();
        errors.checkThat(new YamlTimeNode(v).value(), is(v));
    }

    @Override
    public void testIsScalar() {
        errors.checkThat(instance().isScalar(), is(true));
    }

    @Override
    public void testIsTime() {
        errors.checkThat(instance().isTime(), is(true));
    }

    @Override
    public void testDateTimeValue() {
        YamlTimeNode node = instance();
        errors.checkThat(node.dateTimeValue(), is(node.value()));
    }

    @Override
    public void testDateValue() {
        YamlTimeNode node = instance();
        errors.checkThat(node.dateValue(), is(node.value().toDate()));
    }

    @Override
    public void testAsDateTimeValue_0args() {
        YamlTimeNode node = instance();
        errors.checkThat(node.asDateTimeValue(), is(node.value()));
    }

    @Override
    public void testAsDateValue_0args() {
        YamlTimeNode node = instance();
        errors.checkThat(node.asDateValue(), is(node.value().toDate()));
    }

    @Override
    public void testAsDateTimeValue_DateTime() {
        YamlTimeNode node = instance();
        errors.checkThat(node.asDateTimeValue(DateTime.now()),
                         is(node.value()));
        errors.checkThat(node.asDateTimeValue(randomDateTime()),
                         is(node.value()));
        errors.checkThat(node.asDateTimeValue(null), is(node.value()));
    }

    @Override
    public void testAsDateValue_Date() {
        YamlTimeNode node = instance();
        errors.checkThat(node.asDateValue(new Date()),
                         is(node.value().toDate()));
        errors.checkThat(node.asDateValue(randomDate()),
                         is(node.value().toDate()));
        errors.checkThat(node.asDateValue(null),
                         is(node.value().toDate()));
    }

    @Override
    public void testAsLongValue_0args() {
        long l = randomLong();
        errors.checkThat(new YamlTimeNode(new DateTime(l)).asLongValue(), is(l));
    }

    @Override
    public void testAsNumberValue_0args() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        errors.checkThat(node.asNumberValue(), is((Number) l));
    }

    @Override
    public void testAsTextValue_0args() {
        DateTime v = randomDateTime();
        errors.checkThat(new YamlTimeNode(v).asTextValue(),
                         is(ISODateTimeFormat.dateTime().print(v)));
    }

    @Override
    public void testAsTextValue_String() {
        DateTime v = randomDateTime();
        String printed = ISODateTimeFormat.dateTime().print(v);
        errors.checkThat(new YamlTimeNode(v).asTextValue(""), is(printed));
        errors.checkThat(new YamlTimeNode(v).asTextValue(null), is(printed));
        errors.checkThat(new YamlTimeNode(v).asTextValue("asdf"), is(printed));
    }

    @Override
    public void testAsNumberValue_Number() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        errors.checkThat(node.asNumberValue(0), is((Number) l));
        errors.checkThat(node.asNumberValue(1), is((Number) l));
        errors.checkThat(node.asNumberValue(-1), is((Number) l));
    }

    @Override
    public void testAsLongValue_long() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        errors.checkThat(node.asLongValue(0L), is(l));
        errors.checkThat(node.asLongValue(1L), is(l));
        errors.checkThat(node.asLongValue(-1L), is(l));
    }

    @Override
    public void testAsBigIntegerValue_BigInteger() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        BigInteger biv = BigInteger.valueOf(l);
        errors.checkThat(node.asBigIntegerValue(null), is(biv));
        errors.checkThat(node.asBigIntegerValue(BigInteger.ONE), is(biv));
        errors.checkThat(node.asBigIntegerValue(BigInteger.ZERO), is(biv));
    }

    @Override
    public void testAsBigDecimalValue_BigDecimal() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        BigDecimal bdv = BigDecimal.valueOf(l);
        errors.checkThat(node.asBigDecimalValue(null), is(bdv));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ONE), is(bdv));
        errors.checkThat(node.asBigDecimalValue(BigDecimal.ZERO), is(bdv));
    }


    @Override
    public void testAsBigDecimalValue_0args() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        errors.checkThat(node.asBigDecimalValue(), is(BigDecimal.valueOf(l)));
    }

    @Override
    public void testAsBigIntegerValue_0args() {
        long l = randomLong();
        YamlTimeNode node = new YamlTimeNode(new DateTime(l));
        errors.checkThat(node.asBigIntegerValue(), is(BigInteger.valueOf(l)));
    }




}
