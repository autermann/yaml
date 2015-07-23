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
package com.github.autermann.yaml.construct;

import java.util.Objects;

import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.nodes.YamlScalarNode;
import com.google.common.io.BaseEncoding;

/**
 * Constructs a {@link com.github.autermann.yaml.nodes.YamlBinaryNode} from a
 * scalar node.
 */
public class YamlBinaryNodeConstruct extends YamlScalarNodeConstruct {

    /**
     * The encoding for {@link com.github.autermann.yaml.nodes.YamlBinaryNode}s.
     */
    private final BaseEncoding binaryEncoding;

    /**
     * Creates a new {@link YamlBinaryNodeConstruct}.
     *
     * @param nodeFactory    the node factory
     * @param delegate       the delegate
     * @param binaryEncoding the binary encoding
     */
    public YamlBinaryNodeConstruct(YamlNodeFactory nodeFactory,
                                   YamlNodeConstructor delegate,
                                   BaseEncoding binaryEncoding) {
        super(nodeFactory, delegate);
        this.binaryEncoding = Objects.requireNonNull(binaryEncoding);
    }

    @Override
    protected YamlScalarNode construct(String value) {
        return getNodeFactory().binaryNode(binaryEncoding.decode(value));
    }

}
