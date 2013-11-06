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

import static com.google.common.base.Preconditions.checkNotNull;

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
public class YamlTimeNode extends AbstractYamlScalarNode<DateTime> {
    private final DateTime value;

    public YamlTimeNode(DateTime time) {
        this.value = checkNotNull(time);
    }

    @Override
    public DateTime value() {
        return this.value;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
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
