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
package com.github.autermann.snakeyaml.api;

import org.yaml.snakeyaml.nodes.Tag;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class MissingNode extends BaseNode {
    private static final MissingNode instance = new MissingNode();

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return (o == this);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public Tag tag() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T copy() {
        return (T) this;
    }

    public static MissingNode instance() {
        return instance;
    }

}
