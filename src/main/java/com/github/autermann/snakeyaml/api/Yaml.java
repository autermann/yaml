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
package com.github.autermann.snakeyaml.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

import org.yaml.snakeyaml.DumperOptions;

import com.github.autermann.snakeyaml.api.util.CastingFunction;
import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class Yaml {
    protected final org.yaml.snakeyaml.Yaml delegate;

    public Yaml() {
        this(new DumperOptions(), YamlNodeFactory.getDefault());
    }

    public Yaml(YamlNodeFactory nodeFactory) {
        this(new DumperOptions(), nodeFactory);
    }

    public Yaml(DumperOptions dumperOptions) {
        this(dumperOptions, YamlNodeFactory.getDefault());
    }

    public Yaml(DumperOptions dumperOptions, YamlNodeFactory nodeFactory) {
        checkNotNull(nodeFactory);
        checkNotNull(dumperOptions);
        this.delegate = new org.yaml.snakeyaml.Yaml(
                new YamlNodeConstructor(nodeFactory, dumperOptions),
                new YamlNodeRepresenter(dumperOptions),
                dumperOptions);
    }

    public String dump(YamlNode data) {
        return getDelegate().dump(data);
    }

    public void dump(YamlNode data, Writer output) {
        getDelegate().dump(data, output);
    }

    public void dump(YamlNode data, OutputStream output) {
        getDelegate().dump(data, new OutputStreamWriter(output, Charsets.UTF_8));
    }

    public String dumpAll(Iterator<? extends YamlNode> data) {
        return getDelegate().dumpAll(data);
    }

    public void dumpAll(Iterator<? extends YamlNode> data, Writer output) {
        getDelegate().dumpAll(data, output);
    }

    public void dumpAll(Iterator<? extends YamlNode> data, OutputStream output) {
        getDelegate().dumpAll(data, new OutputStreamWriter(output, Charsets.UTF_8));
    }

    public String dumpAll(Iterable<? extends YamlNode> data) {
        return dumpAll(data.iterator());
    }

    public void dumpAll(Iterable<? extends YamlNode> data, Writer output) {
        dumpAll(data.iterator(), output);
    }

    public void dumpAll(Iterable<? extends YamlNode> data, OutputStream output) {
        dumpAll(data.iterator(), output);
    }

    public YamlNode load(String yaml) {
        return (YamlNode) getDelegate().load(yaml);
    }

    public YamlNode load(InputStream io) {
        return (YamlNode) getDelegate().load(io);
    }

    public YamlNode load(Reader io) {
        return (YamlNode) getDelegate().load(io);
    }

    public Iterable<YamlNode> loadAll(Reader yaml) {
        return cast(getDelegate().loadAll(yaml));
    }

    public Iterable<YamlNode> loadAll(String yaml) {
        return cast(getDelegate().loadAll(yaml));
    }

    public Iterable<YamlNode> loadAll(InputStream yaml) {
        return cast(getDelegate().loadAll(yaml));
    }

    private Iterable<YamlNode> cast(Iterable<Object> nodes) {
        return Iterables.transform(nodes, CastingFunction.<YamlNode>instance());
    }

    protected org.yaml.snakeyaml.Yaml getDelegate() {
        return delegate;
    }
}
