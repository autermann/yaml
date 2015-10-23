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
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.junit.Test;

import com.github.autermann.yaml.YamlNode;

/**
 * Tests for {@link YamlScalarNode}.
 *
 * @author Christian Autermann
 */
public abstract class AbstractYamlScalarNodeTest extends AbstractYamlNodeTest {

    /**
     * The random to create {@code double}s.
     */
    private final Random random = new Random();

    /**
     * Tests {@link YamlScalarNode#value() }.
     */
    @Test
    public abstract void testValue();

    @Override
    protected abstract YamlScalarNode instance();

    /**
     * Creates a random {@code byte}.
     *
     * @return the random {@code byte}
     */
    public byte randomByte() {
        return (byte) random.nextInt(Byte.MAX_VALUE + 1);
    }

    /**
     * Creates a random {@code short}.
     *
     * @return the random {@code short}
     */
    public short randomShort() {
        return (short) random.nextInt(Short.MAX_VALUE + 1);
    }

    /**
     * Creates a random {@code int}.
     *
     * @return the random {@code int}
     */
    public int randomInt() {
        return random.nextInt();
    }

    /**
     * Creates a random {@code long}.
     *
     * @return the random {@code long}
     */
    public long randomLong() {
        return random.nextLong();
    }

    /**
     * Creates a random {@code boolean}.
     *
     * @return the random {@code boolean}
     */
    public boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * Creates a random {@code float}.
     *
     * @return the random {@code float}
     */
    public float randomFloat() {
        return random.nextFloat();
    }

    /**
     * Creates a random {@code double}.
     *
     * @return the random {@code double}
     */
    public double randomDouble() {
        return random.nextDouble();
    }

    /**
     * Creates a random {@link BigDecimal}.
     *
     * @return the random number
     */
    public BigDecimal randomBigDecimal() {
        return new BigDecimal(random.nextDouble());
    }

    /**
     * Creates a random {@link DateTime}.
     *
     * @return the random number
     */
    public DateTime randomDateTime() {
        return new DateTime(randomLong());
    }

    /**
     * Creates a random {@link Date}.
     *
     * @return the random number
     */
    public Date randomDate() {
        return new Date(randomLong());
    }

    /**
     * Creates a {@code byte} that is different from {@code value}.
     *
     * @param value the {@code byte}
     *
     * @return a {@code byte} that is different from {@code value}
     */
    public byte another(byte value) {
        return (byte) (value == Byte.MAX_VALUE ? value - 1 : value + 1);
    }

    /**
     * Creates a {@code short} that is different from {@code value}.
     *
     * @param value the {@code short}
     *
     * @return a {@code short} that is different from {@code value}
     */
    public short another(short value) {
        return (short) (value == Short.MAX_VALUE ? value - 1 : value + 1);
    }

    /**
     * Creates a {@code int} that is different from {@code value}.
     *
     * @param value the {@code int}
     *
     * @return a {@code int} that is different from {@code value}
     */
    public int another(int value) {
        return value == Integer.MAX_VALUE ? value - 1 : value + 1;
    }

    /**
     * Creates a {@code long} that is different from {@code value}.
     *
     * @param value the {@code long}
     *
     * @return a {@code long} that is different from {@code value}
     */
    public long another(long value) {
        return value == Long.MAX_VALUE ? value - 1L : value + 1L;
    }

    @Test
    public void test_stream() {
        YamlScalarNode instance = instance();
        Stream<YamlNode> stream = instance.stream();
        errors.checkThat(stream, is(notNullValue()));
        List<YamlNode> list = stream.collect(Collectors.toList());
        errors.checkThat(list.size(), is(1));
        errors.checkThat(list.get(0), is(instance));
    }

}
