/*
 * Copyright (C) 2013 Christian Autermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.autermann.snakeyaml.api;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.collection.MappingNode;
import com.github.autermann.snakeyaml.api.collection.OrderedMappingNode;
import com.github.autermann.snakeyaml.api.collection.PairsNode;
import com.github.autermann.snakeyaml.api.collection.SequenceNode;
import com.github.autermann.snakeyaml.api.collection.SetNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public interface Node {

    boolean exists();

    boolean isContainer();
    boolean isMapping();
    boolean isOrderedMapping();
    boolean isPairs();
    boolean isSequence();
    boolean isSet();

    boolean isScalar();
    boolean isBinary();
    boolean isBoolean();
    boolean isNull();
    boolean isNumber();
    boolean isString();

    MappingNode asMapping();
    OrderedMappingNode asOrderedMapping();
    PairsNode asPairs();
    SequenceNode asSequence();
    SetNode asSet();
    
    BigDecimal asBigDecimalValue();
    BigDecimal asBigDecimalValue(BigDecimal defaultValue);
    BigDecimal bigDecimalValue();
    
    BigInteger asBigIntegerValue();
    BigInteger asBigIntegerValue(BigInteger defaultValue);
    BigInteger bigIntegerValue();
    
    boolean asBooleanValue();
    boolean asBooleanValue(boolean defaultValue);
    boolean booleanValue();
    
    byte asByteValue();
    byte asByteValue(byte defaultValue);
    byte byteValue();
    
    byte[] asBinaryValue();
    byte[] asBinaryValue(byte[] defaultValue);
    byte[] binaryValue();
    
    double asDoubleValue();
    double asDoubleValue(double defaultValue);
    double doubleValue();
    
    float asFloatValue();
    float asFloatValue(float defaultValue);
    float floatValue();
    
    int asIntValue();
    int asIntValue(int defaultValue);
    int intValue();
    
    long asLongValue();
    long asLongValue(long defaultValue);
    long longValue();
    
    Number asNumberValue();
    Number asNumberValue(Number defaultValue);
    Number numberValue();
    
    short asShortValue();
    short asShortValue(short defaultValue);
    short shortValue();
    
    String asTextValue();
    String asTextValue(String defaultValue);
    String textValue();
    
    <T extends Node> T copy();
    Tag tag();
    
    @Override String toString();
    @Override int hashCode();
    @Override boolean equals(Object o);
}
