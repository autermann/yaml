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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ErrorCollector;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class YamlReferenceTest {
    public final Yaml YAML = new Yaml();
    public final DefaultYamlNodeFactory factory = YamlNodeFactory
            .createDefault();

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Test
    public void testStringReference() {
        YamlNode load = YAML.load("a: &a value\nb: *a");
        errors.checkThat(load.path("a").textValue(), is("value"));
        errors.checkThat(load.path("b").textValue(), is("value"));
    }

    @Test
    public void testObjectReference() {
        YamlNode load = YAML.load("a: &a {value: 1}\nb: *a");
        errors.checkThat(load.path("a").isMap(), is(true));
        errors.checkThat(load.path("b").isMap(), is(true));
        errors.checkThat(load.path("a"), is(sameInstance(load.path("b"))));
    }

    @Test
    @Ignore("Currently not supported-")
    public void testRecursiveObjectReference() {
        YamlNode load = YAML.load("a: &a {value: *a}");
        errors.checkThat(load.path("a").isMap(), is(true));
        errors.checkThat(load.path("a").path("value").isMap(), is(true));
        errors.checkThat(load.path("a"), is(sameInstance(load.path("a").path("value"))));
    }

}
