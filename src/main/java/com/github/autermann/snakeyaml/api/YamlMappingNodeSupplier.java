package com.github.autermann.snakeyaml.api;

import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.google.common.base.Supplier;

/**
 * A {@link Supplier} for {@link YamlMappingNode}s backed by a
 * {@link YamlNodeFactory}.
 */
class YamlMappingNodeSupplier implements Supplier<YamlMappingNode> {

    /**
     * The backing factory.
     */
    private final YamlNodeFactory factory;

    /**
     * Creates a new {@link  YamlMappingNodeSupplier} for the supplied
     * factory.
     *
     * @param factory the factory to use
     */
    YamlMappingNodeSupplier(YamlNodeFactory factory) {
        this.factory = factory;
    }

    @Override
    public YamlMappingNode get() {
        return factory.mappingNode();
    }

}
