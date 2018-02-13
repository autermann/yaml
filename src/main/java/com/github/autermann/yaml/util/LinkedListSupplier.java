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
package com.github.autermann.yaml.util;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;


/**
 * {@link Supplier} for {@link java.util.LinkedList}s.
 *
 * @author Christian Autermann
 */
@Deprecated
public final class LinkedListSupplier implements Supplier<List<?>> {

    /**
     * Private constructor for singleton.
     */
    private LinkedListSupplier() {
    }

    @Override
    public List<?> get() {
        return new LinkedList<>();
    }

    /**
     * Get a instance of {@link LinkedListSupplier} for the specified element
     * type.
     *
     * @param <T> the element type
     *
     * @return a list supplier
     */
    @Deprecated
    public static <T> Supplier<List<T>> instance() {
        return LinkedList::new;
    }

}
