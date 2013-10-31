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
package com.github.autermann.snakeyaml.api.collection;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;

/**
 * TODO JavaDoc
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class PairsNode extends ContainerNode {

    public PairsNode(NodeFactory factory) {
        super(factory);
    }

    @Override
    public boolean isPairs() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.PAIRS;
    }

    @Override
    public boolean equals(Object o) {
        /* TODO implement com.github.autermann.snakeyaml.api.collection.PairsNode.equals() */
        throw new UnsupportedOperationException("com.github.autermann.snakeyaml.api.collection.PairsNode.equals() not yet implemented");
    }

    @Override
    public int hashCode() {
        /* TODO implement com.github.autermann.snakeyaml.api.collection.PairsNode.hashCode() */
        throw new UnsupportedOperationException("com.github.autermann.snakeyaml.api.collection.PairsNode.hashCode() not yet implemented");
    }

    @Override
    public String toString() {
        /* TODO implement com.github.autermann.snakeyaml.api.collection.PairsNode.toString() */
        throw new UnsupportedOperationException("com.github.autermann.snakeyaml.api.collection.PairsNode.toString() not yet implemented");
    }

    @Override
    public <T extends Node> T copy() {
        /* TODO implement com.github.autermann.snakeyaml.api.collection.PairsNode.copy() */
        throw new UnsupportedOperationException("com.github.autermann.snakeyaml.api.collection.PairsNode.copy() not yet implemented");
    }

    @Override
    public int size() {
        /* TODO implement com.github.autermann.snakeyaml.api.collection.PairsNode.size() */
        throw new UnsupportedOperationException("com.github.autermann.snakeyaml.api.collection.PairsNode.size() not yet implemented");
    }
}
