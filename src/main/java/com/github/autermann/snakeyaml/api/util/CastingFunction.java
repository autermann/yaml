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
package com.github.autermann.snakeyaml.api.util;

import com.google.common.base.Function;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
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
