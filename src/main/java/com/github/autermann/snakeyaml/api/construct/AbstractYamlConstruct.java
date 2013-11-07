package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;

import com.google.common.base.Preconditions;

/**
 * {@link Construct} to construct {@link YamlNode}s using
 * {@link YamlNodeConstructor} and {@link YamlNodeFactory} delegates.
 */
public abstract class AbstractYamlConstruct extends AbstractConstruct {

    /**
     * The {@link YamlNodeFactory} to delegate to.
     */
    private final YamlNodeFactory nodeFactory;
    /**
     * The {@link YamlNodeConstructor} to delegate to.
     */
    private final YamlNodeConstructor delegate;

    /**
     * Creates a new {@link AbstractYamlConstruct} backed by the specified
     * {@link YamlNodeFactory} and {@link YamlNodeConstructor}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    protected AbstractYamlConstruct(YamlNodeFactory nodeFactory,
                                    YamlNodeConstructor delegate) {
        this.nodeFactory = Preconditions.checkNotNull(nodeFactory);
        this.delegate = Preconditions.checkNotNull(delegate);
    }

    /**
     * Gets the {@link YamlNodeFactory} for this construct.
     *
     * @return the node factory
     */
    public YamlNodeFactory getNodeFactory() {
        return nodeFactory;
    }

    /**
     * Gets the {@link YamlNodeConstructor} delegate of this construct.
     *
     * @return the delegate
     */
    public YamlNodeConstructor getDelegate() {
        return delegate;
    }

}
