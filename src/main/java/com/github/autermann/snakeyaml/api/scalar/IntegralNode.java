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

import com.google.common.base.Preconditions;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class IntegralNode extends NumberNode {

    private final BigInteger value;

    public IntegralNode(BigInteger value) {
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

}
