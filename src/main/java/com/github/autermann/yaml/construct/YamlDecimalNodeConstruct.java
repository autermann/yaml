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

import java.math.BigDecimal;

import com.github.autermann.yaml.YamlNodeFactory;
import com.github.autermann.yaml.nodes.YamlScalarNode;

/**
 * Constructs a {@link com.github.autermann.yaml.nodes.YamlDecimalNode} from a
 * scalar node.
 *
 * @see com.github.autermann.yaml.nodes.YamlBigDecimalNode
 * @see com.github.autermann.yaml.nodes.YamlDoubleNode
 * @see com.github.autermann.yaml.nodes.YamlFloatNode
 */
public class YamlDecimalNodeConstruct extends YamlScalarNodeConstruct {

    /**
     * Creates a new {@link YamlDecimalNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlDecimalNodeConstruct(YamlNodeFactory nodeFactory,
                                    YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public YamlScalarNode construct(String value) {
        String v = value.trim().toLowerCase().replaceAll("_", "");
        YamlNodeFactory nodeFactory = getNodeFactory();
        switch (v) {
            case ".inf":
                return nodeFactory.doubleNode(Double.POSITIVE_INFINITY);
            case ".nan":
                return nodeFactory.doubleNode(Double.NaN);
            case "-.inf":
                return nodeFactory.doubleNode(Double.NEGATIVE_INFINITY);
        }
        return nodeFactory.bigDecimalNode(new BigDecimal(value));
    }

}
