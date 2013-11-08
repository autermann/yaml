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

import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.YamlNodeVisitor;
import com.google.common.collect.Maps;

/**
 * A {@link YamlNode} for {@literal !!omap} mappings.
 *
 * @author Christian Autermann
 */
public class YamlOrderedMapNode extends YamlMapNode {

    /**
     * Creates a new {@link YamlOrderedMapNode}.
     *
     * @param factory the factory to create children with
     */
    public YamlOrderedMapNode(YamlNodeFactory factory) {
        super(factory, Maps.<YamlNode, YamlNode>newLinkedHashMap());
    }

    @Override
    public boolean isOrderedMap() {
        return true;
    }

    @Override
    public YamlOrderedMapNode asOrderedMap() {
        return this;
    }

    @Override
    public Tag tag() {
        return Tag.OMAP;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlOrderedMapNode copy = getNodeFactory().orderedMapNode();
        for (Entry<YamlNode, YamlNode> e : entries()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return (T) copy;
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
