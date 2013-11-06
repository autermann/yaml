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
import org.yaml.snakeyaml.nodes.Tag;

import com.google.common.base.Preconditions;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlIntegralNode extends AbstractYamlNumberNode {
    private final static BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    private final static BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

    private final BigInteger value;

    public YamlIntegralNode(BigInteger value) {
        this.value = Preconditions.checkNotNull(value);
    }

    @Override
    public BigInteger numberValue() {
        return this.value;
    }

    @Override
    public Tag tag() {
        return Tag.INT;
    }

    @Override
    public BigInteger bigIntegerValue() {
        return numberValue();
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return new BigDecimal(numberValue());
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
    public Date asDateValue(Date defaultValue) {
        return fitsIntoLong() ? new Date(longValue()) : defaultValue;
    }

    @Override
    public DateTime asDateTimeValue(DateTime defaultValue) {
        return fitsIntoLong() ? new DateTime(longValue()) : defaultValue;
    }

    protected boolean fitsIntoLong() {
        return value.compareTo(MIN_LONG) >= 0 &&
               value.compareTo(MAX_LONG) >= 0;
    }

}
