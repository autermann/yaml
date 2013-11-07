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

import java.util.Iterator;

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

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public YamlNode get(int index) {
        YamlNode v = path(index);
        return v.exists() ? v : null;
    }

    @Override
    public YamlNode get(YamlNode key) {
        YamlNode v = path(key);
        return v.exists() ? v : null;
    }

    @Override
    public YamlNode get(String key) {
        YamlNode v = path(key);
        return v.exists() ? v : null;
    }

    @Override
    public boolean hasNotNull(String key) {
        YamlNode v = path(key);
        return v.exists() && !v.isNull();
    }

    @Override
    public boolean hasNotNull(YamlNode key) {
        YamlNode v = path(key);
        return v.exists() && !v.isNull();
    }

    @Override
    public boolean hasNotNull(int key) {
        YamlNode v = path(key);
        return v.exists() && !v.isNull();
    }

    @Override
    public boolean has(YamlNode key) {
        return path(key).exists();
    }

    @Override
    public boolean has(String key) {
        return path(key).exists();
    }

    @Override
    public boolean has(int key) {
        return path(key).exists();
    }

    @Override
    public abstract int size();

    @Override
    public abstract boolean isEmpty();

    @Override
    public abstract YamlNode path(int i);

    @Override
    public abstract YamlNode path(String key);

    @Override
    public abstract YamlNode path(YamlNode key);

    @Override
    public abstract Iterator<YamlNode> iterator();
}
