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
package com.github.autermann.yaml.util;

import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

/**
 * A {@link Supplier} for {@link YamlOrderedMapNode}s backed by a
 * {@link YamlNodeFactory}.
 */
public class YamlOrderedMapNodeSupplier implements Supplier<YamlOrderedMapNode> {

    /**
     * The backing factory.
     */
    private final YamlNodeFactory factory;

    /**
     * Creates a new {@link  YamlOrderedMapNodeSupplier} for the supplied
     * factory.
     *
     * @param factory the factory to use
     */
    public YamlOrderedMapNodeSupplier(YamlNodeFactory factory) {
        this.factory = Preconditions.checkNotNull(factory);
    }

    @Override
    public YamlOrderedMapNode get() {
        return factory.orderedMapNode();
    }

}
