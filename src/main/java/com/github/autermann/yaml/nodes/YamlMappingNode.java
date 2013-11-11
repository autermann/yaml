/*
 * Copyright 2013 Christian Autermann
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
import java.util.Date;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;

/**
 * A abstract {@link YamlNode} for mappings.
 *
 * @param <T> the concrete type of this node
 *
 * @author Christian Autermann
 */
public abstract class YamlMappingNode<T extends YamlMappingNode<T>>
        extends YamlContainerNode {
    /**
     * The {@link MapJoiner} used in the {@link #toString()} method.
     */
    private static final MapJoiner JOINER = Joiner.on(", ")
            .withKeyValueSeparator("=");

    /**
     * Creates a new {@link YamlMappingNode}.
     *
     * @param nodeFactory the node factory to create children with
     */
    public YamlMappingNode(YamlNodeFactory nodeFactory) {
        super(nodeFactory);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, YamlNode value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds a {@link YamlOrderedMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlOrderedMapNode putOrderedMap(String key) {
        return putOrderedMap(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@link YamlPairsNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlPairsNode putPairs(String key) {
        return putPairs(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putArray(String key) {
        return putSequence(key);
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putList(String key) {
        return putSequence(key);
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putSequence(String key) {
        return putSequence(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@link YamlSetNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSetNode putSet(String key) {
        return putSet(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@link YamlMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlMapNode putObject(String key) {
        return putMap(key);
    }

    /**
     * Adds a {@link YamlMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlMapNode putMap(String key) {
        return putMap(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@link YamlOrderedMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlOrderedMapNode putOrderedMap(YamlNode key) {
        return putContainer(key, getNodeFactory().orderedMapNode());
    }

    /**
     * Adds a {@link YamlPairsNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlPairsNode putPairs(YamlNode key) {
        return putContainer(key, getNodeFactory().pairsNode());
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putArray(YamlNode key) {
        return putSequence(key);
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putList(YamlNode key) {
        return putSequence(key);
    }

    /**
     * Adds a {@link YamlSeqNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSeqNode putSequence(YamlNode key) {
        return putContainer(key, getNodeFactory().sequenceNode());
    }

    /**
     * Adds a {@link YamlSetNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlSetNode putSet(YamlNode key) {
        return putContainer(key, getNodeFactory().setNode());
    }

    /**
     * Adds a {@link YamlMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlMapNode putObject(YamlNode key) {
        return putMap(key);
    }

    /**
     * Adds a {@link YamlMapNode} under the specified {@code key}.
     *
     * @param key the key
     *
     * @return the created node
     */
    public YamlMapNode putMap(YamlNode key) {
        return putContainer(key, getNodeFactory().mapNode());
    }

    /**
     * Puts the specified container under the specified key and return
     * {@code this}.
     *
     * @param <X>   the container type
     * @param key   the key
     * @param value the container
     *
     * @return {@code this}
     */
    private <X extends YamlContainerNode> X putContainer(
            YamlNode key, X value) {
        put(key, value);
        return value;
    }

    /**
     * Adds a {@code null} value under the specified {@code key}.
     *
     * @param key the key
     *
     * @return {@code this}
     */
    public T putNull(String key) {
        return putNull(getNodeFactory().textNode(key));
    }

    /**
     * Adds a {@code null} value under the specified {@code key}.
     *
     * @param key the key
     *
     * @return {@code this}
     */
    public T putNull(YamlNode key) {
        return put(key, getNodeFactory().nullNode());
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Boolean value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Boolean value) {
        return put(key, getNodeFactory().booleanNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, boolean value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, boolean value) {
        return put(key, getNodeFactory().booleanNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Byte[] value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Byte[] value) {
        return put(key, getNodeFactory().binaryNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, byte[] value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, byte[] value) {
        return put(key, getNodeFactory().binaryNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Byte value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Byte value) {
        return put(key, getNodeFactory().byteNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, byte value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, byte value) {
        return put(key, getNodeFactory().byteNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Short value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Short value) {
        return put(key, getNodeFactory().shortNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, short value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, short value) {
        return put(key, getNodeFactory().shortNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Integer value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Integer value) {
        return put(key, getNodeFactory().intNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, int value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, int value) {
        return put(key, getNodeFactory().intNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Long value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Long value) {
        return put(key, getNodeFactory().longNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, long value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, long value) {
        return put(key, getNodeFactory().longNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, BigInteger value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, BigInteger value) {
        return put(key, getNodeFactory().bigIntegerNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Float value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Float value) {
        return put(key, getNodeFactory().floatNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, float value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, float value) {
        return put(key, getNodeFactory().floatNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Double value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Double value) {
        return put(key, getNodeFactory().doubleNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, double value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, double value) {
        return put(key, getNodeFactory().doubleNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, BigDecimal value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, BigDecimal value) {
        return put(key, getNodeFactory().bigDecimalNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, String value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, String value) {
        return put(key, getNodeFactory().textNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, DateTime value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, DateTime value) {
        return put(key, getNodeFactory().dateTimeNode(value));
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(String key, Date value) {
        return put(getNodeFactory().textNode(key), value);
    }

    /**
     * Adds the specified {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public T put(YamlNode key, Date value) {
        return put(key, getNodeFactory().dateTimeNode(value));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append("[");
        JOINER.appendTo(builder, entries());
        return builder.append("]").toString();
    }

    @Override
    public boolean has(int key) {
        return has(getNodeFactory().intNode(key));
    }

    @Override
    public boolean has(String key) {
        return has(getNodeFactory().textNode(key));
    }

    @Override
    public boolean hasNotNull(int key) {
        return hasNotNull(getNodeFactory().intNode(key));
    }

    @Override
    public boolean hasNotNull(String key) {
        return hasNotNull(getNodeFactory().textNode(key));
    }

    @Override
    public YamlNode path(int i) {
        return path(getNodeFactory().intNode(i));
    }

    @Override
    public YamlNode path(String key) {
        return path(getNodeFactory().textNode(key));
    }

    /**
     * Add the {@code key}/{@code value} pair to this mapping.
     *
     * @param key   the key
     * @param value the value
     *
     * @return {@code this}
     */
    public abstract T put(YamlNode key, YamlNode value);

    /**
     * Gets the entries of this mapping.
     *
     * @return the entries
     */
    public abstract Iterable<Entry<YamlNode, YamlNode>> entries();
}
