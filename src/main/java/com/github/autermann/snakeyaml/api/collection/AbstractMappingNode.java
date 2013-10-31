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

import java.math.BigDecimal;
import java.math.BigInteger;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.github.autermann.snakeyaml.api.scalar.ScalarNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public abstract class AbstractMappingNode<T extends AbstractMappingNode<T>>
        extends ContainerNode {

    public AbstractMappingNode(NodeFactory nodeFactory) {
        super(nodeFactory);
    }

    public T put(String key, Node value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public OrderedMappingNode putOrderedMapping(String key) {
        return putOrderedMapping(getNodeFactory().textNode(key));
    }

    public PairsNode putPairs(String key) {
        return putPairs(getNodeFactory().textNode(key));
    }

    public SequenceNode putSequence(String key) {
        return putSequence(getNodeFactory().textNode(key));
    }

    public SetNode putSet(String key) {
        return putSet(getNodeFactory().textNode(key));
    }

    public MappingNode putMapping(String key) {
        return putMapping(getNodeFactory().textNode(key));
    }

    public OrderedMappingNode putOrderedMapping(Node key) {
        return putContainer(key, getNodeFactory().orderedMappingNode());
    }

    public PairsNode putPairs(Node key) {
        return putContainer(key, getNodeFactory().pairsNode());
    }

    public SequenceNode putSequence(Node key) {
        return putContainer(key, getNodeFactory().sequenceNode());
    }

    public SetNode putSet(Node key) {
        return putContainer(key, getNodeFactory().setNode());
    }

    public MappingNode putMapping(Node key) {
        return putContainer(key, getNodeFactory().mappingNode());
    }

    private <X extends ContainerNode> X putContainer(Node key, X value) {
        put(key, value);
        return value;
    }

    public T putNull(String key) {
        return putNull(getNodeFactory().textNode(key));
    }

    public T putNull(Node key) {
        return putScalar(key, getNodeFactory().nullNode());
    }

    public T put(String key, Boolean value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Boolean value) {
        return putScalar(key, getNodeFactory().booleanNode(value));
    }

    public T put(String key, boolean value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, boolean value) {
        return putScalar(key, getNodeFactory().booleanNode(value));
    }

    public T put(String key, Byte[] value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Byte[] value) {
        return putScalar(key, getNodeFactory().binaryNode(value));
    }

    public T put(String key, byte[] value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, byte[] value) {
        return putScalar(key, getNodeFactory().binaryNode(value));
    }

    public T put(String key, Byte value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Byte value) {
        return putScalar(key, getNodeFactory().byteNode(value));
    }

    public T put(String key, byte value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, byte value) {
        return putScalar(key, getNodeFactory().byteNode(value));
    }

    public T put(String key, Short value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Short value) {
        return putScalar(key, getNodeFactory().shortNode(value));
    }

    public T put(String key, short value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, short value) {
        return putScalar(key, getNodeFactory().shortNode(value));
    }

    public T put(String key, Integer value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Integer value) {
        return putScalar(key, getNodeFactory().intNode(value));
    }

    public T put(String key, int value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, int value) {
        return putScalar(key, getNodeFactory().intNode(value));
    }

    public T put(String key, Long value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Long value) {
        return putScalar(key, getNodeFactory().longNode(value));
    }

    public T put(String key, long value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, long value) {
        return putScalar(key, getNodeFactory().longNode(value));
    }

    public T put(String key, BigInteger value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, BigInteger value) {
        return putScalar(key, getNodeFactory().bigIntegerNode(value));
    }

    public T put(String key, Float value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Float value) {
        return putScalar(key, getNodeFactory().floatNode(value));
    }

    public T put(String key, float value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, float value) {
        return putScalar(key, getNodeFactory().floatNode(value));
    }

    public T put(String key, Double value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, Double value) {
        return putScalar(key, getNodeFactory().doubleNode(value));
    }

    public T put(String key, double value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, double value) {
        return putScalar(key, getNodeFactory().doubleNode(value));
    }

    public T put(String key, BigDecimal value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, BigDecimal value) {
        return putScalar(key, getNodeFactory().bigDecimalNode(value));
    }

    public T put(String key, String value) {
        return put(getNodeFactory().textNode(key), value);
    }

    public T put(Node key, String value) {
        return putScalar(key, getNodeFactory().textNode(value));
    }

    @SuppressWarnings("unchecked")
    private T putScalar(Node key, ScalarNode value) {
        put(key, value);
        return (T) this;
    }

    public boolean has(String key) {
        return has(getNodeFactory().textNode(key));
    }

    public boolean hasNotNull(String key) {
        return hasNotNull(getNodeFactory().textNode(key));
    }

    public abstract T put(Node key, Node value);

    public abstract boolean has(Node key);

    public abstract boolean hasNotNull(Node key);
}
