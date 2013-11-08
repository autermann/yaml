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
package com.github.autermann.yaml.nodes;

import java.math.BigDecimal;

import com.github.autermann.yaml.YamlNode;

/**
 * A {@link YamlNode} for {@code double} values.
 *
 * @author Christian Autermann
 */
public class YamlDoubleNode extends YamlDecimalNode {
    /**
     * The {@code double} value.
     */
    private final double value;

    /**
     * Creates a new {@link YamlDoubleNode}.
     *
     * @param value the value
     */
    public YamlDoubleNode(double value) {
        this.value = value;
    }

    @Override
    public Number value() {
        return this.value;
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return BigDecimal.valueOf(value);
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}
