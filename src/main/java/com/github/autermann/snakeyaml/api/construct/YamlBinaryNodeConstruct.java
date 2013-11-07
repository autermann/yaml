package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;

/**
 * Constructs a {@link YamlBinaryNode} from a scalar node.
 */
public class YamlBinaryNodeConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * The encoding for {@link YamlBinaryNode}s.
     */
    final BaseEncoding binaryEncoding;

    /**
     * Creates a new {@link YamlBinaryNodeConstruct}.
     *
     * @param nodeFactory    the node factory
     * @param delegate       the delegate
     * @param binaryEncoding the binary encoding
     */
    public YamlBinaryNodeConstruct(YamlNodeFactory nodeFactory,
                                   YamlNodeConstructor delegate,
                                   BaseEncoding binaryEncoding) {
        super(nodeFactory, delegate);
        this.binaryEncoding = Preconditions.checkNotNull(binaryEncoding);
    }

    @Override
    protected AbstractYamlScalarNode<?> construct(String value) {
        return getNodeFactory().binaryNode(binaryEncoding.decode(value));
    }

}
