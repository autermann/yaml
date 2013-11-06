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

import java.util.Arrays;

import org.yaml.snakeyaml.nodes.Tag;

import com.google.common.io.BaseEncoding;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlBinaryNode extends AbstractYamlScalarNode<byte[]> {
    private final byte[] value;

    public YamlBinaryNode(byte[] value) {
        this.value = checkNotNull(value);
    }

    @Override
    public byte[] value() {
        return value;
    }

    @Override
    public byte[] binaryValue() {
        return value();
    }

    @Override
    public byte[] asBinaryValue(byte[] defaultValue) {
        return value();
    }

    @Override
    public String asTextValue(String defaultValue) {
        return BaseEncoding.base64().encode(value());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(binaryValue());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof YamlBinaryNode &&
               Arrays.equals(value(), ((YamlBinaryNode) o).value());
    }

    @Override
    public Tag tag() {
        return Tag.BINARY;
    }

    @Override
    public boolean isBinary() {
        return true;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
