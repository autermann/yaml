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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;
import java.util.Random;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.YamlNode;
import com.google.common.io.BaseEncoding;

/**
 * Test for {@link YamlBinaryNode}s.
 *
 * @author Christian Autermann
 */
public class YamlBinaryNodeTest extends AbstractYamlNodeTest {
    /**
     * The random to fill the byte arrays.
     */
    private final Random random = new Random();

    @Override
    public void testToString() {
        YamlBinaryNode node = instance();
        errors.checkThat(node.toString(), is(base64(node)));
    }

    @Override
    public void testIsBinary() {
        errors.checkThat(instance().isBinary(), is(true));
    }

    @Override
    public void testIsScalar() {
        errors.checkThat(instance().isScalar(), is(true));
    }

    @Override
    public void testBinaryValue() {
        byte[] bytes = randomBytes();
        YamlBinaryNode i = new YamlBinaryNode(bytes);
        errors.checkThat(i.binaryValue(), is(bytes));
    }

    @Override
    public void testAsBinaryValue_0args() {
        byte[] bytes = randomBytes();
        YamlBinaryNode i = new YamlBinaryNode(bytes);
        errors.checkThat(i.asBinaryValue(), is(bytes));
    }

    @Override
    public void testAsBinaryValue_byteArr() {
        byte[] bytes = randomBytes();
        YamlBinaryNode i = new YamlBinaryNode(bytes);
        errors.checkThat(i.asBinaryValue(null), is(bytes));
        errors.checkThat(i.asBinaryValue(new byte[0]), is(bytes));
        errors.checkThat(i.asBinaryValue(randomBytes()), is(bytes));
    }

    /**
     * Test {@code null} in {@link YamlBinaryNode} constructor.
     */
    public void testNullConstructor() {
        thrown.expect(NullPointerException.class);
        new YamlBinaryNode(null);
    }

    @Override
    public void testAsTextValue_0args() {
        YamlBinaryNode node = instance();
        errors.checkThat(node.asTextValue(), is(base64(node)));
    }

    @Override
    public void testAsTextValue_String() {
        YamlBinaryNode node = instance();
        errors.checkThat(node.asTextValue(""), is(base64(node)));
        errors.checkThat(node.asTextValue("asdf"), is(base64(node)));
        errors.checkThat(node.asTextValue(null), is(base64(node)));
    }

    @Override
    public void testEquals() {
        byte[] bytes = randomBytes();
        byte[] copy = Arrays.copyOf(bytes, bytes.length);
        errors.checkThat(new YamlBinaryNode(bytes),
                         is(equalTo(new YamlBinaryNode(copy))));
        errors.checkThat((YamlNode) new YamlBinaryNode(bytes),
                         is(not(equalTo((YamlNode) factory
                .binaryNode((byte[]) null)))));
        copy[0] = (byte) -copy[0];
        errors.checkThat(new YamlBinaryNode(bytes),
                         is(not(equalTo(new YamlBinaryNode(copy)))));
    }

    @Override
    public void testHashCode() {
        YamlBinaryNode i = instance();
        errors.checkThat(i.hashCode(), is(Arrays.hashCode(i.binaryValue())));
    }

    @Override
    public void testTag() {
        errors.checkThat(instance().tag(), is(Tag.BINARY));
    }

    @Override
    protected YamlBinaryNode instance() {
        byte[] bytes = randomBytes();
        return new YamlBinaryNode(bytes);
    }

    @Override
    protected FailingReturningYamlNodeVisitor returningVisitor() {
        return new FailingReturningYamlNodeVisitor() {
            @Override
            public Void visit(YamlBinaryNode node) {
                return hasVisited(true);
            }
        };
    }

    @Override
    protected FailingYamlNodeVisitor visitor() {
        return new FailingYamlNodeVisitor() {
            @Override
            public void visit(YamlBinaryNode node) {
                hasVisited(true);
            }
        };
    }

    /**
     * Generates a random byte array.
     *
     * @return the random bytes
     */
    protected byte[] randomBytes() {
        byte[] bytes = new byte[random.nextInt(128)];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Encodes a binary node as a base 64 encoded string.
     *
     * @param node the node
     *
     * @return the base 64 encoded bytes
     */
    private String base64(YamlBinaryNode node) {
        return BaseEncoding.base64().encode(node.binaryValue());
    }
}
