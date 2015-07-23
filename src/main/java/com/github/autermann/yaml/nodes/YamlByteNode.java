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

/**
 * A {@link com.github.autermann.yaml.YamlNode} for {@code byte} values.
 *
 * @author Christian Autermann
 */
public class YamlByteNode extends YamlIntegralNode {
    /**
     * The {@code byte} value.
     */
    private final byte value;

    /**
     * Creates a new {@link YamlByteNode}.
     *
     * @param value the value
     */
    public YamlByteNode(byte value) {
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
        return true;
    }

    @Override
    public boolean fitsIntoShort() {
        return true;
    }

    @Override
    public byte byteValue() {
        return this.value;
    }

    @Override
    public Byte value() {
        return this.value;
    }
}
