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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;

/**
 * A {@link YamlNode} for {@code !!set} mappings.
 *
 * @author Christian Autermann
 */
public class YamlSetNode extends YamlSequenceNode<YamlSetNode> {
    /**
     * The children of this node.
     */
    private final LinkedHashSet<YamlNode> nodes;

    /**
     * Create a new {@link YamlSetNode}.
     *
     * @param factory the factory to create children
     */
    public YamlSetNode(YamlNodeFactory factory) {
        super(factory);
        this.nodes = new LinkedHashSet<>();
    }

    @Override
    public boolean isSet() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.SET;
    }

    @Override
    public YamlSetNode asSet() {
        return this;
    }

    @Override
    public Set<YamlNode> value() {
        return this.nodes;
    }

    @Override
    public YamlSetNode copy() {
        YamlSetNode copy = getNodeFactory().setNode();
        for (YamlNode node : this) {
            copy.add(node.copy());
        }
        return copy;
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Spliterator<YamlNode> spliterator() {
        return Spliterators.spliterator(value(), Spliterator.DISTINCT |
                                                 Spliterator.NONNULL);
    }
}
