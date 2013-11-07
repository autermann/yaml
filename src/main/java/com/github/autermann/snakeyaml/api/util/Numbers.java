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
package com.github.autermann.snakeyaml.api.util;

import static java.math.BigInteger.valueOf;

import java.math.BigInteger;

public class Numbers {
    private static final BigInteger MIN_LONG_AS_BIG_INT = valueOf(Long.MIN_VALUE);
    private static final BigInteger MAX_LONG_AS_BIG_INT = valueOf(Long.MAX_VALUE);
    private static final BigInteger MIN_INT_AS_BIG_INT = valueOf(Integer.MIN_VALUE);
    private static final BigInteger MAX_INT_AS_BIG_INT = valueOf(Integer.MAX_VALUE);
    private static final BigInteger MIN_SHORT_AS_BIG_INT = valueOf(Short.MIN_VALUE);
    private static final BigInteger MAX_SHORT_AS_BIG_INT = valueOf(Short.MAX_VALUE);
    private static final BigInteger MIN_BYTE_AS_BIG_INT = valueOf(Byte.MIN_VALUE);
    private static final BigInteger MAX_BYTE_AS_BIG_INT = valueOf(Byte.MAX_VALUE);
    private static final long MAX_INT_AS_LONG = (long) Integer.MAX_VALUE;
    private static final long MIN_INT_AS_LONG = (long) Integer.MIN_VALUE;
    private static final long MAX_SHORT_AS_LONG = (long) Short.MAX_VALUE;
    private static final long MIN_SHORT_AS_LONG = (long) Short.MIN_VALUE;
    private static final long MAX_BYTE_AS_LONG = (long) Byte.MAX_VALUE;
    private static final long MIN_BYTE_AS_LONG = (long) Byte.MIN_VALUE;
    private static final int MIN_SHORT_AS_INT = (int) Short.MIN_VALUE;
    private static final int MAX_SHORT_AS_INT = (int) Short.MAX_VALUE;
    private static final int MAX_BYTE_AS_INT = (int) Byte.MAX_VALUE;
    private static final int MIN_BYTE_AS_INT = (int) Byte.MIN_VALUE;
    private static final short MAX_BYTE_AS_SHORT = (short) Byte.MAX_VALUE;
    private static final short MIN_BYTE_AS_SHORT = (short) Byte.MIN_VALUE;

    public static boolean fitsIntoLong(BigInteger value) {
        return value.compareTo(MIN_LONG_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_LONG_AS_BIG_INT) <= 0;
    }

    public static boolean fitsIntoInt(BigInteger value) {
        return value.compareTo(MIN_INT_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_INT_AS_BIG_INT) <= 0;
    }

    public static boolean fitsIntoShort(BigInteger value) {
        return value.compareTo(MIN_SHORT_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_SHORT_AS_BIG_INT) <= 0;
    }

    public static boolean fitsIntoByte(BigInteger value) {
        return value.compareTo(MIN_BYTE_AS_BIG_INT) >= 0 &&
               value.compareTo(MAX_BYTE_AS_BIG_INT) <= 0;
    }

    public static boolean fitsIntoInt(long value) {
        return value >= MIN_INT_AS_LONG &&
               value <= MAX_INT_AS_LONG;
    }

    public static boolean fitsIntoShort(long value) {
        return value >= MIN_SHORT_AS_LONG &&
               value <= MAX_SHORT_AS_LONG;
    }

    public static boolean fitsIntoByte(long value) {
        return value >= MIN_BYTE_AS_LONG &&
               value <= MAX_BYTE_AS_LONG;
    }

    public static boolean fitsIntoShort(int value) {
        return value >= MIN_SHORT_AS_INT &&
               value <= MAX_SHORT_AS_INT;
    }

    public static boolean fitsIntoByte(int value) {
        return value >= MIN_BYTE_AS_INT &&
               value <= MAX_BYTE_AS_INT;
    }

    public static boolean fitsIntoByte(short value) {
        return value >= MIN_BYTE_AS_SHORT &&
               value <= MAX_BYTE_AS_SHORT;
    }

}
