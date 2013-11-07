package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;

import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;

/**
 * Constructs a {@link YamlSetNode} from a mapping node.
 */
public class YamlSetNodeConstruct extends AbstractYamlConstruct {

    /**
     * Creates a new {@link YamlSetNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlSetNodeConstruct(YamlNodeFactory nodeFactory,
                                YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public YamlSetNode construct(Node node) {
        YamlSetNode set = getNodeFactory().setNode();
        for (Object o : getDelegate().constructSet((MappingNode) node)) {
            set.add((YamlNode) o);
        }
        return set;
    }

}
