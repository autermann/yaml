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

import org.yaml.snakeyaml.nodes.Tag;


/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class NullNode extends ScalarNode {
    private static final NullNode instance = new NullNode();
    private static final String TEXT_VALUE = "null";

    private NullNode() {
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
    public String textValue() {
        return TEXT_VALUE;
    }

    public static NullNode instance() {
        return instance;
    }

}
