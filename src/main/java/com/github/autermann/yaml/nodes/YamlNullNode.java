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
package com.github.autermann.yaml.nodes;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNodeVisitor;

/**
 * A {@link com.github.autermann.yaml.YamlNode} for {@code null} values.
 *
 * @author Christian Autermann
 */
public final class YamlNullNode extends YamlScalarNode {
    /**
     * The singleton instance.
     */
    private static final YamlNullNode INSTANCE = new YamlNullNode();
    /**
     * The {@code String} value for {@link YamlNullNode}s.
     */
    private static final String TEXT_VALUE = "null";

    /**
     * Private constructor for singleton.
     */
    private YamlNullNode() {
    }

    @Override
    public Tag tag() {
        return Tag.NULL;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String asTextValue(String defaultValue) {
        return TEXT_VALUE;
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * Gets the singleton {@code null} node instance.
     *
     * @return the instance
     */
    public static YamlNullNode instance() {
        return INSTANCE;
    }
}
