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
package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import java.math.BigInteger;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBigIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlByteNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegerNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlLongNode;
import com.github.autermann.snakeyaml.api.nodes.YamlShortNode;
import com.github.autermann.snakeyaml.api.util.Numbers;

/**
 * Constructs a {@link YamlIntegralNode} from a scalar node.
 *
 * @see YamlByteNode
 * @see YamlShortNode
 * @see YamlIntegerNode
 * @see YamlLongNode
 * @see YamlBigIntegerNode
 */
public class YamlIntegralConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * Creates a new {@link YamlIntegralConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlIntegralConstruct(YamlNodeFactory nodeFactory,
                                 YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public AbstractYamlScalarNode<?> construct(String value) {
        BigInteger number = new BigInteger(value);
        if (Numbers.fitsIntoByte(number)) {
            return getNodeFactory().byteNode(number.byteValue());
        } else if (Numbers.fitsIntoShort(number)) {
            return getNodeFactory().shortNode(number.shortValue());
        } else if (Numbers.fitsIntoInt(number)) {
            return getNodeFactory().intNode(number.intValue());
        } else if (Numbers.fitsIntoLong(number)) {
            return getNodeFactory().longNode(number.longValue());
        } else {
            return getNodeFactory().bigIntegerNode(number);
        }
    }

}
