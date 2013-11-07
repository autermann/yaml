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
package com.github.autermann.snakeyaml.api.nodes;

import java.math.BigDecimal;

import com.github.autermann.snakeyaml.api.YamlNode;
import com.google.common.base.Preconditions;

/**
 * A {@link YamlNode} for {@link BigDecimal}s.
 *
 * @author Christian Autermann
 */
public class YamlBigDecimalNode extends YamlDecimalNode {
    /**
     * The {@link BigDecimal} value.
     */
    private final BigDecimal value;

    /**
     * Create a new {@link YamlBigDecimalNode}.
     *
     * @param value the value
     */
    public YamlBigDecimalNode(BigDecimal value) {
        this.value = Preconditions.checkNotNull(value);
    }

    @Override
    public BigDecimal value() {
        return this.value;
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return this.value;
    }
}
