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

import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.bigDecimalNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.bigIntegerNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.binaryNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.booleanNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.byteNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.containerNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.decimalNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.doubleNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.existingNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.floatNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.intNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.integralNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.longNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.mappingNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.nullNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.numberNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.orderedMappingNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.pairsNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.scalarNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.sequenceNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.setNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.shortNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.textNode;
import static com.github.autermann.snakeyaml.api.nodes.YamlNodesMatcher.timeNode;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.github.autermann.snakeyaml.api.YamlNode;

public class YamlMissingNodeTest {

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Test
    public void testType() {
        YamlNode node = YamlMissingNode.instance();
        assertThat(node, is(notNullValue()));
        errors.checkThat(node, is(not(binaryNode())));
        errors.checkThat(node, is(not(booleanNode())));
        errors.checkThat(node, is(not(containerNode())));
        errors.checkThat(node, is(not(decimalNode())));
        errors.checkThat(node, is(not(existingNode())));
        errors.checkThat(node, is(not(integralNode())));
        errors.checkThat(node, is(not(mappingNode())));
        errors.checkThat(node, is(not(nullNode())));
        errors.checkThat(node, is(not(numberNode())));
        errors.checkThat(node, is(not(orderedMappingNode())));
        errors.checkThat(node, is(not(pairsNode())));
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
}
