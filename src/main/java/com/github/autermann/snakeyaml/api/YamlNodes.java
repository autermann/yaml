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
package com.github.autermann.snakeyaml.api;

import com.github.autermann.snakeyaml.api.nodes.YamlMissingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

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
        return value == null ? YamlNullNode.instance() : value;
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
        return value == null ? YamlMissingNode.instance() : value;
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not
     * {@code null}.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notNull() {
        return NotNullPredicate.instance();
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not missing.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notMissing() {
        return NotMissingPredicate.instance();
    }

    /**
     * Creates a {@link Predicate} for {@link YamlNode}s that are not
     * {@code null} or missing.
     *
     * @return the predicate
     */
    public static Predicate<YamlNode> notNullOrMissing() {
        return Predicates.and(notNull(), notMissing());
    }

    /**
     * {@link Predicate} for {@link YamlNode}s that are not {@code null}.
     */
    private static class NotNullPredicate implements Predicate<YamlNode> {
        /**
         * The singleton instance.
         */
        private static final NotNullPredicate INSTANCE
                = new NotNullPredicate();

        @Override
        public boolean apply(YamlNode input) {
            return input != null && !input.isNull();
        }

        @Override
        public String toString() {
            return "YamlNodes.notNull()";
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        /**
         * Gets the singleton instance.
         *
         * @return the instance
         */
        public static NotNullPredicate instance() {
            return INSTANCE;
        }
    }

    /**
     * {@link Predicate} for {@link YamlNode}s that are not missing.
     */
    private static class NotMissingPredicate implements Predicate<YamlNode> {
        /**
         * The singleton instance.
         */
        private static final NotMissingPredicate INSTANCE
                = new NotMissingPredicate();

        @Override
        public boolean apply(YamlNode input) {
            return input != null && input.exists();
        }

        @Override
        public String toString() {
            return "YamlNodes.notNullOrMissing()";
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        /**
         * Gets the singleton instance.
         *
         * @return the instance
         */
        public static NotMissingPredicate instance() {
            return INSTANCE;
        }
    }
}
