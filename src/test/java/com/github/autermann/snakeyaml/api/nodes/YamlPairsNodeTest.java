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
package com.github.autermann.snakeyaml.api.nodes;

import static com.google.common.collect.Maps.immutableEntry;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.github.autermann.snakeyaml.api.Yaml;
import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class YamlPairsNodeTest {

    public static final Yaml YAML = new Yaml();
    private final YamlNodeFactory factory = YamlNodeFactory.getDefault();

    @Test
    @SuppressWarnings("unchecked")
    public void testOrder() {
        YamlNode a = factory.textNode("a");
        YamlNode b = factory.textNode("b");
        assertThat(factory.pairsNode().put(a, a).put(b, b).put(a, a).entries(),
                   Matchers.allOf(is(notNullValue()),
                                  contains(immutableEntry(a, a),
                                           immutableEntry(b, b),
                                           immutableEntry(a, a))));
    }

    @Test
    public void testEquals() {
        assertThat(factory.pairsNode(), is(equalTo(factory.pairsNode())));
    }
}
