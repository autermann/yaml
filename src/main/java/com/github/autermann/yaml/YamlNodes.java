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
package com.github.autermann.yaml;

import java.util.Optional;
import java.util.function.Predicate;

import com.github.autermann.yaml.nodes.YamlMissingNode;
import com.github.autermann.yaml.nodes.YamlNullNode;

/**
 * Utility methods to handle {@link YamlNode}s.
 *
 * @author Christian Autermann
 */
public class YamlNodes {

    /**
     * Private constructor for utility class.
     */
    private YamlNodes() {
    }

    /**
     * Checks if {@code value} is {@code null} and returns {@code value} or a
     * instance of {@link YamlNullNode}.
     *
     * @param value the node to test
     *
     * @return {@code value} if not {@code null}, else a {@link YamlNullNode}
     */
    public static YamlNode nullToNode(YamlNode value) {
        return Optional.ofNullable(value).orElseGet(YamlNullNode::instance);
    }

    /**
     * Checks if {@code value} is {@code null} and returns {@code value} or a
     * instance of {@link YamlMissingNode}.
     *
     * @param value the node to test
     *
     * @return {@code value} if not {@code null}, else a {@link YamlMissingNode}
     */
    public static YamlNode nullToMissing(YamlNode value) {
        return Optional.ofNullable(value).orElseGet(YamlMissingNode::instance);
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not
     * {@code null}.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notNull() {
        return node -> node != null && !node.isNull();
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not missing.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notMissing() {
        return node -> node != null && node.exists();
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not
     * {@code null} or missing.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notNullOrMissing() {
        return notNull().and(notMissing());
    }
}