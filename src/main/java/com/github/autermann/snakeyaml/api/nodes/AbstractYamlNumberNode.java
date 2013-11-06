/*
 * Copyright (C) 2013 Christian Autermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
