package com.github.autermann.snakeyaml.api.construct;

import com.github.autermann.snakeyaml.api.YamlNodeFactory;
import org.joda.time.format.DateTimeFormatter;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;
import com.google.common.base.Preconditions;

/**
 * Constructs a {@link YamlTimeNode} from a scalar node.
 */
public class YamlTimeNodeConstruct extends AbstractYamlScalarNodeConstruct {

    /**
     * The encoding for {@link YamlTimeNode}s.
     */
    final DateTimeFormatter timeEncoding;

    /**
     * Creates a new {@link YamlTimeNodeConstruct}.
     *
     * @param nodeFactory  the node factory
     * @param delegate     the delegate
     * @param timeEncoding the time encoding
     */
    public YamlTimeNodeConstruct(YamlNodeFactory nodeFactory,
                                 YamlNodeConstructor delegate,
                                 DateTimeFormatter timeEncoding) {
        super(nodeFactory, delegate);
        this.timeEncoding = Preconditions.checkNotNull(timeEncoding);
    }

    @Override
    protected AbstractYamlScalarNode<?> construct(String value) {
        return getNodeFactory().dateTimeNode(timeEncoding.parseDateTime(value));
    }

}
