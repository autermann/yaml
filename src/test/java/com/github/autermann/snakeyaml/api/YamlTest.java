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

import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.byteNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.intNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.longNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.shortNode;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import org.hamcrest.Matcher;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.github.autermann.snakeyaml.api.util.DecimalPrecision;

public class YamlTest {
    public final Yaml YAML = new Yaml();
    public final DefaultYamlNodeFactory factory = YamlNodeFactory.createDefault();

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    public void test(YamlNode node) {
        test(node, is(equalTo(node)));
    }

    public void test(YamlNode node, Matcher<YamlNode> matcher) {
        errors.checkThat(YAML.load(YAML.dump(node)), matcher);
    }

    @Test
    public void testTextNode() {
        test(factory.textNode("hello world"));
    }

    @Test
    public void testBooleanNode() {
        test(factory.booleanNode(true));
        test(factory.booleanNode(false));
    }

    @Test
    public void testBigIntegerNode() {
        test(factory.bigIntegerNode(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE)));
        test(factory.bigIntegerNode(BigInteger.valueOf(Long.MAX_VALUE)), is(longNode()));
        test(factory.bigIntegerNode(BigInteger.valueOf((long) Integer.MAX_VALUE)), is(intNode()));
        test(factory.bigIntegerNode(BigInteger.valueOf((long) Short.MAX_VALUE)), is(shortNode()));
        test(factory.bigIntegerNode(BigInteger.valueOf((long) Byte.MAX_VALUE)), is(byteNode()));
    }

    @Test
    public void testLongNode() {
        test(factory.longNode(Long.MAX_VALUE));
        test(factory.longNode((long) Integer.MAX_VALUE), is(intNode()));
        test(factory.longNode((long) Short.MAX_VALUE), is(shortNode()));
        test(factory.longNode((long) Byte.MAX_VALUE), is(byteNode()));
    }

    @Test
    public void testIntegerNode() {
        test(factory.intNode(Integer.MAX_VALUE));
        test(factory.intNode((int) Short.MAX_VALUE), is(shortNode()));
        test(factory.intNode((int) Byte.MAX_VALUE), is(byteNode()));
    }

    @Test
    public void testShortNode() {
        test(factory.shortNode(Short.MAX_VALUE));
        test(factory.shortNode((short) Byte.MAX_VALUE), is(byteNode()));
    }

    @Test
    public void testByteNode() {
        test(factory.byteNode(Byte.MAX_VALUE));
    }

    @Test
    public void testDoubleNode() {
        test(factory.withDecimalPrecision(DecimalPrecision.DOUBLE)
                .doubleNode(42.42d));
    }

    @Test
    public void testFloatNode() {
        test(factory.withDecimalPrecision(DecimalPrecision.FLOAT)
                .floatNode(42.42f));
    }

    @Test
    public void testBigDecimalNode() {
        test(factory.withDecimalPrecision(DecimalPrecision.BIG_DECIMAL)
                .bigDecimalNode(BigDecimal.valueOf(42.42d)));
    }

    @Test
    public void testBinaryNode() {
        byte[] bytes = new byte[128];
        new Random().nextBytes(bytes);
        test(factory.binaryNode(bytes));
    }

    @Test
    public void testTimeNode() {
        test(factory.dateTimeNode(new DateTime()));
    }

    @Test
    public void testNullNode() {
        test(factory.nullNode());
    }

    @Test
    public void testMappingNode() {
        test(factory.mappingNode().put("a", "b").put("b", "c"));
    }

    @Test
    public void testOrderedMappingNode() {
        test(factory.orderedMappingNode().put("a", "b").put("b", "c"));
    }

    @Test
    public void testSequenceNode() {
        test(factory.sequenceNode().add("a").add("a").add("b"));
    }

    @Test
    public void testPairsNode() {
        test(factory.pairsNode().put("a", "b").put("b", "c").put("a", "b"));
    }

    @Test
    public void testSetNode() {
        test(factory.setNode().add("a").add("a").add("b"));
    }
}
