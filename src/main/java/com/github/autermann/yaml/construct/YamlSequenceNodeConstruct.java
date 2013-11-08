/*
 * Copyright 2013 Christian Autermann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.autermann.yaml.construct;

import com.github.autermann.yaml.YamlNode;
import com.github.autermann.yaml.YamlNodeFactory;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;

import com.github.autermann.yaml.nodes.YamlSequenceNode;

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
