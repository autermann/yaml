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

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.rules.ErrorCollector;

import com.github.autermann.yaml.YamlNode;

public class YamlNodesMatcher extends ErrorCollector {

    public static <T> Matcher<T> noneOf(Matcher<? super T>... matchers) {
        return Matchers.not(Matchers.anyOf(matchers));
    }

    @Factory
    public static Matcher<YamlNode> existingNode() {
        return new NodeTypeMatcher("existing") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.exists();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> nullNode() {
        return new NodeTypeMatcher("null") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isNull();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> binaryNode() {
        return new NodeTypeMatcher("binary") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isBinary();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> booleanNode() {
        return new NodeTypeMatcher("boolean") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isBoolean();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> containerNode() {
        return new NodeTypeMatcher("container") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isContainer();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> decimalNode() {
        return new NodeTypeMatcher("decimal") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isDecimal();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> bigDecimalNode() {
        return new NodeTypeMatcher("big decimal") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isBigDecimal();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> doubleNode() {
        return new NodeTypeMatcher("double") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isDouble();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> floatNode() {
        return new NodeTypeMatcher("float") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isFloat();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> integralNode() {
        return new NodeTypeMatcher("integral") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isIntegral();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> bigIntegerNode() {
        return new NodeTypeMatcher("bigInteger") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isBigInteger();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> longNode() {
        return new NodeTypeMatcher("long") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isLong();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> intNode() {
        return new NodeTypeMatcher("int") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isInt();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> shortNode() {
        return new NodeTypeMatcher("short") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isShort();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> byteNode() {
        return new NodeTypeMatcher("byte") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isByte();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> mapNode() {
        return new NodeTypeMatcher("map") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isMap();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> numberNode() {
        return new NodeTypeMatcher("number") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isNumber();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> orderedMapNode() {
        return new NodeTypeMatcher("ordered map") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isOrderedMap();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> pairsNode() {
        return new NodeTypeMatcher("pairs") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isPairs();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> scalarNode() {
        return new NodeTypeMatcher("scalar") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isScalar();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> sequenceNode() {
        return new NodeTypeMatcher("sequence") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isSequence();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> setNode() {
        return new NodeTypeMatcher("set") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isSet();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> textNode() {
        return new NodeTypeMatcher("text") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isText();
            }
        };
    }

    @Factory
    public static Matcher<YamlNode> timeNode() {
        return new NodeTypeMatcher("time") {
            @Override
            protected boolean matchesSafely(YamlNode item) {
                return item.isTime();
            }
        };
    }

    private static abstract class NodeTypeMatcher extends TypeSafeMatcher<YamlNode> {
        private final String type;

        NodeTypeMatcher(String type) {
            this.type = type;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(type).appendText(" node");
        }
    }
}
