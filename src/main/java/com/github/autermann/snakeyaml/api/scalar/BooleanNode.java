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
public abstract class BooleanNode extends ScalarNode {
    private static final BooleanNode TRUE = new TrueNode();
    private static final BooleanNode FALSE = new FalseNode();

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
    public abstract boolean booleanValue();

    @Override
    public abstract Number asNumberValue(Number defaultValue);

    @Override
    public abstract BigDecimal asBigDecimalValue(BigDecimal defaultValue);

    @Override
    public abstract double asDoubleValue(double defaultValue);

    @Override
    public abstract float asFloatValue(float defaultValue);

    @Override
    public abstract BigInteger asBigIntegerValue(BigInteger defaultValue);

    @Override
    public abstract long asLongValue(long defaultValue);

    @Override
    public abstract int asIntValue(int defaultValue);

    @Override
    public abstract short asShortValue(short defaultValue);

    @Override
    public abstract byte asByteValue(byte defaultValue);

    @Override
    public abstract String asTextValue(String defaultValue);

    @Override
    public abstract boolean asBooleanValue(boolean defaultValue);

    public static ScalarNode of(Boolean value) {
        if (value == null) {
            return NullNode.instance();
        }
        return of(value.booleanValue());
    }

    public static BooleanNode of(boolean value) {
        return value ? TRUE : FALSE;
    }

    private static class TrueNode extends BooleanNode {

        private TrueNode() {
        }

        @Override
        public boolean booleanValue() {
            return true;
        }

        @Override
        public Number asNumberValue(Number defaultValue) {
            return 1;
        }

        @Override
        public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
            return BigDecimal.ONE;
        }

        @Override
        public double asDoubleValue(double defaultValue) {
            return 1.0d;
        }

        @Override
        public float asFloatValue(float defaultValue) {
            return 1.0f;
        }

        @Override
        public BigInteger asBigIntegerValue(BigInteger defaultValue) {
            return BigInteger.ONE;
        }

        @Override
        public long asLongValue(long defaultValue) {
            return 1l;
        }

        @Override
        public int asIntValue(int defaultValue) {
            return 1;
        }

        @Override
        public short asShortValue(short defaultValue) {
            return (short) 1;
        }

        @Override
        public byte asByteValue(byte defaultValue) {
            return (byte) 1;
        }

        @Override
        public String asTextValue(String defaultValue) {
            return "true";
        }

        @Override
        public boolean asBooleanValue(boolean defaultValue) {
            return true;
        }
    }

    private static class FalseNode extends BooleanNode {

        private FalseNode() {
        }

        @Override
        public boolean booleanValue() {
            return false;
        }

        @Override
        public Number asNumberValue(Number defaultValue) {
            return 0;
        }

        @Override
        public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
            return BigDecimal.ZERO;
        }

        @Override
        public double asDoubleValue(double defaultValue) {
            return 0.0d;
        }

        @Override
        public float asFloatValue(float defaultValue) {
            return 0.0f;
        }

        @Override
        public BigInteger asBigIntegerValue(BigInteger defaultValue) {
            return BigInteger.ZERO;
        }

        @Override
        public long asLongValue(long defaultValue) {
            return 0l;
        }

        @Override
        public int asIntValue(int defaultValue) {
            return 0;
        }

        @Override
        public short asShortValue(short defaultValue) {
            return (short) 0;
        }

        @Override
        public byte asByteValue(byte defaultValue) {
            return (byte) 0;
        }

        @Override
        public String asTextValue(String defaultValue) {
            return "false";
        }

        @Override
        public boolean asBooleanValue(boolean defaultValue) {
            return false;
        }
    }
}
