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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.ReturningYamlNodeVisitor;
import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeVisitor;

/**
 * A {@link YamlNode} for {@link DateTime} values.
 *
 * @author Christian Autermann
 */
public class YamlTimeNode extends AbstractYamlScalarNode<DateTime> {
    /**
     * The {@link DateTime} value.
     */
    private final DateTime value;

    /**
     * Creates a new {@link YamlTimeNode}.
     *
     * @param value the value
     */
    public YamlTimeNode(DateTime value) {
        this.value = checkNotNull(value);
    }

    @Override
    public DateTime value() {
        return this.value;
    }

    @Override
    public boolean isTime() {
        return true;
    }

    @Override
    public DateTime dateTimeValue() {
        return value();
    }

    @Override
    public Date dateValue() {
        return value().toDate();
    }

    @Override
    public DateTime asDateTimeValue(DateTime defaultValue) {
        return dateTimeValue();
    }

    @Override
    public Date asDateValue(Date defaultValue) {
        return dateValue();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlTimeNode &&
               value().equals(((YamlTimeNode) o).value());
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
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
    public Tag tag() {
        return Tag.TIMESTAMP;
    }

    @Override
    public String asTextValue(String defaultValue) {
        return ISODateTimeFormat.dateTime().print(value());
    }

    @Override
    public Number asNumberValue(Number defaultValue) {
        return value().getMillis();
    }

    @Override
    public long asLongValue(long defaultValue) {
        return value().getMillis();
    }

    @Override
    public BigInteger asBigIntegerValue(BigInteger defaultValue) {
        return BigInteger.valueOf(value().getMillis());
    }

    @Override
    public BigDecimal asBigDecimalValue(BigDecimal defaultValue) {
        return BigDecimal.valueOf(value().getMillis());
    }

}
