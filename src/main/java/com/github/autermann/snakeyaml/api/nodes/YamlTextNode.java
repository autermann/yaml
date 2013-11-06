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
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlTextNode extends AbstractYamlScalarNode<String> {
    private final String value;

    public YamlTextNode(String value) {
        this.value = value;
    }

    @Override
    public Tag tag() {
        return Tag.STR;
    }

    @Override
    public String textValue() {
        return value;
    }

    @Override
    public String asTextValue(String defaultValue) {
        return textValue();
    }

    @Override
    public short asShortValue(short defaultValue) {
        try {
            return Short.parseShort(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public Number asNumberValue(Number defaultValue) {
        try {
            return new BigDecimal(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public long asLongValue(long defaultValue) {
        try {
            return Long.parseLong(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public int asIntValue(int defaultValue) {
        try {
            return Integer.parseInt(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public float asFloatValue(float defaultValue) {
        try {
            return Float.parseFloat(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public double asDoubleValue(double defaultValue) {
        try {
            return Double.parseDouble(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public byte[] asBinaryValue(byte[] defaultValue) {
        return textValue().getBytes();
    }

    @Override
    public byte asByteValue(byte defaultValue) {
        try {
            return Byte.parseByte(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean asBooleanValue(boolean defaultValue) {
        return Boolean.parseBoolean(textValue());
    }

    @Override
    public BigInteger asBigIntegerValue(BigInteger defaultValue) {
        try {
            return new BigInteger(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
        try {
            return new BigDecimal(textValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public int hashCode() {
        return textValue().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlTextNode &&
               textValue().equals(((YamlTextNode) o).textValue());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String value() {
        return textValue();
    }

    @Override
    public Date asDateValue(Date defaultValue) {
        DateTime dt = asDateTimeValue(null);
        return dt == null ? defaultValue : dt.toDate();
    }

    @Override
    public DateTime asDateTimeValue(DateTime defaultValue) {
        try {
            return ISODateTimeFormat.dateTime().parseDateTime(textValue());
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }
}