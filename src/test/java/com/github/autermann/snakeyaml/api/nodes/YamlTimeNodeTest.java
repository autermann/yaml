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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;

public class YamlTimeNodeTest {
    public final YamlNodeFactory factory = YamlNodeFactory.getDefault();

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Test
    public void testType() {
        YamlNode node = factory.dateTimeNode(DateTime.now());
        assertThat(node, is(notNullValue()));
        errors.checkThat(node, is(not(binaryNode())));
        errors.checkThat(node, is(not(booleanNode())));
        errors.checkThat(node, is(not(containerNode())));
        errors.checkThat(node, is(not(decimalNode())));
        errors.checkThat(node, is((existingNode())));
        errors.checkThat(node, is(not(integralNode())));
        errors.checkThat(node, is(not(mappingNode())));
        errors.checkThat(node, is(not(nullNode())));
        errors.checkThat(node, is(not(numberNode())));
        errors.checkThat(node, is(not(orderedMappingNode())));
        errors.checkThat(node, is(not(pairsNode())));
        errors.checkThat(node, is((scalarNode())));
        errors.checkThat(node, is(not(sequenceNode())));
        errors.checkThat(node, is(not(setNode())));
        errors.checkThat(node, is(not(textNode())));
        errors.checkThat(node, is((timeNode())));
        errors.checkThat(node, is(not(bigIntegerNode())));
        errors.checkThat(node, is(not(longNode())));
        errors.checkThat(node, is(not(intNode())));
        errors.checkThat(node, is(not(shortNode())));
        errors.checkThat(node, is(not(byteNode())));
        errors.checkThat(node, is(not(bigDecimalNode())));
        errors.checkThat(node, is(not(doubleNode())));
        errors.checkThat(node, is(not(floatNode())));
    }
}
