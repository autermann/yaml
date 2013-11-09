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
package com.github.autermann.yaml.nodes;

import java.math.BigInteger;
import java.util.Date;

import org.joda.time.DateTime;
import org.yaml.snakeyaml.nodes.Tag;

import com.github.autermann.yaml.ReturningYamlNodeVisitor;
import com.github.autermann.yaml.YamlNodeVisitor;

/**
 * A abstract {@link com.github.autermann.yaml.YamlNode} for integral values.
 *
 * @author Christian Autermann
 */
public abstract class YamlIntegralNode extends AbstractYamlNumberNode {
    @Override
    public Tag tag() {
        return Tag.INT;
    }

    @Override
    public boolean isIntegral() {
        return true;
    }

    @Override
    public boolean isBigInteger() {
        return true;
    }

    @Override
    public boolean isLong() {
        return fitsIntoLong();
    }

    @Override
    public boolean isInt() {
        return fitsIntoInt();
    }

    @Override
    public boolean isShort() {
        return fitsIntoShort();
    }

    @Override
    public boolean isByte() {
        return fitsIntoByte();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(numberValue().longValue());
    }

    @Override
    public void accept(YamlNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(ReturningYamlNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Date asDateValue(Date defaultValue) {
        return fitsIntoLong() ? new Date(longValue()) : defaultValue;
    }

    @Override
    public DateTime asDateTimeValue(DateTime defaultValue) {
        return fitsIntoLong() ? new DateTime(longValue()) : defaultValue;
    }

    @Override
    public long asLongValue(long defaultValue) {
        return fitsIntoLong() ? value().longValue() : defaultValue;
    }

    @Override
    public int asIntValue(int defaultValue) {
        return fitsIntoInt() ? value().intValue() : defaultValue;
    }

    @Override
    public short asShortValue(short defaultValue) {
        return fitsIntoShort() ? value().shortValue() : defaultValue;
    }

    @Override
    public byte asByteValue(byte defaultValue) {
        return fitsIntoByte() ? value().byteValue() : defaultValue;
    }

    @Override
    public BigInteger asBigIntegerValue(BigInteger defaultValue) {
        return bigIntegerValue();
    }

    /**
     * Checks if this {@code BigInteger} isInRange into a {@code long}.
     *
     * @return if the value is in range
     */
    public abstract boolean fitsIntoLong();

    /**
     * Checks if this {@code BigInteger} isInRange into a {@code int}.
     *
     * @return if the value is in range
     */
    public abstract boolean fitsIntoInt();

    /**
     * Checks if this {@code BigInteger} isInRange into a {@code byte}.
     *
     * @return if the value is in range
     */
    public abstract boolean fitsIntoByte();

    /**
     * Checks if this {@code BigInteger} isInRange into a {@code short}.
     *
     * @return if the value is in range
     */
    public abstract boolean fitsIntoShort();

}
