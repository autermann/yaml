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
package com.github.autermann.yaml.util;

import static java.math.BigInteger.valueOf;

import java.math.BigInteger;

/**
 * Utility class to check integral primitive boundaries.
 *
 * @author Christian Autermann
 */
public class Numbers {
    /**
     * Minimal {@code long} as {@code BigInteger}.
     */
    private static final BigInteger MIN_LONG_AS_BIG_INT = valueOf(Long.MIN_VALUE);
    /**
     * Maximal {@code long} as {@code BigInteger}.
     */
    private static final BigInteger MAX_LONG_AS_BIG_INT = valueOf(Long.MAX_VALUE);
    /**
     * Minimal {@code int} as {@code BigInteger}.
     */
    private static final BigInteger MIN_INT_AS_BIG_INT = valueOf(Integer.MIN_VALUE);
    /**
     * Maximal {@code int} as {@code BigInteger}.
     */
    private static final BigInteger MAX_INT_AS_BIG_INT = valueOf(Integer.MAX_VALUE);
    /**
     * Minimal {@code short} as {@code BigInteger}.
     */
    private static final BigInteger MIN_SHORT_AS_BIG_INT = valueOf(Short.MIN_VALUE);
    /**
     * Maximal {@code short} as {@code BigInteger}.
     */
    private static final BigInteger MAX_SHORT_AS_BIG_INT = valueOf(Short.MAX_VALUE);
    /**
     * Minimal {@code byte} as {@code BigInteger}.
     */
    private static final BigInteger MIN_BYTE_AS_BIG_INT = valueOf(Byte.MIN_VALUE);
    /**
     * Maximal {@code byte} as {@code BigInteger}.
     */
    private static final BigInteger MAX_BYTE_AS_BIG_INT = valueOf(Byte.MAX_VALUE);
    /**
     * Maximal {@code int} as {@code long}.
     */
    private static final long MAX_INT_AS_LONG = (long) Integer.MAX_VALUE;
    /**
     * Minimal {@code int} as {@code long}.
     */
    private static final long MIN_INT_AS_LONG = (long) Integer.MIN_VALUE;
    /**
     * Maximal {@code short} as {@code long}.
     */
    private static final long MAX_SHORT_AS_LONG = (long) Short.MAX_VALUE;
    /**
     * Minimal {@code short} as {@code long}.
     */
    private static final long MIN_SHORT_AS_LONG = (long) Short.MIN_VALUE;
    /**
     * Maximal {@code byte} as {@code long}.
     */
    private static final long MAX_BYTE_AS_LONG = (long) Byte.MAX_VALUE;
    /**
     * Minimal {@code byte} as {@code long}.
     */
    private static final long MIN_BYTE_AS_LONG = (long) Byte.MIN_VALUE;
    /**
     * Minimal {@code short} as {@code int}.
     */
    private static final int MIN_SHORT_AS_INT = (int) Short.MIN_VALUE;
    /**
     * Maximal {@code short} as {@code int}.
     */
    private static final int MAX_SHORT_AS_INT = (int) Short.MAX_VALUE;
    /**
     * Maximal {@code byte} as {@code int}.
     */
    private static final int MAX_BYTE_AS_INT = (int) Byte.MAX_VALUE;
    /**
     * Minimal {@code byte} as {@code int}.
     */
    private static final int MIN_BYTE_AS_INT = (int) Byte.MIN_VALUE;
    /**
     * Maximal {@code byte} as {@code short}.
     */
    private static final short MAX_BYTE_AS_SHORT = (short) Byte.MAX_VALUE;
    /**
     * Minimal {@code byte} as {@code short}.
     */
    private static final short MIN_BYTE_AS_SHORT = (short) Byte.MIN_VALUE;

    /**
     * Private constructor for utility class.
     */
    private Numbers() {
    }

    /**
     * Checks if the supplied {@code BigInteger} fits into a {@code long}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoLong(BigInteger value) {
        return value.compareTo(MIN_LONG_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_LONG_AS_BIG_INT) <= 0;
    }

    /**
     * Checks if the supplied {@code BigInteger} fits into a {@code int}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoInt(BigInteger value) {
        return value.compareTo(MIN_INT_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_INT_AS_BIG_INT) <= 0;
    }

    /**
     * Checks if the supplied {@code BigInteger} fits into a {@code short}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoShort(BigInteger value) {
        return value.compareTo(MIN_SHORT_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_SHORT_AS_BIG_INT) <= 0;
    }

    /**
     * Checks if the supplied {@code BigInteger} fits into a {@code byte}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoByte(BigInteger value) {
        return value.compareTo(MIN_BYTE_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_BYTE_AS_BIG_INT) <= 0;
    }

    /**
     * Checks if the supplied {@code long} fits into a {@code int}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoInt(long value) {
        return value >= MIN_INT_AS_LONG &&
               value <= MAX_INT_AS_LONG;
    }

    /**
     * Checks if the supplied {@code long} fits into a {@code short}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoShort(long value) {
        return value >= MIN_SHORT_AS_LONG &&
               value <= MAX_SHORT_AS_LONG;
    }

    /**
     * Checks if the supplied {@code long} fits into a {@code byte}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoByte(long value) {
        return value >= MIN_BYTE_AS_LONG &&
               value <= MAX_BYTE_AS_LONG;
    }

    /**
     * Checks if the supplied {@code int} fits into a {@code short}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoShort(int value) {
        return value >= MIN_SHORT_AS_INT &&
               value <= MAX_SHORT_AS_INT;
    }

    /**
     * Checks if the supplied {@code int} fits into a {@code byte}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoByte(int value) {
        return value >= MIN_BYTE_AS_INT &&
               value <= MAX_BYTE_AS_INT;
    }

    /**
     * Checks if the supplied {@code short} fits into a {@code byte}.
     *
     * @param value the value to test
     *
     * @return {@code true} if it fits, else {@code false}
     */
    public static boolean fitsIntoByte(short value) {
        return value >= MIN_BYTE_AS_SHORT &&
               value <= MAX_BYTE_AS_SHORT;
    }

}
