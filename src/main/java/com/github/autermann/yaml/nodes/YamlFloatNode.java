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


/**
 * A {@link com.github.autermann.yaml.YamlNode} for {@code float} values.
 *
 * @author Christian Autermann
 */
public class YamlFloatNode extends YamlDecimalNode {
    /**
     * The {@code float} value.
     */
    private final float value;

    /**
     * Creates a new {@link YamlFloatNode}.
     *
     * @param value the value
     */
    public YamlFloatNode(float value) {
        this.value = value;
    }

    @Override
    public Number value() {
        return this.value;
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return BigDecimal.valueOf(doubleValue());
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return (double) this.value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public boolean isFloat() {
        return true;
    }

}
