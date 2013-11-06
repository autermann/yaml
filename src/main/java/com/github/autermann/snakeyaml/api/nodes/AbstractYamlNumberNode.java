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

import java.math.BigDecimal;
import java.math.BigInteger;

import com.google.common.base.Objects;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public abstract class AbstractYamlNumberNode extends AbstractYamlScalarNode<Number> {

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public byte byteValue() {
        return numberValue().byteValue();
    }

    @Override
    public byte asByteValue(byte defaultValue) {
        return byteValue();
    }

    @Override
    public short shortValue() {
        return numberValue().shortValue();
    }

    @Override
    public short asShortValue(short defaultValue) {
        return shortValue();
    }

    @Override
    public int intValue() {
        return numberValue().intValue();
    }

    @Override
    public int asIntValue(int defaultValue) {
        return intValue();
    }

    @Override
    public long longValue() {
        return numberValue().longValue();
    }

    @Override
    public long asLongValue(long defaultValue) {
        return longValue();
    }

    @Override
    public float floatValue() {
        return numberValue().floatValue();
    }

    @Override
    public float asFloatValue(float defaultValue) {
        return floatValue();
    }

    @Override
    public double doubleValue() {
        return numberValue().doubleValue();
    }

    @Override
    public double asDoubleValue(double defaultValue) {
        return doubleValue();
    }

    @Override
    public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
        return bigDecimalValue();
    }

    @Override
    public Number asNumberValue(Number defaultValue) {
        return numberValue();
    }

    @Override
    public BigInteger asBigIntegerValue(BigInteger defaultValue) {
        return bigIntegerValue();
    }

    @Override
    public String asTextValue(String defaultValue) {
        return textValue();
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
    public Number value() {
        return numberValue();
    }
}
