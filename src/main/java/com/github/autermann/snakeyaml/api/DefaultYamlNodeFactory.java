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

import com.github.autermann.snakeyaml.api.nodes.YamlBigDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBigIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlByteNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDoubleNode;
import com.github.autermann.snakeyaml.api.nodes.YamlFloatNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlLongNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlShortNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;

public class DefaultYamlNodeFactory extends YamlNodeFactory {

    protected DefaultYamlNodeFactory() {
    }

    @Override
    protected YamlTextNode createTextNode(String value) {
        return new YamlTextNode(value);
    }

    @Override
    protected YamlIntegralNode createBigIntegerNode(BigInteger value) {
        return new YamlBigIntegerNode(value);
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

    @Override
    public YamlIntegralNode byteNode(byte value) {
        return new YamlByteNode(value);
    }

    @Override
    public YamlIntegralNode shortNode(short value) {
        return new YamlShortNode(value);
    }

    @Override
    public YamlIntegralNode intNode(int value) {
        return new YamlIntegerNode(value);
    }

    @Override
    public YamlIntegralNode longNode(long value) {
        return new YamlLongNode(value);
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
    protected YamlDecimalNode createBigDecimalNode(BigDecimal value) {
        switch (getDecimalPrecision()) {
            case BIG_DECIMAL:
                return new YamlBigDecimalNode(value);
            case DOUBLE:
                return doubleNode(value.doubleValue());
            case FLOAT:
                return floatNode(value.floatValue());
            default:
                throw new Error("unknown DecimalPrecision: " +
                                getDecimalPrecision());
        }
    }

    @Override
    public YamlDecimalNode doubleNode(double value) {
        switch (getDecimalPrecision()) {
            case BIG_DECIMAL:
            case DOUBLE:
                return new YamlDoubleNode(value);
            case FLOAT:
                return floatNode((float) value);
            default:
                throw new Error("unknown DecimalPrecision: " +
                                getDecimalPrecision());
        }
    }

    @Override
    public YamlDecimalNode floatNode(float value) {
        return new YamlFloatNode(value);
    }

    public static DefaultYamlNodeFactory create() {
        return new DefaultYamlNodeFactory();
    }

}
