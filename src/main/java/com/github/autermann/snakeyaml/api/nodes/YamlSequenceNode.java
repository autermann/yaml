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
package com.github.autermann.snakeyaml.api.nodes;

import java.util.List;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.google.common.collect.Lists;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlSequenceNode extends AbstractYamlSequenceNode<YamlSequenceNode> {
    private final List<YamlNode> nodes;

    public YamlSequenceNode(YamlNodeFactory factory) {
        super(factory);
        this.nodes = Lists.newArrayList();
    }

    @Override
    public boolean isSequence() {
        return true;
    }

    @Override
    public YamlSequenceNode asSequence() {
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
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlSequenceNode copy = getNodeFactory().sequenceNode();
        for (YamlNode node : this) {
            copy.add(node.copy());
        }
        return (T) copy;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public YamlNode path(int index) {
        if (index < 0 || index >= size()) {
            return YamlMissingNode.instance();
        }
        return value().get(index);
    }

    @Override
    public YamlNode get(int index) {
        return value().get(index);
    }

}
