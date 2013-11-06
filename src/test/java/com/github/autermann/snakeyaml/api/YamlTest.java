/*
 * Copyright (C) 2013 Christian Autermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.autermann.snakeyaml.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Test;


/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class YamlTest {
    public static final Yaml YAML = new Yaml();
    private final YamlNodeFactory factory = YamlNodeFactory.getDefault();

    public void test(YamlNode node) {
        assertThat(YAML.load(YAML.dump(node)), is(equalTo(node)));
    }

    @Test
    public void testTextNode() {
        test(factory.textNode("hello world"));
    }

    @Test
    public void testBooleanNode() {
        test(factory.booleanNode(true));
        test(factory.booleanNode(false));
    }

    @Test
    public void testIntegerNode() {
        test(factory.intNode(42));
    }

    @Test
    public void testDoubleNode() {
        test(factory.doubleNode(42.42));
    }

    @Test
    public void testBinaryNode() {
        byte[] bytes = new byte[128];
        new Random().nextBytes(bytes);
        test(factory.binaryNode(bytes));
    }

    @Test
    public void testTimeNode() {
        test(factory.dateTimeNode(new DateTime()));
    }

    @Test
    public void testNullNode() {
        test(factory.nullNode());
    }

    @Test
    public void testMappingNode() {
        test(factory.mappingNode().put("a", "b").put("b", "c"));
    }

    @Test
    public void testOrderedMappingNode() {
        test(factory.orderedMappingNode().put("a", "b").put("b", "c"));
    }

    @Test
    public void testSequenceNode() {
        test(factory.sequenceNode().add("a").add("a").add("b"));
    }

    @Test
    public void testPairsNode() {
        test(factory.pairsNode().put("a", "b").put("a", "b").put("b", "c"));
    }

    @Test
    public void testSetNode() {
        test(factory.setNode().add("a").add("a").add("b"));
    }

}
