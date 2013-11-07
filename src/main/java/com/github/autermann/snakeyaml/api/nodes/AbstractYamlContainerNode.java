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

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;

/**
 * A abstract {@link YamlNode} representing a container holding child nodes.
 *
 * @author Christian Autermann
 */
public abstract class AbstractYamlContainerNode extends AbstractYamlNode {

    /**
     * The {@link YamlNodeFactory} to create children with.
     */
    private final YamlNodeFactory factory;

    /**
     * Creates a new {@link AbstractYamlContainerNode}.
     *
     * @param factory the factory to create children with
     */
    public AbstractYamlContainerNode(YamlNodeFactory factory) {
        this.factory = checkNotNull(factory);
    }

    /**
     * Gets the {@link YamlNodeFactory} to create children with.
     *
     * @return the {@link YamlNodeFactory}
     *
     */
    public YamlNodeFactory getNodeFactory() {
        return factory;
    }

    /**
     * Gets the size of this container node.
     *
     * @return the size
     */
    public abstract int size();

    /**
     * Checks if this container node is empty.
     *
     * @return {@code true} if it is empty, else {@code false}
     */
    public abstract boolean isEmpty();

    @Override
    public boolean isContainer() {
        return true;
    }
}
