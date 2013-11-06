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

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.YamlNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlMissingNode extends AbstractYamlNode {
    private static final YamlMissingNode instance = new YamlMissingNode();

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
    @SuppressWarnings("unchecked")
    public <T extends YamlNode> T copy() {
        return (T) this;
    }

    @Override
    public void accept(Visitor visitor) {
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return null;
    }

    public static YamlMissingNode instance() {
        return instance;
    }

}
