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

import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;

/**
 * A returning visitor for {@link YamlNode}s.
 *
 * @param <T> the returned type
 *
 * @see YamlNodeVisitor
 */
public interface ReturningYamlNodeVisitor<T> {

    /**
     * Visit a {@link YamlMappingNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlMappingNode node);

    /**
     * Visit a {@link YamlOrderedMappingNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlOrderedMappingNode node);

    /**
     * Visit a {@link YamlPairsNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlPairsNode node);

    /**
     * Visit a {@link YamlSequenceNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlSequenceNode node);

    /**
     * Visit a {@link YamlSetNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlSetNode node);

    /**
     * Visit a {@link YamlBinaryNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlBinaryNode node);

    /**
     * Visit a {@link YamlBooleanNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlBooleanNode node);

    /**
     * Visit a {@link YamlDecimalNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlDecimalNode node);

    /**
     * Visit a {@link YamlIntegralNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlIntegralNode node);

    /**
     * Visit a {@link YamlNullNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlNullNode node);

    /**
     * Visit a {@link YamlTextNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlTextNode node);

    /**
     * Visit a {@link YamlTimeNode}.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    T visit(YamlTimeNode node);

}
