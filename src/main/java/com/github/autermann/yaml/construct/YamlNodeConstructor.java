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
package com.github.autermann.yaml.construct;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.Yaml;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.google.common.io.BaseEncoding;

/**
 * Constructor for {@link YamlNode} classes.
 *
 * @author Christian Autermann
 */
public class YamlNodeConstructor extends SafeConstructor {
    /**
     * The dumper options the {@link Yaml} associated with this constructor is
     * using.
     */
    private final DumperOptions options;
    /**
     * The {@link YamlNodeFactory} of this constructor.
     */
    private final YamlNodeFactory nodeFactory;

    /**
     * Creates a new {@link YamlNodeConstructor} using a default
     * {@link YamlNodeFactory} and {@link DumperOptions}.
     */
    public YamlNodeConstructor() {
        this(YamlNodeFactory.createDefault(), new DumperOptions());
    }

    /**
     * Creates a new {@link YamlNodeConstructor} using a default
     * {@link YamlNodeFactory} and the supplied {@link DumperOptions}.
     *
     * @param dumperOptions the dumper options
     */
    public YamlNodeConstructor(DumperOptions dumperOptions) {
        this(YamlNodeFactory.createDefault(), dumperOptions);
    }

    /**
     * Creates a new {@link YamlNodeConstructor} using the supplied
     * {@link YamlNodeFactory} and default {@link DumperOptions}.
     *
     * @param nodeFactory the node factory
     */
    public YamlNodeConstructor(YamlNodeFactory nodeFactory) {
        this(nodeFactory, new DumperOptions());
    }

    /**
     * Creates a new {@link YamlNodeConstructor} using the supplied
     * {@link YamlNodeFactory} and {@link DumperOptions}.
     *
     * @param nodeFactory the node factory
     * @param options     the dumper options
     */
    public YamlNodeConstructor(YamlNodeFactory nodeFactory,
                               DumperOptions options) {
        this.options = checkNotNull(options);
        this.nodeFactory = checkNotNull(nodeFactory);
        register();
    }

    /**
     * Register the {@code Construct}s of this {@code Constructor}.
     */
    private void register() {
        this.yamlConstructors.put(Tag.MAP, mapConstruct());
        this.yamlConstructors.put(Tag.OMAP, omapConstruct());
        this.yamlConstructors.put(Tag.PAIRS, pairsConstruct());
        this.yamlConstructors.put(Tag.SEQ, seqConstruct());
        this.yamlConstructors.put(Tag.SET, setConstruct());
        this.yamlConstructors.put(Tag.BINARY, binaryConstruct());
        this.yamlConstructors.put(Tag.BOOL, boolConstruct());
        this.yamlConstructors.put(Tag.FLOAT, floatConstruct());
        this.yamlConstructors.put(Tag.INT, intConstruct());
        this.yamlConstructors.put(Tag.NULL, nullConstruct());
        this.yamlConstructors.put(Tag.STR, strConstruct());
        this.yamlConstructors.put(Tag.TIMESTAMP, timestampConstruct());
    }

    /**
     * Creates a new {@link Construct} for {@code !!set}.
     *
     * @return the construct
     */
    protected Construct setConstruct() {
        return new YamlSetNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!timestamp}.
     *
     * @return the construct
     */
    protected Construct timestampConstruct() {
        DateTimeFormatter encoding = ISODateTimeFormat.dateTime();
        return new YamlTimeNodeConstruct(getNodeFactory(), this, encoding);
    }

    /**
     * Creates a new {@link Construct} for {@code !!str}.
     *
     * @return the construct
     */
    protected Construct strConstruct() {
        return new YamlTextNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!null}.
     *
     * @return the construct
     */
    protected Construct nullConstruct() {
        return new YamlNullNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!int}.
     *
     * @return the construct
     */
    protected Construct intConstruct() {
        return new YamlIntegralConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!float}.
     *
     * @return the construct
     */
    protected Construct floatConstruct() {
        return new YamlDecimalNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!bool}.
     *
     * @return the construct
     */
    protected Construct boolConstruct() {
        return new YamlBooleanNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!binary}.
     *
     * @return the construct
     */
    protected Construct binaryConstruct() {
        String linebreak = getOptions().getLineBreak().getString();
        int width = getOptions().getWidth();
        BaseEncoding encoding = BaseEncoding.base64()
                .withSeparator(linebreak, width);
        return new YamlBinaryNodeConstruct(getNodeFactory(), this, encoding);
    }

    /**
     * Creates a new {@link Construct} for {@code !!seq}.
     *
     * @return the construct
     */
    protected Construct seqConstruct() {
        return new YamlSequenceNodeConstruct(getNodeFactory(), this);
    }

    /**
     * Creates a new {@link Construct} for {@code !!pairs}.
     *
     * @return the construct
     */
    protected Construct pairsConstruct() {
        return new YamlMappingNodeConstruct(
                getNodeFactory(), this, getNodeFactory().pairsNodeSupplier());
    }

    /**
     * Creates a new {@link Construct} for {@code !!omap}.
     *
     * @return the construct
     */
    protected Construct omapConstruct() {
        return new YamlMappingNodeConstruct(
                getNodeFactory(), this, getNodeFactory()
                .orderedMapNodeSupplier());
    }

    /**
     * Creates a new {@link Construct} for {@code !!map}.
     *
     * @return the construct
     */
    protected Construct mapConstruct() {
        return new YamlMappingNodeConstruct(
                getNodeFactory(), this, getNodeFactory().mapNodeSupplier());
    }

    /**
     * Gets the {@link DumperOptions} of this constructor.
     *
     * @return the dumper options
     */
    public DumperOptions getOptions() {
        return options;
    }

    /**
     * Gets the {@link YamlNodeFactory} of this constructor.
     *
     * @return the node factory
     */
    public YamlNodeFactory getNodeFactory() {
        return nodeFactory;
    }

    @Override
    public Object constructObject(Node node) {
        return super.constructObject(node);
    }

    /**
     * Constructs a new array.
     *
     * @param node the node to construct from
     *
     * @return the constructed array
     *
     * @see SafeConstructor#constructArray(SequenceNode)
     */
    @Override
    public Object constructArray(SequenceNode node) {
        return super.constructArray(node);
    }

    /**
     * Constructs a mapping.
     *
     * @param node the node to construct from
     *
     * @return the constructed mapping
     *
     * @see SafeConstructor#constructMapping(MappingNode)
     */
    @Override
    public Map<Object, Object> constructMapping(MappingNode node) {
        return super.constructMapping(node);
    }

    /**
     * Constructs a set.
     *
     * @param node the node to construct from
     *
     * @return the constructed set
     *
     * @see SafeConstructor#constructSet(SequenceNode)
     */
    @Override
    public Set<Object> constructSet(MappingNode node) {
        return super.constructSet(node);
    }

    /**
     * Constructs a set.
     *
     * @param node the node to construct from
     *
     * @return the constructed set
     *
     * @see SafeConstructor#constructSet(SequenceNode)
     */
    @Override
    public Set<? extends Object> constructSet(SequenceNode node) {
        return super.constructSet(node);
    }

    /**
     * Constructs a sequence.
     *
     * @param node the node to construct from
     *
     * @return the constructed sequence
     *
     * @see SafeConstructor#constructSequence(SequenceNode)
     */
    @Override
    public List<? extends Object> constructSequence(SequenceNode node) {
        return super.constructSequence(node);
    }

    /**
     * Constructs a scalar.
     *
     * @param node the node to construct from
     *
     * @return the constructed scalar
     *
     * @see SafeConstructor#constructScalar(ScalarNode)
     */
    @Override
    public Object constructScalar(ScalarNode node) {
        return super.constructScalar(node);
    }
}
