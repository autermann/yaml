/*
 * Copyright (C) 2013 Christian Autermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
