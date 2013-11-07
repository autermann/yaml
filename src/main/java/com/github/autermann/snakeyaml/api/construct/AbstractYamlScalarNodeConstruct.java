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

import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;

/**
 * {@link Construct} to construct {@link AbstractYamlScalarNode}s from
 * string scalar nodes.
 */
public abstract class AbstractYamlScalarNodeConstruct extends AbstractYamlConstruct {

    /**
     * Creates a new {@link AbstractYamlScalarNode} backed by the specified
     * {@link YamlNodeFactory} and {@link YamlNodeConstructor}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public AbstractYamlScalarNodeConstruct(YamlNodeFactory nodeFactory,
                                           YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public Object construct(Node node) {
        return construct(((ScalarNode) node).getValue());
    }

    /**
     * Construct a {@link YamlNode} from the specified scalar value.
     *
     * @param value the value of the scalar
     *
     * @return the {@link YamlNode}
     */
    protected abstract AbstractYamlScalarNode<?> construct(String value);

}
