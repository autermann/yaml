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

import java.util.List;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.google.common.collect.Lists;

/**
 * A {@link YamlNode} representing a {@code !!seq} sequence.
 *
 * @author Christian Autermann
 */
public class YamlSeqNode extends YamlSequenceNode<YamlSeqNode> {
    /**
     * The children of this node.
     */
    private final List<YamlNode> nodes;

    /**
     * Creates a new {@link YamlSequenceNode}.
     *
     * @param factory the factory to create children
     */
    public YamlSeqNode(YamlNodeFactory factory) {
        super(factory);
        this.nodes = Lists.newArrayList();
    }

    @Override
    public boolean isSequence() {
        return true;
    }

    @Override
    public YamlSeqNode asSequence() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.SEQ;
    }

    @Override
    public List<YamlNode> value() {
        return nodes;
    }

    @Override
    public YamlSeqNode copy() {
        YamlSeqNode copy = getNodeFactory().sequenceNode();
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
}
