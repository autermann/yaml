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

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigInteger;

import com.github.autermann.snakeyaml.api.util.Numbers;

public class YamlBigIntegerNode extends YamlIntegralNode {
    private final BigInteger value;

    public YamlBigIntegerNode(BigInteger value) {
        this.value = checkNotNull(value);
    }

    @Override
    public boolean fitsIntoLong() {
        return Numbers.fitsIntoLong(value);
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
    public BigInteger bigIntegerValue() {
        return this.value;
    }

    @Override
    public BigInteger value() {
        return this.value;
    }

}
