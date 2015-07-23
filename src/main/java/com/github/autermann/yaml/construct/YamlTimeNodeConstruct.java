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

import org.joda.time.format.DateTimeFormatter;

import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.nodes.YamlScalarNode;

/**
 * Constructs a {@link com.github.autermann.yaml.nodes.YamlTimeNode} from a
 * scalar node.
 */
public class YamlTimeNodeConstruct extends YamlScalarNodeConstruct {

    /**
     * The encoding for {@link com.github.autermann.yaml.nodes.YamlTimeNode}s.
     */
    private final DateTimeFormatter timeEncoding;

    /**
     * Creates a new {@link YamlTimeNodeConstruct}.
     *
     * @param nodeFactory  the node factory
     * @param delegate     the delegate
     * @param timeEncoding the time encoding
     */
    public YamlTimeNodeConstruct(YamlNodeFactory nodeFactory,
                                 YamlNodeConstructor delegate,
                                 DateTimeFormatter timeEncoding) {
        super(nodeFactory, delegate);
        this.timeEncoding = Objects.requireNonNull(timeEncoding);
    }

    @Override
    protected YamlScalarNode construct(String value) {
        return getNodeFactory().dateTimeNode(timeEncoding.parseDateTime(value));
    }

}
