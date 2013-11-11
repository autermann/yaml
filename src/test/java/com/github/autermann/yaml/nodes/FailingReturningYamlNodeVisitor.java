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
package com.github.autermann.yaml.nodes;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;

/**
 * {@link ReturningYamlNodeVisitor} that causes test failures for every visit.
 *
 * @author Christian Autermann
 */
public abstract class FailingReturningYamlNodeVisitor
        implements ReturningYamlNodeVisitor<Void> {
    private boolean visited;

    @Override
    public Void visit(YamlMapNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlOrderedMapNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlPairsNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlSeqNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlSetNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlBinaryNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlBooleanNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlDecimalNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlIntegralNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlNullNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlTextNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public Void visit(YamlTimeNode node) {
        throw new AssertionError("Wrong node visited");
    }

    public boolean hasVisited() {
        return visited;
    }

    public Void hasVisited(boolean visited) {
        this.visited = visited;
        return null;
    }
}
