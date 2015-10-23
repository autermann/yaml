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

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * {@code Spliterator} for single elements.
 *
 * @param <T> the element type
 *
 * @author Christian Autermann
 * @since 1.0.3
 */
public class SingletonSpliterator<T> implements Spliterator<T> {
    private final int characteristics;
    private final T element;
    private long size = 1;

    /**
     * Creates a new {@code SingletonSpliterator} from the specified element.
     *
     * @param element the element (may be {@code null}
     */
    public SingletonSpliterator(T element) {
        this.characteristics = (element != null ? Spliterator.NONNULL : 0) |
                               SIZED | SUBSIZED | IMMUTABLE | DISTINCT | ORDERED;
        this.element = element;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        if (size > 0) {
            size--;
            action.accept(element);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        tryAdvance(action);
    }

    @Override
    public long estimateSize() {
        return size;
    }

    @Override
    public int characteristics() {
        return characteristics;
    }
}
