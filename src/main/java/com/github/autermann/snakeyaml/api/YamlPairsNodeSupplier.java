package com.github.autermann.snakeyaml.api;

import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.google.common.base.Supplier;

/**
 * A {@link Supplier} for {@link YamlPairsNode}s backed by a
 * {@link YamlNodeFactory}.
 */
class YamlPairsNodeSupplier implements Supplier<YamlPairsNode> {

    /**
     * The backing factory.
     */
    private final YamlNodeFactory factory;

    /**
     * Creates a new {@link  YamlPairsNodeSupplier} for the supplied
     * factory.
     *
     * @param factory the factory to use
     */
    YamlPairsNodeSupplier(YamlNodeFactory factory) {
        this.factory = factory;
    }

    @Override
    public YamlPairsNode get() {
        return factory.pairsNode();
    }

}
