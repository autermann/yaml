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

import com.google.common.base.Objects;

/**
 * A abstract {@link com.github.autermann.yaml.YamlNode} representing a
 * {@link Number} value.
 *
 * @author Christian Autermann
 */
public abstract class AbstractYamlNumberNode extends AbstractYamlScalarNode<Number> {

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public byte asByteValue(byte defaultValue) {
        return numberValue().byteValue();
    }

    @Override
    public short asShortValue(short defaultValue) {
        return numberValue().shortValue();
    }

    @Override
    public int asIntValue(int defaultValue) {
        return numberValue().intValue();
    }

    @Override
    public long asLongValue(long defaultValue) {
        return numberValue().longValue();
    }

    @Override
    public float asFloatValue(float defaultValue) {
        return numberValue().floatValue();
    }

    @Override
    public double asDoubleValue(double defaultValue) {
        return numberValue().doubleValue();
    }

    @Override
    public Number asNumberValue(Number defaultValue) {
        return numberValue();
    }

    @Override
    public String toString() {
        return numberValue().toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberValue());
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() &&
               Objects.equal(numberValue(), ((AbstractYamlNumberNode) o)
                .numberValue());
    }

    @Override
    public String asTextValue(String defaultValue) {
        return value().toString();
    }

    @Override
    public Number numberValue() {
        return value();
    }
}
