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

import com.github.autermann.yaml.util.Numbers;

/**
 * A {@link com.github.autermann.yaml.YamlNode} for {@code int} values.
 *
 * @author Christian Autermann
 */
public class YamlIntegerNode extends YamlIntegralNode {
    /**
     * The {@code int} value.
     */
    private final int value;

    /**
     * Creates a new {@link YamlIntegerNode}.
     *
     * @param value the value
     */
    public YamlIntegerNode(int value) {
        this.value = value;
    }

    @Override
    public boolean fitsIntoLong() {
        return true;
    }

    @Override
    public boolean fitsIntoInt() {
        return true;
    }

    @Override
    public boolean fitsIntoByte() {
        return Numbers.fitsIntoByte(value);
    }

    @Override
    public boolean fitsIntoShort() {
        return Numbers.fitsIntoShort(value);
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
