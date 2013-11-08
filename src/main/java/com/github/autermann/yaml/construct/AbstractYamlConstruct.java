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

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import com.google.common.base.Preconditions;

/**
 * {@link Construct} to construct {@link YamlNode}s using
 * {@link YamlNodeConstructor} and {@link YamlNodeFactory} delegates.
 */
public abstract class AbstractYamlConstruct extends AbstractConstruct {

    /**
     * The {@link YamlNodeFactory} to delegate to.
     */
    private final YamlNodeFactory nodeFactory;
    /**
     * The {@link YamlNodeConstructor} to delegate to.
     */
    private final YamlNodeConstructor delegate;

    /**
     * Creates a new {@link AbstractYamlConstruct} backed by the specified
     * {@link YamlNodeFactory} and {@link YamlNodeConstructor}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    protected AbstractYamlConstruct(YamlNodeFactory nodeFactory,
                                    YamlNodeConstructor delegate) {
        this.nodeFactory = Preconditions.checkNotNull(nodeFactory);
        this.delegate = Preconditions.checkNotNull(delegate);
    }

    /**
     * Gets the {@link YamlNodeFactory} for this construct.
     *
     * @return the node factory
     */
    public YamlNodeFactory getNodeFactory() {
        return nodeFactory;
    }

    /**
     * Gets the {@link YamlNodeConstructor} delegate of this construct.
     *
     * @return the delegate
     */
    public YamlNodeConstructor getDelegate() {
        return delegate;
    }

}
