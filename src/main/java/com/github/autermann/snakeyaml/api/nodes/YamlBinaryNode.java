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

import java.util.Arrays;

import org.yaml.snakeyaml.nodes.Tag;

import com.google.common.io.BaseEncoding;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class YamlBinaryNode extends AbstractYamlScalarNode<byte[]> {
    private final byte[] value;

    public YamlBinaryNode(byte[] value) {
        this.value = checkNotNull(value);
    }

    @Override
    public byte[] value() {
        return value;
    }

    @Override
    public byte[] binaryValue() {
        return value();
    }

    @Override
    public String asTextValue(String defaultValue) {
        return BaseEncoding.base64().encode(value());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(binaryValue());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlBinaryNode &&
               Arrays.equals(value(), ((YamlBinaryNode) o).value());
    }

    @Override
    public Tag tag() {
        return Tag.BINARY;
    }

    @Override
    public boolean isBinary() {
        return true;
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
