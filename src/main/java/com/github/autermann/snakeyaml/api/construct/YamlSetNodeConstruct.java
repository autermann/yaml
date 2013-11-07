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
package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;

import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;

/**
 * Constructs a {@link YamlSetNode} from a mapping node.
 */
public class YamlSetNodeConstruct extends AbstractYamlConstruct {

    /**
     * Creates a new {@link YamlSetNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlSetNodeConstruct(YamlNodeFactory nodeFactory,
                                YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public YamlSetNode construct(Node node) {
        YamlSetNode set = getNodeFactory().setNode();
        for (Object o : getDelegate().constructSet((MappingNode) node)) {
            set.add((YamlNode) o);
        }
        return set;
    }

}
