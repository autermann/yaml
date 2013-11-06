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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Test;



/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlTest {
    public static final Yaml YAML = new Yaml();
    private final YamlNodeFactory factory = YamlNodeFactory.getDefault();

    public void test(YamlNode node) {
        assertThat(YAML.load(YAML.dump(node)), is(equalTo(node)));
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
    public void testIntegerNode() {
        test(factory.intNode(42));
    }

    @Test
    public void testDoubleNode() {
        test(factory.doubleNode(42.42));
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
