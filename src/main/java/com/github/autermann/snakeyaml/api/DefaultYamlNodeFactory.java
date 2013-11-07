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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;

import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;

public class DefaultYamlNodeFactory extends YamlNodeFactory {

    private static final DefaultYamlNodeFactory instance
            = new DefaultYamlNodeFactory();

    private DefaultYamlNodeFactory() {
    }

    @Override
    protected YamlTextNode createTextNode(String value) {
        return new YamlTextNode(value);
    }

    @Override
    protected YamlDecimalNode createDecimalNode(BigDecimal value) {
        return new YamlDecimalNode(value);
    }

    @Override
    protected YamlIntegralNode createIntegralNode(BigInteger value) {
        return new YamlIntegralNode(value);
    }

    @Override
    public YamlMappingNode mappingNode() {
        return new YamlMappingNode(this);
    }

    @Override
    public YamlOrderedMappingNode orderedMappingNode() {
        return new YamlOrderedMappingNode(this);
    }

    @Override
    public YamlPairsNode pairsNode() {
        return new YamlPairsNode(this);
    }

    @Override
    public YamlSequenceNode sequenceNode() {
        return new YamlSequenceNode(this);
    }

    @Override
    public YamlSetNode setNode() {
        return new YamlSetNode(this);
    }

    @Override
    public YamlBooleanNode booleanNode(boolean value) {
        return YamlBooleanNode.of(value);
    }

    @Override
    protected YamlBinaryNode createBinaryNode(byte[] value) {
        return new YamlBinaryNode(value);
    }

    @Override
    public YamlNullNode nullNode() {
        return YamlNullNode.instance();
    }

    @Override
    protected YamlTimeNode createDateTimeNode(DateTime value) {
        return new YamlTimeNode(value);
    }

    public static DefaultYamlNodeFactory instance() {
        return instance;
    }

}
