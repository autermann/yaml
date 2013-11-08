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
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests for {@link YamlMissingNode}.
 *
 * @author Christian Autermann
 */
public class YamlMissingNodeTest extends AbstractYamlNodeTest {

    @Override
    public void testToString() {
        assertThat(instance().toString(), is(""));
    }

    @Override
    public void testEquals() {
        assertThat(instance(), is(instance()));
    }

    @Override
    public void testHashCode() {
        assertThat(instance().hashCode(),
                   is(instance().hashCode()));
    }

    @Override
    public void testExists() {
        assertThat(instance().exists(), is(false));
    }

    @Override
    public void testTag() {
        assertThat(instance().tag(), is(nullValue()));
    }

    /**
     * Test for {@link YamlMissingNode#instance() }.
     */
    @Test
    public void testInstance() {
        assertThat(instance(), is(notNullValue()));
        assertThat(instance(), is(instance()));
        assertThat(instance() == instance(), is(true));
    }

    @Override
    protected YamlMissingNode instance() {
        return YamlMissingNode.instance();
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        FailingReturningYamlNodeVisitor v
                = new FailingReturningYamlNodeVisitor() {
                };
        v.hasVisited(true);
        return v;
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        FailingYamlNodeVisitor v
                = new FailingYamlNodeVisitor() {
                };
        v.hasVisited(true);
        return v;
    }

    @Override
    public void testDump_0args() {
        thrown.expect(IllegalArgumentException.class);
        super.testDump_0args();
    }

    @Override
    public void testDump_DumperOptions() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_DumperOptions();
    }

    @Override
    public void testDump_OutputStream() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_OutputStream();
    }

    @Override
    public void testDump_OutputStream_DumperOptions() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_OutputStream_DumperOptions();
    }

    @Override
    public void testDump_OutputStream_Yaml() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_OutputStream_Yaml();
    }

    @Override
    public void testDump_Writer() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_Writer();
    }

    @Override
    public void testDump_Writer_DumperOptions() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_Writer_DumperOptions();
    }

    @Override
    public void testDump_Writer_Yaml() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_Writer_Yaml();
    }

    @Override
    public void testDump_Yaml() {
        // this node can't be dumped
        thrown.expect(IllegalArgumentException.class);
        super.testDump_Yaml();
    }
}
