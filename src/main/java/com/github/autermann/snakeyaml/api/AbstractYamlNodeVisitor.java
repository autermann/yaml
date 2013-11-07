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

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlSequenceNode;
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
 * Abstract {@link YamlNodeVisitor} implementation to allow smaller
 * implementations. Per default implementations will delegate to one of
 * <ul>
 * <li>{@link #visitMapping(AbstractYamlMappingNode)}</li>
 * <li>{@link #visitScalar(AbstractYamlScalarNode)}</li>
 * <li>{@link #visitSequence(AbstractYamlSequenceNode)}</li>
 * </ul>
 */
public abstract class AbstractYamlNodeVisitor implements YamlNodeVisitor {

    @Override
    public void visit(YamlMappingNode node) {
        visitMapping(node);
    }

    @Override
    public void visit(YamlOrderedMappingNode node) {
        visitMapping(node);
    }

    @Override
    public void visit(YamlPairsNode node) {
        visitMapping(node);
    }

    @Override
    public void visit(YamlSequenceNode node) {
        visitSequence(node);
    }

    @Override
    public void visit(YamlSetNode node) {
        visitSequence(node);
    }

    @Override
    public void visit(YamlBinaryNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlBooleanNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlDecimalNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlIntegralNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlNullNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlTextNode node) {
        visitScalar(node);
    }

    @Override
    public void visit(YamlTimeNode node) {
        visitScalar(node);
    }

    /**
     * Visit any mapping node.
     *
     * @param node the node to visit
     */
    protected void visitMapping(AbstractYamlMappingNode<?> node) {
        // no-op
    }

    /**
     * Visit any sequence node.
     *
     * @param node the node to visit
     */
    protected void visitSequence(AbstractYamlSequenceNode<?> node) {
        // no-op
    }

    /**
     * Visit any scalar node.
     *
     * @param node the node to visit
     */
    protected void visitScalar(AbstractYamlScalarNode<?> node) {
        // no-op
    }

}
