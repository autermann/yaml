package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import java.math.BigDecimal;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBigDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDoubleNode;
import com.github.autermann.snakeyaml.api.nodes.YamlFloatNode;

/**
 * Constructs a {@link YamlDecimalNode} from a scalar node.
 *
 * @see YamlBigDecimalNode
 * @see YamlDoubleNode
 * @see YamlFloatNode
 */
public class YamlDecimalNodeConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * Creates a new {@link YamlDecimalNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlDecimalNodeConstruct(YamlNodeFactory nodeFactory,
                                    YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public AbstractYamlScalarNode<?> construct(String value) {
        String v = value.trim().toLowerCase().replaceAll("_", "");
        if (v.equals(".inf")) {
            return getNodeFactory().doubleNode(Double.POSITIVE_INFINITY);
        } else if (v.equals(".nan")) {
            return getNodeFactory().doubleNode(Double.NaN);
        } else if (v.equals("-.inf")) {
            return getNodeFactory().doubleNode(Double.NEGATIVE_INFINITY);
        }
        return getNodeFactory().bigDecimalNode(new BigDecimal(value));
    }

}
