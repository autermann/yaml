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
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.joda.time.DateTime;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.YamlNodes;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public abstract class AbstractYamlSequenceNode<T extends AbstractYamlSequenceNode<T>>
        extends AbstractYamlContainerNode implements Iterable<YamlNode> {
    private static final Joiner JOINER = Joiner.on(", ");

    public AbstractYamlSequenceNode(YamlNodeFactory factory) {
        super(factory);
    }

    @Override
    public Iterator<YamlNode> iterator() {
        return Iterators.unmodifiableIterator(value().iterator());
    }

    @Override
    public int size() {
        return value().size();
    }

    @Override
    public boolean isEmpty() {
        return value().isEmpty();
    }

    @SuppressWarnings("unchecked")
    public T add(YamlNode node) {
        // small protected adding this to a collection added to this still works
        if (node == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        value().add(YamlNodes.nullToNode(node));
        return (T) this;
    }

    private <X extends AbstractYamlContainerNode> X addContainer(X node) {
        add(node);
        return node;
    }

    public YamlOrderedMappingNode addOrderedMapping() {
        return addContainer(getNodeFactory().orderedMappingNode());
    }

    public YamlPairsNode addPairs() {
        return addContainer(getNodeFactory().pairsNode());
    }

    public YamlSequenceNode addSequence() {
        return addContainer(getNodeFactory().sequenceNode());
    }

    public YamlSetNode addSet() {
        return addContainer(getNodeFactory().setNode());
    }

    public YamlMappingNode addMapping() {
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

    public T add(Date value) {
        return add(getNodeFactory().dateTimeNode(value));
    }

    public T add(DateTime value) {
        return add(getNodeFactory().dateTimeNode(value));
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
               getClass() == o.getClass() &&
               value().equals(((AbstractYamlSequenceNode<?>) o).value());
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder
                = new StringBuilder().append(getClass().getSimpleName())
                .append("[");
        return JOINER.appendTo(builder, value()).append("]").toString();
    }

    public abstract Collection<YamlNode> value();

}
