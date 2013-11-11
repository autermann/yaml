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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlNullNode}.
 *
 * @author Christian Autermann
 */
public class YamlNullNodeTest extends AbstractYamlScalarNodeTest {
    @Override
    public void testIsScalar() {
        assertThat(instance().isScalar(), is(true));
    }

    @Override
    public void testAsTextValue_0args() {
        assertThat(instance().asTextValue(), is("null"));
    }

    @Override
    public void testAsTextValue_String() {
        errors.checkThat(instance().asTextValue("asdf"), is("null"));
        errors.checkThat(instance().asTextValue(""), is("null"));
        errors.checkThat(instance().asTextValue(null), is("null"));
    }

    @Override
    public void testTag() {
        assertThat(instance().tag(), is(Tag.NULL));
    }

    @Override
    public void testHashCode() {
        assertThat(instance().hashCode(),
                   is(instance().hashCode()));
    }

    @Override
    public void testEquals() {
        errors.checkThat(instance(), is(instance()));
        errors.checkThat(instance(), is(notNullValue()));
        errors.checkThat((YamlNode) instance(),
                         is(not((YamlNode) YamlMissingNode.instance())));
    }

    @Override
    public void testIsNull() {
        assertThat(instance().isNull(), is(true));
    }

    @Override
    public void testValue() {
        assertThat(instance().value(), is(nullValue()));
    }

    /**
     * Test for {@link YamlNullNode#instance() }.
     */
    @Test
    public void testInstance() {
        assertThat(instance(), is(notNullValue()));
        assertThat(instance(), is(instance()));
        assertThat(instance() == instance(), is(true));
    }

    @Override
    public void testToString() {
        assertThat(instance().toString(), is("null"));
    }

    @Override
    protected YamlNullNode instance() {
        return YamlNullNode.instance();
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlNullNode node) {
                return hasVisited(true);
            }
        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlNullNode node) {
                hasVisited(true);
            }
        };
    }
}
