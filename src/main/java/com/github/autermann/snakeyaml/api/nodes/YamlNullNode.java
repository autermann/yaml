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

import org.yaml.snakeyaml.nodes.Tag;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class YamlNullNode extends AbstractYamlScalarNode<Object> {
    private static final YamlNullNode instance = new YamlNullNode();
    private static final String TEXT_VALUE = "null";

    private YamlNullNode() {
    }

    @Override
    public Tag tag() {
        return Tag.NULL;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String asTextValue(String defaultValue) {
        return TEXT_VALUE;
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @SuppressWarnings("unchecked")
    public static YamlNullNode instance() {
        return instance;
    }
}
