package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;

/**
 * {@link Construct} to construct {@link AbstractYamlScalarNode}s from
 * string scalar nodes.
 */
public abstract class AbstractYamlScalarNodeConstruct extends AbstractYamlConstruct {

    /**
     * Creates a new {@link AbstractScalarConstruct} backed by the specified
     * {@link YamlNodeFactory} and {@link YamlNodeConstructor}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public AbstractYamlScalarNodeConstruct(YamlNodeFactory nodeFactory,
                                           YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public Object construct(Node node) {
        return construct(((ScalarNode) node).getValue());
    }

    /**
     * Construct a {@link YamlNode} from the specified scalar value.
     *
     * @param value the value of the scalar
     *
     * @return the {@link YamlNode}
     */
    protected abstract AbstractYamlScalarNode<?> construct(String value);

}
