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

import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.snakeyaml.api.nodes.AbstractYamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlScalarNode;
import com.github.autermann.snakeyaml.api.nodes.AbstractYamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBinaryNode;
import com.github.autermann.snakeyaml.api.nodes.YamlBooleanNode;
import com.github.autermann.snakeyaml.api.nodes.YamlDecimalNode;
import com.github.autermann.snakeyaml.api.nodes.YamlIntegralNode;
import com.github.autermann.snakeyaml.api.nodes.YamlMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlNullNode;
import com.github.autermann.snakeyaml.api.nodes.YamlOrderedMappingNode;
import com.github.autermann.snakeyaml.api.nodes.YamlPairsNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSequenceNode;
import com.github.autermann.snakeyaml.api.nodes.YamlSetNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTextNode;
import com.github.autermann.snakeyaml.api.nodes.YamlTimeNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public interface YamlNode {

    /**
     * @return if this node exists
     */
    boolean exists();

    /**
     * @return if this is a container node
     */
    boolean isContainer();

    /**
     * @return if this is a mapping node
     */
    boolean isMapping();

    /**
     * @return if this is oredered mapping node
     */
    boolean isOrderedMapping();

    /**
     * @return if this is pairs node
     */
    boolean isPairs();

    /**
     * @return if this is a sequence node
     */
    boolean isSequence();

    /**
     * @return if this is a set node
     */
    boolean isSet();

    /**
     * @return if this is a scalar node
     */
    boolean isScalar();

    /**
     * @return if this is a binary node
     */
    boolean isBinary();

    /**
     * @return if this is a boolean node
     */
    boolean isBoolean();

    /**
     * @return if this is a null node
     */
    boolean isNull();

    /**
     * @return if this is a number node
     */
    boolean isNumber();

    /**
     * @return if this is a decimal node
     */
    boolean isDecimal();

    /**
     * @return if this is a integral node
     */
    boolean isIntegral();

    /**
     * @return if this a text node
     */
    boolean isText();

    YamlMappingNode asMapping();
    YamlOrderedMappingNode asOrderedMapping();
    YamlPairsNode asPairs();
    YamlSequenceNode asSequence();
    YamlSetNode asSet();
    
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

    DateTime asDateTimeValue();
    DateTime asDateTimeValue(DateTime defaultValue);
    DateTime dateTimeValue();

    Date asDateValue();
    Date asDateValue(Date defaultValue);
    Date dateValue();
    
    <T extends YamlNode> T copy();

    void accept(Visitor visitor);
    <T> T accept(ReturningVisitor<T> visitor);

    Tag tag();

     String dump();
     void dump(Writer output);
     void dump(OutputStream output);
     String dump(DumperOptions options);
     void dump(Writer output, DumperOptions options);
     void dump(OutputStream output, DumperOptions options);
     String dump(Yaml yaml);
     void dump(Writer output, Yaml yaml);
     void dump(OutputStream output, Yaml yaml);

    
    @Override String toString();
    @Override int hashCode();
    @Override boolean equals(Object o);

    interface Visitor {
        void visit(YamlMappingNode node);
        void visit(YamlOrderedMappingNode node);
        void visit(YamlPairsNode node);
        void visit(YamlSequenceNode node);
        void visit(YamlSetNode node);
        void visit(YamlBinaryNode node);
        void visit(YamlBooleanNode node);
        void visit(YamlDecimalNode node);
        void visit(YamlIntegralNode node);
        void visit(YamlNullNode node);
        void visit(YamlTextNode node);
        void visit(YamlTimeNode node);
    }

    abstract class AbstractVisitor implements Visitor {
        @Override public void visit(YamlMappingNode node) { visitMapping(node); }
        @Override public void visit(YamlOrderedMappingNode node) { visitMapping(node); }
        @Override public void visit(YamlPairsNode node) { visitMapping(node); }
        @Override public void visit(YamlSequenceNode node) { visitSequence(node); }
        @Override public void visit(YamlSetNode node) { visitSequence(node); }
        @Override public void visit(YamlBinaryNode node) { visitScalar(node); }
        @Override public void visit(YamlBooleanNode node) { visitScalar(node); }
        @Override public void visit(YamlDecimalNode node) { visitScalar(node); }
        @Override public void visit(YamlIntegralNode node) { visitScalar(node); }
        @Override public void visit(YamlNullNode node) { visitScalar(node); }
        @Override public void visit(YamlTextNode node) { visitScalar(node); }
        @Override public void visit(YamlTimeNode node) { visitScalar(node); }
        protected void visitMapping(AbstractYamlMappingNode<?> node) {}
        protected void visitSequence(AbstractYamlSequenceNode<?> node) {}
        protected void visitScalar(AbstractYamlScalarNode<?> node) {}
    }

    interface ReturningVisitor<T> {
        T visit(YamlMappingNode node);
        T visit(YamlOrderedMappingNode node);
        T visit(YamlPairsNode node);
        T visit(YamlSequenceNode node);
        T visit(YamlSetNode node);
        T visit(YamlBinaryNode node);
        T visit(YamlBooleanNode node);
        T visit(YamlDecimalNode node);
        T visit(YamlIntegralNode node);
        T visit(YamlNullNode node);
        T visit(YamlTextNode node);
        T visit(YamlTimeNode node);
    }

     abstract class AbstractReturningVisitor<T> implements ReturningVisitor<T> {
        @Override public T visit(YamlMappingNode node) { return visitMapping(node); }
        @Override public T visit(YamlOrderedMappingNode node) { return visitMapping(node); }
        @Override public T visit(YamlPairsNode node) { return visitMapping(node); }
        @Override public T visit(YamlSequenceNode node) { return visitSequence(node); }
        @Override public T visit(YamlSetNode node) { return visitSequence(node); }
        @Override public T visit(YamlBinaryNode node) { return visitScalar(node); }
        @Override public T visit(YamlBooleanNode node) { return visitScalar(node); }
        @Override public T visit(YamlDecimalNode node) { return visitScalar(node); }
        @Override public T visit(YamlIntegralNode node) { return visitScalar(node); }
        @Override public T visit(YamlNullNode node) { return visitScalar(node); }
        @Override public T visit(YamlTextNode node) { return visitScalar(node); }
        @Override public T visit(YamlTimeNode node) { return visitScalar(node); }
        protected T visitMapping(AbstractYamlMappingNode<?> node) { return null; }
        protected T visitSequence(AbstractYamlSequenceNode<?> node) { return null; }
        protected T visitScalar(AbstractYamlScalarNode<?> node) { return null; }
    }
}
