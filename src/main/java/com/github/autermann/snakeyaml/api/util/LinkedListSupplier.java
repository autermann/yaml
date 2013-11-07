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

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

/**
 * {@link Supplier} for {@link LinkedList}s.
 *
 * @author Christian Autermann
 */
public class LinkedListSupplier implements Supplier<List<?>> {
    /**
     * The singleton instance.
     */
    private static final LinkedListSupplier INSTANCE = new LinkedListSupplier();

    /**
     * Private constructor for singleton.
     */
    private LinkedListSupplier() {
    }

    @Override
    public List<?> get() {
        return Lists.newLinkedList();
    }

    /**
     * Get a instance of {@link LinkedListSupplier} for the specified element
     * type.
     *
     * @param <T> the element type
     *
     * @return a list supplier
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> Supplier<List<T>> instance() {
        Object o = INSTANCE;
        return (Supplier<List<T>>) o;
    }

}
