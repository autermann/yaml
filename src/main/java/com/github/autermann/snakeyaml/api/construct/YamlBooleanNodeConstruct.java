package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;

/**
 * Constructs a {@link YamlBooleanNode} from a scalar node.
 */
public class YamlBooleanNodeConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * Creates a new {@link YamlBooleanNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlBooleanNodeConstruct(YamlNodeFactory nodeFactory,
                                    YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public AbstractYamlScalarNode<?> construct(String value) {
        return getNodeFactory().booleanNode(Boolean.valueOf(value));
    }

}
