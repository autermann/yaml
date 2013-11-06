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
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlNodes {

    private YamlNodes() {
    }

    public static YamlNode nullToNode(YamlNode value) {
        return value == null ? YamlNullNode.instance() : value;
    }

    public static YamlNode nullToMissing(YamlNode value) {
        return value == null ? YamlMissingNode.instance() : value;
    }

    public static Predicate<YamlNode> notNull() {
        return NotNullPredicate.instance();
    }

    public static Predicate<YamlNode> notMissing() {
        return NotMissingPredicate.instance();
    }

    public static Predicate<YamlNode> notNullOrMissing() {
        return Predicates.and(notNull(), notMissing());
    }

    private static class NotNullPredicate implements Predicate<YamlNode> {
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

        public static NotNullPredicate instance() {
            return INSTANCE;
        }
    }

    private static class NotMissingPredicate implements Predicate<YamlNode> {
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

        public static NotMissingPredicate instance() {
            return INSTANCE;
        }
    }
}
