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
package com.github.autermann.snakeyaml.api.scalar;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.yaml.snakeyaml.nodes.Tag;


/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class BooleanNode extends ScalarNode {
    private static final BooleanNode TRUE = new BooleanNode(true);
    private static final BooleanNode FALSE = new BooleanNode(false);
    private final boolean value;

    private BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public String textValue() {
        return String.valueOf(booleanValue());
    }

    @Override
    public int hashCode() {
        return Boolean.valueOf(booleanValue()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }

    @Override
    public Tag tag() {
        return Tag.BOOL;
    }

    @Override
    public boolean booleanValue() {
        return value;
    }

    @Override
    public Number asNumberValue(Number defaultValue) {
        return booleanValue() ? 1 : 0;
    }

    @Override
    public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
        return booleanValue() ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    @Override
    public double asDoubleValue(double defaultValue) {
        return booleanValue() ? 1 : 0;
    }

    @Override
    public float asFloatValue(float defaultValue) {
        return booleanValue() ? 1 : 0;
    }

    @Override
    public BigInteger asBigIntegerValue(BigInteger defaultValue) {
        return booleanValue() ? BigInteger.ONE : BigInteger.ZERO;
    }

    @Override
    public long asLongValue(long defaultValue) {
        return booleanValue() ? 1 : 0;
    }

    @Override
    public int asIntValue(int defaultValue) {
        return booleanValue() ? 1 : 0;
    }

    @Override
    public short asShortValue(short defaultValue) {
        return (short) (booleanValue() ? 1 : 0);
    }

    @Override
    public byte asByteValue(byte defaultValue) {
        return (byte) (booleanValue() ? 1 : 0);
    }

    @Override
    public String asTextValue(String defaultValue) {
        return textValue();
    }

    @Override
    public boolean asBooleanValue(boolean defaultValue) {
        return booleanValue();
    }

    public static ScalarNode of(Boolean value) {
        if (value == null) {
            return NullNode.instance();
        }
        return of(value.booleanValue());
    }

    public static BooleanNode of(boolean value) {
        return value ? TRUE : FALSE;
    }
}
