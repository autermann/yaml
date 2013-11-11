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

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.google.common.base.Charsets;

/**
 * A {@link com.github.autermann.yaml.YamlNode} for {@link String} values.
 *
 * @author Christian Autermann
 */
public class YamlTextNode extends YamlScalarNode {
    /**
     * The {@link String} value.
     */
    private final String value;

    /**
     * Creates a new {@link YamlTextNode}.
     *
     * @param value the value
     */
    public YamlTextNode(String value) {
        this.value = checkNotNull(value);
    }

    @Override
    public Tag tag() {
        return Tag.STR;
    }

    @Override
    public boolean isText() {
        return true;
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
        return textValue().getBytes(Charsets.UTF_8);
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
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
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
