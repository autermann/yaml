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

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.util.Numbers;

/**
 * A {@link YamlNode} for {@code long} values.
 *
 * @author Christian Autermann
 */
public class YamlLongNode extends YamlIntegralNode {

    /**
     * The {@code long} value.
     */
    private final long value;

    /**
     * Creates a new {@link YamlLongNode}.
     *
     * @param value the value
     */
    public YamlLongNode(long value) {
        this.value = value;
    }

    @Override
    public boolean fitsIntoLong() {
        return true;
    }

    @Override
    public boolean fitsIntoInt() {
        return Numbers.fitsIntoInt(value);
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
    public long longValue() {
        return this.value;
    }

    @Override
    public Long value() {
        return this.value;
    }

}
