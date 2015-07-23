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

import static com.github.autermann.yaml.nodes.YamlNodesMatcher.bigDecimalNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.bigIntegerNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.binaryNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.booleanNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.byteNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.containerNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.decimalNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.doubleNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.existingNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.floatNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.intNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.integralNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.longNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.mapNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.nullNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.numberNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.orderedMapNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.pairsNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.scalarNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.sequenceNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.setNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.shortNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.textNode;
import static com.github.autermann.yaml.nodes.YamlNodesMatcher.timeNode;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;

public class YamlShortNodeTest {

    public final YamlNodeFactory factory = YamlNodeFactory.createDefault();

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Test
    public void testType() {
        YamlNode node = factory.shortNode(Short.MAX_VALUE);
        assertThat(node, is(notNullValue()));
        errors.checkThat(node, is(not(binaryNode())));
        errors.checkThat(node, is(not(booleanNode())));
        errors.checkThat(node, is(not(containerNode())));
        errors.checkThat(node, is(not(decimalNode())));
        errors.checkThat(node, is((existingNode())));
        errors.checkThat(node, is((integralNode())));
        errors.checkThat(node, is(not(mapNode())));
        errors.checkThat(node, is(not(nullNode())));
        errors.checkThat(node, is((numberNode())));
        errors.checkThat(node, is(not(orderedMapNode())));
        errors.checkThat(node, is(not(pairsNode())));
        errors.checkThat(node, is((scalarNode())));
        errors.checkThat(node, is(not(sequenceNode())));
        errors.checkThat(node, is(not(setNode())));
        errors.checkThat(node, is(not(textNode())));
        errors.checkThat(node, is(not(timeNode())));
        errors.checkThat(node, is((bigIntegerNode())));
        errors.checkThat(node, is((longNode())));
        errors.checkThat(node, is((intNode())));
        errors.checkThat(node, is((shortNode())));
        errors.checkThat(node, is(not(byteNode())));
        errors.checkThat(node, is(not(bigDecimalNode())));
        errors.checkThat(node, is(not(doubleNode())));
        errors.checkThat(node, is(not(floatNode())));
    }
}
