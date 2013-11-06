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

import java.util.List;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class LinkedListSupplier implements Supplier<List<?>> {
    private static final LinkedListSupplier INSTANCE = new LinkedListSupplier();

    private LinkedListSupplier() {
    }

    @Override
    public List<YamlNode> get() {
        return Lists.newLinkedList();
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> Supplier<List<T>> instance() {
        return (Supplier<List<T>>) INSTANCE;
    }

}
