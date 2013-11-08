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


import com.github.autermann.yaml.YamlNodeVisitor;

/**
 * {@link YamlNodeVisitor} that causes test failures for every visit.
 *
 * @author Christian Autermann
 */
public abstract class FailingYamlNodeVisitor implements YamlNodeVisitor {
    private boolean visited = false;

    @Override
    public void visit(YamlMapNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlOrderedMapNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlPairsNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlSequenceNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlSetNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlBinaryNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlBooleanNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlDecimalNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlIntegralNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlNullNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlTextNode node) {
        throw new AssertionError("Wrong node visited");
    }

    @Override
    public void visit(YamlTimeNode node) {
        throw new AssertionError("Wrong node visited");
    }

    public boolean hasVisited() {
        return visited;
    }

    public void hasVisited(boolean visited) {
        this.visited = visited;
    }

}
