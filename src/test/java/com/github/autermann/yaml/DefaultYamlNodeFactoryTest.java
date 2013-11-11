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

import static com.github.autermann.yaml.nodes.YamlNodesMatcher.bigDecimalNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.bigIntegerNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.binaryNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.booleanNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.byteNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.doubleNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.floatNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.intNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.longNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.nullNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.shortNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.textNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.timeNode;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.github.autermann.yaml.nodes.YamlBigDecimalNode;
import com.github.autermann.yaml.nodes.YamlDoubleNode;
import com.github.autermann.yaml.nodes.YamlFloatNode;
import com.github.autermann.yaml.nodes.YamlScalarNode;
import com.github.autermann.yaml.util.DecimalPrecision;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class DefaultYamlNodeFactoryTest {
    private final DefaultYamlNodeFactory factory = DefaultYamlNodeFactory
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
     * Tests the return type of {@link DecimalPrecision#BIG_DECIMAL} factories.
     */
    @Test
    public void testBigDecimalFactory() {
        YamlNodeFactory factory = DefaultYamlNodeFactory
                .create(DecimalPrecision.BIG_DECIMAL);
        errors.checkThat(factory.bigDecimalNode(BigDecimal.ZERO),
                         is(instanceOf(YamlBigDecimalNode.class)));
        errors.checkThat(factory.doubleNode(0.0d),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.POSITIVE_INFINITY),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.NEGATIVE_INFINITY),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.NaN),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.floatNode(0.0f),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.POSITIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NEGATIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NaN),
                         is(instanceOf(YamlFloatNode.class)));
    }

    /**
     * Tests the return type of {@link DecimalPrecision#DOUBLE} factories.
     */
    @Test
    public void testDoubleFactory() {
        YamlNodeFactory factory = DefaultYamlNodeFactory
                .create(DecimalPrecision.DOUBLE);
        errors.checkThat(factory.bigDecimalNode(BigDecimal.ZERO),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(0.0d),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.POSITIVE_INFINITY),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.NEGATIVE_INFINITY),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.doubleNode(Double.NaN),
                         is(instanceOf(YamlDoubleNode.class)));
        errors.checkThat(factory.floatNode(0.0f),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.POSITIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NEGATIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NaN),
                         is(instanceOf(YamlFloatNode.class)));
    }

    /**
     * Tests the return type of {@link DecimalPrecision#FLOAT} factories.
     */
    @Test
    public void testFloatFactory() {
        YamlNodeFactory factory = DefaultYamlNodeFactory
                .create(DecimalPrecision.FLOAT);
        errors.checkThat(factory.bigDecimalNode(BigDecimal.ZERO),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.doubleNode(0.0d),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.doubleNode(Double.POSITIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.doubleNode(Double.NEGATIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.doubleNode(Double.NaN),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(0.0f),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.POSITIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NEGATIVE_INFINITY),
                         is(instanceOf(YamlFloatNode.class)));
        errors.checkThat(factory.floatNode(Float.NaN),
                         is(instanceOf(YamlFloatNode.class)));
    }

    @Test
    public void testNullDecimalPrecision() {
        thrown.expect(NullPointerException.class);
        new DefaultYamlNodeFactory(null);
    }

    @Test
    public void testNullDecimalPrecision2() {
        thrown.expect(NullPointerException.class);
        DefaultYamlNodeFactory.create(null);
    }

    @Test
    public void testBooleanNode() {
        errors.checkThat(factory.booleanNode(null), is(nullNode()));
        errors.checkThat(factory.booleanNode(Boolean.TRUE), is(booleanNode()));
    }

    @Test
    public void testBinaryNode() {
        errors.checkThat(factory.binaryNode((Byte[]) null), is(nullNode()));
        errors.checkThat(factory.binaryNode((byte[]) null), is(nullNode()));
        Random random = new Random();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        YamlScalarNode node = factory.binaryNode(bytes);
        errors.checkThat(node, is(binaryNode()));
        Byte[] bytes2 = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes2[i] = bytes[i];
        }
        YamlScalarNode node2 = factory.binaryNode(bytes2);
        errors.checkThat(node2, is(binaryNode()));
        errors.checkThat(node, is(equalTo(node2)));
    }

    @Test
    public void testByteNode() {
        errors.checkThat(factory.byteNode((byte) 0), is(byteNode()));
        errors.checkThat(factory.byteNode((Byte) ((byte) 0)), is(byteNode()));
        errors.checkThat(factory.byteNode(null), is(nullNode()));
    }

    @Test
    public void testShortNode() {
        errors.checkThat(factory.shortNode((short) 0), is(shortNode()));
        errors.checkThat(factory.shortNode((Short) ((short) 0)),
                         is(shortNode()));
        errors.checkThat(factory.shortNode(null), is(nullNode()));
    }

    @Test
    public void testIntNode() {
        errors.checkThat(factory.intNode(0), is(intNode()));
        errors.checkThat(factory.intNode((Integer) (0)), is(intNode()));
        errors.checkThat(factory.intNode(null), is(nullNode()));
    }

    @Test
    public void testLongNode() {
        errors.checkThat(factory.longNode(0L), is(longNode()));
        errors.checkThat(factory.longNode((Long) 0L), is(longNode()));
        errors.checkThat(factory.longNode(null), is(nullNode()));
    }

    @Test
    public void testBigIntegerNode() {
        errors.checkThat(factory.bigIntegerNode(BigInteger.ZERO),
                         is(bigIntegerNode()));
        errors.checkThat(factory.bigIntegerNode(null), is(nullNode()));
    }

    @Test
    public void testFloatNode() {
        errors.checkThat(factory.floatNode(0.0f), is(floatNode()));
        errors.checkThat(factory.floatNode((Float) 0.0f), is(floatNode()));
        errors.checkThat(factory.floatNode(null), is(nullNode()));
    }

    @Test
    public void testDoubleNode() {
        errors.checkThat(factory.doubleNode(0.0d), is(doubleNode()));
        errors.checkThat(factory.doubleNode((Double) 0.0d), is(doubleNode()));
        errors.checkThat(factory.doubleNode(null), is(nullNode()));
    }

    @Test
    public void testTextNode() {
        errors.checkThat(factory.textNode(null), is(nullNode()));
        errors.checkThat(factory.textNode(""), is(textNode()));
        errors.checkThat(factory.textNode("asdf"), is(textNode()));
    }

    @Test
    public void testBigDecimalNode() {
        errors.checkThat(factory.bigDecimalNode(null), is(nullNode()));
        errors.checkThat(factory.bigDecimalNode(BigDecimal.ONE),
                         is(bigDecimalNode()));
    }

    @Test
    public void testDateTimeNode() {
        errors.checkThat(factory.dateTimeNode((Date) null), is(nullNode()));
        errors.checkThat(factory.dateTimeNode((DateTime) null), is(nullNode()));
        errors.checkThat(factory.dateTimeNode(new Date()), is(timeNode()));
        errors.checkThat(factory.dateTimeNode(new DateTime()), is(timeNode()));
    }

    @Test
    public void testObjectNode() {
        errors.checkThat(factory.objectNode(), is(factory.mapNode()));
    }

    @Test
    public void testListNode() {
        errors.checkThat(factory.listNode(), is(factory.sequenceNode()));
    }

}
