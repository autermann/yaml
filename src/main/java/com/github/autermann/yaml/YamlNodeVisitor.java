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
package com.github.autermann.yaml;

import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlDecimalNode;
import com.github.autermann.yaml.nodes.YamlIntegralNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlSequenceNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;

/**
 * A visitor for {@link YamlNode}s.
 *
 * @see ReturningYamlNodeVisitor
 * @see AbstractYamlNodeVisitor
 */
public interface YamlNodeVisitor {

    /**
     * Visit a {@link YamlMapNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlMapNode node);

    /**
     * Visit a {@link YamlOrderedMapNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlOrderedMapNode node);

    /**
     * Visit a {@link YamlPairsNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlPairsNode node);

    /**
     * Visit a {@link YamlSequenceNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlSequenceNode node);

    /**
     * Visit a {@link YamlSetNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlSetNode node);

    /**
     * Visit a {@link YamlBinaryNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlBinaryNode node);

    /**
     * Visit a {@link YamlBooleanNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlBooleanNode node);

    /**
     * Visit a {@link YamlDecimalNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlDecimalNode node);

    /**
     * Visit a {@link YamlIntegralNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlIntegralNode node);

    /**
     * Visit a {@link YamlNullNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlNullNode node);

    /**
     * Visit a {@link YamlTextNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlTextNode node);

    /**
     * Visit a {@link YamlTimeNode}.
     *
     * @param node the node to visit
     */
    void visit(YamlTimeNode node);

}
