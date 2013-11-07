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
package com.github.autermann.snakeyaml.api.nodes;

import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.bigDecimalNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.bigIntegerNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.binaryNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.booleanNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.byteNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.containerNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.decimalNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.doubleNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.existingNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.floatNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.intNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.integralNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.longNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.mappingNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.nullNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.numberNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.orderedMappingNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.pairsNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.scalarNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.sequenceNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.setNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.shortNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.textNode;
import static com.github.autermann.snakeyaml.api.nodes.NodesMatcher.timeNode;
import static com.google.common.collect.Maps.immutableEntry;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;

public class YamlPairsNodeTest {
    private final YamlNodeFactory factory = YamlNodeFactory.getDefault();

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Test
    @SuppressWarnings("unchecked")
    public void testOrder() {
        YamlNode a = factory.textNode("a");
        YamlNode b = factory.textNode("b");
        errors.checkThat(
                factory.pairsNode().put(a, a).put(b, b).put(a, a).entries(),
                allOf(is(notNullValue()), contains(immutableEntry(a, a),
                                                   immutableEntry(b, b),
                                                   immutableEntry(a, a))));
    }

    @Test
    public void testEqualsAndHashCode() {
        YamlPairsNode a;
        YamlPairsNode b;

        a = factory.pairsNode();
        b = factory.pairsNode();
        checkEquals(a, b);
        checkEqualHashCode(a, b);

        a = factory.pairsNode().put("a", "b");
        checkNotEquals(a, b);
        checkNotEqualHashCode(a, b);

        b = factory.pairsNode().put("a", "b");
        checkEquals(a, b);
        checkEqualHashCode(a, b);

        a = factory.pairsNode().put("a", "b").put("a", "b");
        b = factory.pairsNode().put("a", "b");
        checkNotEquals(a, b);
        checkNotEqualHashCode(a, b);

        a = factory.pairsNode().put("a", "b").put("a", "b");
        b = factory.pairsNode().put("a", "b").put("a", "b");
        checkEquals(a, b);
        checkEqualHashCode(a, b);

        a = factory.pairsNode().put("a", "b").put("a", "b").put("c", "d");
        b = factory.pairsNode().put("a", "b").put("c", "d").put("a", "b");
        checkNotEquals(a, b);
        checkNotEqualHashCode(a, b);
    }

    @Test
    public void testType() {
        YamlNode node = factory.pairsNode();
        assertThat(node, is(notNullValue()));
        errors.checkThat(node, is(not(binaryNode())));
        errors.checkThat(node, is(not(booleanNode())));
        errors.checkThat(node, is((containerNode())));
        errors.checkThat(node, is(not(decimalNode())));
        errors.checkThat(node, is((existingNode())));
        errors.checkThat(node, is(not(integralNode())));
        errors.checkThat(node, is(not(mappingNode())));
        errors.checkThat(node, is(not(nullNode())));
        errors.checkThat(node, is(not(numberNode())));
        errors.checkThat(node, is(not(orderedMappingNode())));
        errors.checkThat(node, is((pairsNode())));
        errors.checkThat(node, is(not(scalarNode())));
        errors.checkThat(node, is(not(sequenceNode())));
        errors.checkThat(node, is(not(setNode())));
        errors.checkThat(node, is(not(textNode())));
        errors.checkThat(node, is(not(timeNode())));
        errors.checkThat(node, is(not(bigIntegerNode())));
        errors.checkThat(node, is(not(longNode())));
        errors.checkThat(node, is(not(intNode())));
        errors.checkThat(node, is(not(shortNode())));
        errors.checkThat(node, is(not(byteNode())));
        errors.checkThat(node, is(not(bigDecimalNode())));
        errors.checkThat(node, is(not(doubleNode())));
        errors.checkThat(node, is(not(floatNode())));
    }

    protected void checkEquals(YamlNode a, YamlNode b) {
        errors.checkThat(a, allOf(is(equalTo(a)), is(equalTo(b))));
        errors.checkThat(b, allOf(is(equalTo(a)), is(equalTo(b))));
    }

    protected void checkNotEquals(YamlNode a, YamlNode b) {
        errors.checkThat(a, allOf(is(equalTo(a)), is(not(equalTo(b)))));
        errors.checkThat(b, allOf(is(not(equalTo(a))), is(equalTo(b))));
    }

    protected void checkEqualHashCode(YamlNode a, YamlNode b) {
        errors
                .checkThat(a.hashCode(), allOf(is(equalTo(a.hashCode())), is(equalTo(b
                                                .hashCode()))));
        errors
                .checkThat(b.hashCode(), allOf(is(equalTo(a.hashCode())), is(equalTo(b
                                                .hashCode()))));
    }

    protected void checkNotEqualHashCode(YamlNode a, YamlNode b) {
        errors
                .checkThat(a.hashCode(), allOf(is(equalTo(a.hashCode())), is(not(equalTo(b
                                                        .hashCode())))));
        errors
                .checkThat(b.hashCode(), allOf(is(not(equalTo(a.hashCode()))), is(equalTo(b
                                                .hashCode()))));
    }
}
