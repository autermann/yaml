/*
 * Copyright 2013-2015 Christian Autermann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.autermann.yaml.nodes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodes;
import com.google.common.collect.Iterators;

/**
 * A abstract {@link YamlNode} for sequences.
 *
 * @param <T> the concrete type of this class
 *
 * @author Christian Autermann
 */
public abstract class YamlSequenceNode<T extends YamlSequenceNode<T>>
        extends YamlContainerNode {

    /**
     * Creates a new {@link YamlSequenceNode}.
     *
     * @param factory the factory to create children with
     */
    public YamlSequenceNode(YamlNodeFactory factory) {
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

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    @SuppressWarnings("unchecked")
    public T add(YamlNode value) {
        // small protection: adding this to a collection added to this still works
        if (value == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        value().add(YamlNodes.nullToNode(value));
        return (T) this;
    }

    /**
     * Adds all specified values to this sequence.
     *
     * @param values the values
     *
     * @return {@code this}
     */
    @SuppressWarnings("unchecked")
    public T addAll(YamlNode... values) {
        for (YamlNode value : values) {
            add(value);
        }
        return (T) this;
    }

    /**
     * Adds all specified values to this sequence.
     *
     * @param values the values
     *
     * @return {@code this}
     */
    @SuppressWarnings("unchecked")
    public T addAll(Iterable<? extends YamlNode> values) {
        for (YamlNode value : values) {
            add(value);
        }
        return (T) this;
    }

    /**
     * Adds a container to this sequence and returns the added value.
     *
     * @param <X>  the container type
     * @param node the container
     *
     * @return the added container
     */
    private <X extends YamlContainerNode> X addContainer(X node) {
        add(node);
        return node;
    }

    /**
     * Adds a {@link YamlOrderedMapNode} to this sequence.
     *
     * @return the added value
     */
    public YamlOrderedMapNode addOrderedMap() {
        return addContainer(getNodeFactory().orderedMapNode());
    }

    /**
     * Adds a {@link YamlPairsNode} to this sequence.
     *
     * @return the added value
     */
    public YamlPairsNode addPairs() {
        return addContainer(getNodeFactory().pairsNode());
    }

    /**
     * Adds a {@link YamlSeqNode} to this sequence.
     *
     * @return the added value
     */
    public YamlSeqNode addSequence() {
        return addContainer(getNodeFactory().sequenceNode());
    }

    /**
     * Adds a {@link YamlSetNode} to this sequence.
     *
     * @return the added value
     */
    public YamlSetNode addSet() {
        return addContainer(getNodeFactory().setNode());
    }

    /**
     * Adds a {@link YamlMapNode} to this sequence.
     *
     * @return the added value
     */
    public YamlMapNode addMap() {
        return addContainer(getNodeFactory().mapNode());
    }

    /**
     * Adds a {@link YamlNullNode} to this sequence.
     *
     * @return {@code this}
     */
    public T addNull() {
        return add(getNodeFactory().nullNode());
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Boolean value) {
        return add(getNodeFactory().booleanNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(boolean value) {
        return add(getNodeFactory().booleanNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Byte[] value) {
        return add(getNodeFactory().binaryNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(byte[] value) {
        return add(getNodeFactory().binaryNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Byte value) {
        return add(getNodeFactory().byteNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(byte value) {
        return add(getNodeFactory().byteNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Short value) {
        return add(getNodeFactory().shortNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(short value) {
        return add(getNodeFactory().shortNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Integer value) {
        return add(getNodeFactory().intNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(int value) {
        return add(getNodeFactory().intNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Long value) {
        return add(getNodeFactory().longNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(long value) {
        return add(getNodeFactory().longNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(BigInteger value) {
        return add(getNodeFactory().bigIntegerNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Float value) {
        return add(getNodeFactory().floatNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(float value) {
        return add(getNodeFactory().floatNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Double value) {
        return add(getNodeFactory().doubleNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(double value) {
        return add(getNodeFactory().doubleNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(BigDecimal value) {
        return add(getNodeFactory().bigDecimalNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(String value) {
        return add(getNodeFactory().textNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(Date value) {
        return add(getNodeFactory().dateTimeNode(value));
    }

    /**
     * Adds the specified value to this sequence.
     *
     * @param value the value
     *
     * @return {@code this}
     */
    public T add(DateTime value) {
        return add(getNodeFactory().dateTimeNode(value));
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
               getClass() == o.getClass() &&
               value().equals(((YamlSequenceNode<?>) o).value());
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }

    @Override
    public String toString() {
        String pre = getClass().getSimpleName() + "[";
        String post = "]";
        String delim = ",";
        return value().stream().map(String::valueOf)
                .collect(Collectors.joining(delim, pre, post));
    }

    @Override
    public YamlNode path(String key) {
        return path(getNodeFactory().textNode(key));
    }

    @Override
    public YamlNode path(YamlNode key) {
        return path(YamlNodes.nullToNode(key).asIntValue(-1));
    }

    @Override
    public YamlNode path(int index) {
        if (index < 0) {
            return YamlMissingNode.instance();
        }
        return value().stream().skip(index)
                .findFirst().orElseGet(YamlMissingNode::instance);
    }

    /**
     * Gets the backing collection of this value.
     *
     * @return the backing collection
     */
    public abstract Collection<YamlNode> value();
}
