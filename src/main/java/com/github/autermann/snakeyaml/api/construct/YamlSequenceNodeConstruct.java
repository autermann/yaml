package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;

import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;

/**
 * Constructs a {@link YamlSequenceNode} from a mapping node.
 */
public class YamlSequenceNodeConstruct extends AbstractYamlConstruct {

    /**
     * Creates a new {@link YamlSequenceNodeConstruct}.
     *
     * @param nodeFactory the node factory
     * @param delegate    the delegate
     */
    public YamlSequenceNodeConstruct(YamlNodeFactory nodeFactory,
                                     YamlNodeConstructor delegate) {
        super(nodeFactory, delegate);
    }

    @Override
    public YamlSequenceNode construct(Node node) {
        YamlSequenceNode seq = getNodeFactory().sequenceNode();
        for (Object o : getDelegate().constructSequence((SequenceNode) node)) {
            seq.add((YamlNode) o);
        }
        return seq;
    }

}
