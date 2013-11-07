package com.github.autermann.snakeyaml.api.construct;


import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;

/**
 * Constructs a {@link YamlNullNode} from a scalar node.
 */
public class YamlNullNodeConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * Creates a new {@link YamlNullNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlNullNodeConstruct(YamlNodeFactory nodeFactory,
                                 YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public AbstractYamlScalarNode<?> construct(String value) {
        return getNodeFactory().nullNode();
    }

}
