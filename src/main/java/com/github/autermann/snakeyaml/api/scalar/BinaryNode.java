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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.yaml.snakeyaml.nodes.Tag;

import com.google.common.io.BaseEncoding;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class BinaryNode extends ScalarNode {
    private final byte[] value;

    public BinaryNode(byte[] value) {
        this.value = checkNotNull(value);
    }

    @Override
    public byte[] binaryValue() {
        return value;
    }

    @Override
    public String asTextValue() {
        return textValue();
    }

    @Override
    public String textValue() {
        return BaseEncoding.base64().encode(binaryValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(binaryValue());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BinaryNode &&
               Arrays.equals(binaryValue(), ((BinaryNode) o).binaryValue());
    }

    @Override
    public Tag tag() {
        return Tag.BINARY;
    }

    @Override
    public boolean isBinary() {
        return true;
    }
}
