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

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;

import com.github.autermann.yaml.nodes.AbstractYamlMappingNode;
import com.google.common.base.Supplier;

/**
 * Constructs a {@link AbstractYamlMappingNode} from a mapping node.
 */
public class YamlMappingNodeConstruct extends AbstractYamlConstruct {

    /**
     * A supplier for {@link AbstractYamlMappingNode} instances.
     */
    final Supplier<? extends AbstractYamlMappingNode<?>> supplier;

    /**
     * Creates a new {@link YamlMappingNodeConstruct} using
     * {@code supplier}
     * to create concrete instances.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     * @param supplier    the supplier
     */
    YamlMappingNodeConstruct(YamlNodeFactory nodeFactory,
                             YamlNodeConstructor delegate,
                             Supplier<? extends AbstractYamlMappingNode<?>> supplier) {
        super(nodeFactory, delegate);
        this.supplier = supplier;
    }

    @Override
    public YamlNode construct(Node node) {
        AbstractYamlMappingNode<?> mapping = supplier.get();
        MappingNode mnode = (MappingNode) node;
        for (NodeTuple tuple : mnode.getValue()) {
            mapping.put((YamlNode) getDelegate()
                    .constructObject(tuple.getKeyNode()), (YamlNode) getDelegate()
                    .constructObject(tuple.getValueNode()));
        }
        return mapping;
    }

}
