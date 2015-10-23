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
package com.github.autermann.yaml.nodes;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeVisitor;

/**
 * A {@link YamlNode} representing a missing (non-existing) node.
 *
 * @author Christian Autermann
 */
public final class YamlMissingNode implements YamlNode {
    /**
     * The singleton instance.
     */
    private static final YamlMissingNode INSTANCE = new YamlMissingNode();

    /**
     * Private constructor for singleton.
     */
    private YamlMissingNode() {
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return (o == this);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public Tag tag() {
        return null;
    }

    @Override
    public YamlMissingNode copy() {
        return this;
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return null;
    }

    @Override
    public Stream<YamlNode> stream() {
        return Stream.empty();
    }

    @Override
    public Spliterator<YamlNode> spliterator() {
        return Spliterators.emptySpliterator();
    }

    /**
     * Gets the singleton missing node instance.
     *
     * @return the instance
     */
    public static YamlMissingNode instance() {
        return INSTANCE;
    }

}
