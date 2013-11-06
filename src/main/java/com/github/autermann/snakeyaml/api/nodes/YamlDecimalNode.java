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

import org.yaml.snakeyaml.nodes.Tag;

import com.google.common.base.Preconditions;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlDecimalNode extends AbstractYamlNumberNode {

    private final BigDecimal value;

    public YamlDecimalNode(BigDecimal value) {
        this.value = Preconditions.checkNotNull(value);
    }

    @Override
    public BigDecimal numberValue() {
        return this.value;
    }

    @Override
    public Tag tag() {
        return Tag.FLOAT;
    }

    @Override
    public boolean isDecimal() {
        return true;
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return numberValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return numberValue().toBigInteger();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
