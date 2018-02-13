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
package com.github.autermann.yaml.construct;

import java.util.function.Supplier;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.nodes.YamlMappingNode;

/**
 * Constructs a {@link YamlMappingNode} from a mapping node.
 */
public class YamlMappingNodeConstruct extends YamlConstruct {

    /**
     * A supplier for {@link YamlMappingNode} instances.
     */
    private final Supplier<? extends YamlMappingNode<?>> supplier;

    /**
     * Creates a new {@link YamlMappingNodeConstruct} using
     * {@code supplier}
     * to create concrete instances.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     * @param supplier    the supplier
     */
    YamlMappingNodeConstruct(
            YamlNodeFactory nodeFactory,
            YamlNodeConstructor delegate,
            Supplier<? extends YamlMappingNode<?>> supplier) {
        super(nodeFactory, delegate);
        this.supplier = supplier;
    }

    @Override
    public YamlNode construct(Node node) {
        MappingNode mnode = (MappingNode) node;
        YamlMappingNode<?> mapping = supplier.get();
        for (NodeTuple tuple : mnode.getValue()) {
            Node key = tuple.getKeyNode();
            Node value = tuple.getValueNode();
            mapping.put(delegate(key), delegate(value));
        }
        return mapping;
    }

    private YamlNode delegate(Node key) {
        return (YamlNode) getDelegate().constructObject(key);
    }

}
