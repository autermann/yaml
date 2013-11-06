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

import com.github.autermann.snakeyaml.api.YamlNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public abstract class AbstractYamlScalarNode<T> extends AbstractYamlNode {

    @Override
    public boolean isScalar() {
        return true;
    }

    @Override
    public String toString() {
        return asTextValue();
    }

    @Override
    public abstract String asTextValue(String defaultValue);

    public abstract T value();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        // subclasses are immutable
        return (T) this;
    }

}
