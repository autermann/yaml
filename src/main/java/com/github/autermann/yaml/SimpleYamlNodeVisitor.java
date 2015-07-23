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
package com.github.autermann.yaml;

import com.github.autermann.yaml.nodes.YamlBinaryNode;
import com.github.autermann.yaml.nodes.YamlBooleanNode;
import com.github.autermann.yaml.nodes.YamlDecimalNode;
import com.github.autermann.yaml.nodes.YamlIntegralNode;
import com.github.autermann.yaml.nodes.YamlMapNode;
import com.github.autermann.yaml.nodes.YamlMappingNode;
import com.github.autermann.yaml.nodes.YamlNullNode;
import com.github.autermann.yaml.nodes.YamlOrderedMapNode;
import com.github.autermann.yaml.nodes.YamlPairsNode;
import com.github.autermann.yaml.nodes.YamlScalarNode;
import com.github.autermann.yaml.nodes.YamlSeqNode;
import com.github.autermann.yaml.nodes.YamlSequenceNode;
import com.github.autermann.yaml.nodes.YamlSetNode;
import com.github.autermann.yaml.nodes.YamlTextNode;
import com.github.autermann.yaml.nodes.YamlTimeNode;

/**
 * Abstract {@link YamlNodeVisitor} implementation to allow smaller
 * implementations. Per default implementations will delegate to one of
 * <ul>
 * <li>{@link #visitMapping(YamlMappingNode)}</li>
 * <li>{@link #visitScalar(YamlScalarNode)}</li>
 * <li>{@link #visitSequence(YamlSequenceNode)}</li>
 * </ul>
 */
public interface SimpleYamlNodeVisitor extends YamlNodeVisitor {

    @Override
    default void visit(YamlMapNode node) {
        visitMapping(node);
    }

    @Override
    default void visit(YamlOrderedMapNode node) {
        visitMapping(node);
    }

    @Override
    default void visit(YamlPairsNode node) {
        visitMapping(node);
    }

    @Override
    default void visit(YamlSeqNode node) {
        visitSequence(node);
    }

    @Override
    default void visit(YamlSetNode node) {
        visitSequence(node);
    }

    @Override
    default void visit(YamlBinaryNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlBooleanNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlDecimalNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlIntegralNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlNullNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlTextNode node) {
        visitScalar(node);
    }

    @Override
    default void visit(YamlTimeNode node) {
        visitScalar(node);
    }

    /**
     * Visit any mapping node.
     *
     * @param node the node to visit
     */
    default void visitMapping(YamlMappingNode<?> node) {
        // no-op
    }

    /**
     * Visit any sequence node.
     *
     * @param node the node to visit
     */
    default void visitSequence(YamlSequenceNode<?> node) {
        // no-op
    }

    /**
     * Visit any scalar node.
     *
     * @param node the node to visit
     */
    default void visitScalar(YamlScalarNode node) {
        // no-op
    }

}
