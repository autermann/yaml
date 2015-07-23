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
package com.github.autermann.yaml.nodes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlBooleanNode}s.
 *
 * @author Christian Autermann
 */
public abstract class YamlBooleanNodeTest extends AbstractYamlScalarNodeTest {

    @Override
    public void testHashCode() {
        YamlNode node = instance();
        assertThat(instance().hashCode(),
                   is(Boolean.valueOf(node.booleanValue()).hashCode()));
    }

    @Override
    public void testTag() {
        assertThat(instance().tag(), is(Tag.BOOL));
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlBooleanNode node) {
                return hasVisited(true);
            }

        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlBooleanNode node) {
                hasVisited(true);
            }
        };
    }

    @Override
    public void testIsBoolean() {
        assertThat(instance().isBoolean(), is(true));
    }

    @Override
    public void testIsScalar() {
        assertThat(instance().isScalar(), is(true));
    }

    @Override
    public void testValue() {
        assertThat(instance().booleanValue(), is(instance().value()));
    }


}
