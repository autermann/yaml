package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlMappingNode;
import com.google.common.base.Supplier;

/**
 * Constructs a {@link AbstractYamlMappingNode} from a mapping node.
 */
public class YamlMappingNodeConstruct extends AbstractYamlConstruct {

    /**
     * A supplier for {@link AbstractYamlMappingNode} instances.
     */
    final Supplier<? extends AbstractYamlMappingNode<?>> supplier;

    /**
     * Creates a new {@link YamlMappingNodeConstruct} using
     * {@literal supplier}
     * to create concrete instances.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     * @param supplier    the supplier
     */
    YamlMappingNodeConstruct(YamlNodeFactory nodeFactory,
                             YamlNodeConstructor delegate,
                             Supplier<? extends AbstractYamlMappingNode<?>> supplier) {
        super(nodeFactory, delegate);
        this.supplier = supplier;
    }

    @Override
    public YamlNode construct(Node node) {
        AbstractYamlMappingNode<?> mapping = supplier.get();
        MappingNode mnode = (MappingNode) node;
        for (NodeTuple tuple : mnode.getValue()) {
            mapping.put((YamlNode) getDelegate()
                    .constructObject(tuple.getKeyNode()), (YamlNode) getDelegate()
                    .constructObject(tuple.getValueNode()));
        }
        return mapping;
    }

}
