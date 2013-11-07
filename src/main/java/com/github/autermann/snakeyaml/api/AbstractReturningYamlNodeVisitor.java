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
 * Abstract {@link ReturningYamlNodeVisitor} implementation to allow smaller
 * implementations. Per default implementations will return null and
 * delegate to one of
 * <ul>
 * <li>{@link #visitMapping(AbstractYamlMappingNode)}</li>
 * <li>{@link #visitScalar(AbstractYamlScalarNode)}</li>
 * <li>{@link #visitSequence(AbstractYamlSequenceNode)}</li>
 * </ul>
 *
 * @param <T> the returned type
 */
public abstract class AbstractReturningYamlNodeVisitor<T>
        implements ReturningYamlNodeVisitor<T> {

    @Override
    public T visit(YamlMappingNode node) {
        return visitMapping(node);
    }

    @Override
    public T visit(YamlOrderedMappingNode node) {
        return visitMapping(node);
    }

    @Override
    public T visit(YamlPairsNode node) {
        return visitMapping(node);
    }

    @Override
    public T visit(YamlSequenceNode node) {
        return visitSequence(node);
    }

    @Override
    public T visit(YamlSetNode node) {
        return visitSequence(node);
    }

    @Override
    public T visit(YamlBinaryNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlBooleanNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlDecimalNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlIntegralNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlNullNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlTextNode node) {
        return visitScalar(node);
    }

    @Override
    public T visit(YamlTimeNode node) {
        return visitScalar(node);
    }

    /**
     * Visit any mapping node.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    protected T visitMapping(AbstractYamlMappingNode<?> node) {
        return null;
    }

    /**
     * Visit any sequence node.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    protected T visitSequence(AbstractYamlSequenceNode<?> node) {
        return null;
    }

    /**
     * Visit any scalar node.
     *
     * @param node the node to visit
     *
     * @return the returned value
     */
    protected T visitScalar(AbstractYamlScalarNode<?> node) {
        return null;
    }

}
