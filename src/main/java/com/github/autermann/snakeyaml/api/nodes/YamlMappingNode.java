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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.YamlNodes;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlMappingNode extends AbstractYamlMappingNode<YamlMappingNode> {
    private final Map<YamlNode, YamlNode> nodes;

    protected YamlMappingNode(YamlNodeFactory factory,
                              Map<YamlNode, YamlNode> nodes) {
        super(factory);
        this.nodes = checkNotNull(nodes);
    }

    public YamlMappingNode(YamlNodeFactory factory) {
        this(factory, Maps.<YamlNode, YamlNode>newHashMap());
    }

    @Override
    public YamlMappingNode put(YamlNode key, YamlNode value) {
        // small protected adding this to a collection added to this still works
        if (key == this || value == this) {
            throw new IllegalArgumentException("recursive structures are currently not supported");
        }
        value().put(YamlNodes.nullToNode(key), YamlNodes.nullToNode(value));
        return this;
    }

    @Override
    public boolean has(YamlNode key) {
        return value().containsKey(YamlNodes.nullToNode(key));
    }

    @Override
    public boolean hasNotNull(YamlNode key) {
        YamlNode k = YamlNodes.nullToNode(key);
        return has(k) && !value().get(k).isNull();
    }

    public YamlNode get(String key) {
        return get(getNodeFactory().textNode(key));
    }

    public YamlNode get(YamlNode key) {
        return value().get(YamlNodes.nullToNode(key));
    }

    public YamlNode path(String key) {
        return path(getNodeFactory().textNode(key));
    }

    public YamlNode path(YamlNode key) {
        return YamlNodes.nullToMissing(get(YamlNodes.nullToNode(key)));
    }

    @Override
    public boolean isMapping() {
        return true;
    }

    @Override
    public Tag tag() {
        return Tag.MAP;
    }

    @Override
    public YamlMappingNode asMapping() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlMappingNode &&
               value().equals(((YamlMappingNode) o).value());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        YamlMappingNode copy = getNodeFactory().mappingNode();
        for (Entry<YamlNode, YamlNode> e : entries()) {
            copy.put(e.getKey().copy(), e.getValue().copy());
        }
        return (T) copy;
    }

    protected Map<YamlNode, YamlNode> value() {
        return nodes;
    }

    @Override
    public int size() {
        return value().size();
    }

    @Override
    public boolean isEmpty() {
        return value().isEmpty();
    }

    @Override
    public Collection<Entry<YamlNode, YamlNode>> entries() {
        return value().entrySet();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
