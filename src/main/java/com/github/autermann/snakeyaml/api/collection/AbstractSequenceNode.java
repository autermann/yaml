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
import java.util.Collection;
import java.util.Iterator;

import com.github.autermann.snakeyaml.api.Node;
import com.github.autermann.snakeyaml.api.NodeFactory;
import com.github.autermann.snakeyaml.api.Nodes;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public abstract class AbstractSequenceNode<T extends AbstractSequenceNode<T>>
        extends ContainerNode implements Iterable<Node> {
    private static final Joiner JOINER = Joiner.on(", ");

    public AbstractSequenceNode(NodeFactory factory) {
        super(factory);
        getNodeFactory().sequenceNode().addMapping()
                .putSequence("hallo").add("adsf").add(true);
    }

    @Override
    public Iterator<Node> iterator() {
        return Iterators.unmodifiableIterator(getNodes().iterator());
    }

    @Override
    public int size() {
        return getNodes().size();
    }

    @SuppressWarnings("unchecked")
    public T add(Node node) {
        getNodes().add(Nodes.nullToNode(node));
        return (T) this;
    }

    private <X extends ContainerNode> X addContainer(X node) {
        add(node);
        return node;
    }

    public OrderedMappingNode addOrderedMapping() {
        return addContainer(getNodeFactory().orderedMappingNode());
    }

    public PairsNode addPairs() {
        return addContainer(getNodeFactory().pairsNode());
    }

    public SequenceNode addSequence() {
        return addContainer(getNodeFactory().sequenceNode());
    }

    public SetNode addSet() {
        return addContainer(getNodeFactory().setNode());
    }

    public MappingNode addMapping() {
        return addContainer(getNodeFactory().mappingNode());
    }

    public T addNull() {
        return add(getNodeFactory().nullNode());
    }

    public T add(Boolean value) {
        return add(getNodeFactory().booleanNode(value));
    }

    public T add(boolean value) {
        return add(getNodeFactory().booleanNode(value));
    }

    public T add(Byte[] value) {
        return add(getNodeFactory().binaryNode(value));
    }

    public T add(byte[] value) {
        return add(getNodeFactory().binaryNode(value));
    }

    public T add(Byte value) {
        return add(getNodeFactory().byteNode(value));
    }

    public T add(byte value) {
        return add(getNodeFactory().byteNode(value));
    }

    public T add(Short value) {
        return add(getNodeFactory().shortNode(value));
    }

    public T add(short value) {
        return add(getNodeFactory().shortNode(value));
    }

    public T add(Integer value) {
        return add(getNodeFactory().intNode(value));
    }

    public T add(int value) {
        return add(getNodeFactory().intNode(value));
    }

    public T add(Long value) {
        return add(getNodeFactory().longNode(value));
    }

    public T add(long value) {
        return add(getNodeFactory().longNode(value));
    }

    public T add(BigInteger value) {
        return add(getNodeFactory().bigIntegerNode(value));
    }

    public T add(Float value) {
        return add(getNodeFactory().floatNode(value));
    }

    public T add(float value) {
        return add(getNodeFactory().floatNode(value));
    }

    public T add(Double value) {
        return add(getNodeFactory().doubleNode(value));
    }

    public T add(double value) {
        return add(getNodeFactory().doubleNode(value));
    }

    public T add(BigDecimal value) {
        return add(getNodeFactory().bigDecimalNode(value));
    }

    public T add(String value) {
        return add(getNodeFactory().textNode(value));
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
               getClass() == o.getClass() &&
               getNodes().equals(((SequenceNode) o).getNodes());
    }

    @Override
    public int hashCode() {
        return getNodes().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder
                = new StringBuilder().append(getClass().getSimpleName())
                .append("[");
        return JOINER.appendTo(builder, getNodes()).append("]").toString();
    }

    protected abstract Collection<Node> getNodes();

}
