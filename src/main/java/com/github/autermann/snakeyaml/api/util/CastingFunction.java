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

import com.google.common.base.Function;

public class CastingFunction<T> implements Function<Object, T> {
    private static final CastingFunction<?> instance = new CastingFunction<Object>();

    private CastingFunction() {
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public T apply(Object input) {
        return (T) input;
    }

    @SuppressWarnings("unchecked")
    public static <T> CastingFunction<T> instance() {
        return (CastingFunction<T>) instance;
    }

}
