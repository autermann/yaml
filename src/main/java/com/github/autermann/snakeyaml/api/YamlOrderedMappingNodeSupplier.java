package com.github.autermann.snakeyaml.api;

import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.google.common.base.Supplier;

/**
 * A {@link Supplier} for {@link YamlOrderedMappingNode}s backed by a
 * {@link YamlNodeFactory}.
 */
class YamlOrderedMappingNodeSupplier implements Supplier<YamlOrderedMappingNode> {

    /**
     * The backing factory.
     */
    private final YamlNodeFactory factory;

    /**
     * Creates a new {@link  YamlOrderedMappingNodeSupplier} for the supplied
     * factory.
     *
     * @param factory the factory to use
     */
    YamlOrderedMappingNodeSupplier(YamlNodeFactory factory) {
        this.factory = factory;
    }

    @Override
    public YamlOrderedMappingNode get() {
        return factory.orderedMappingNode();
    }

}
