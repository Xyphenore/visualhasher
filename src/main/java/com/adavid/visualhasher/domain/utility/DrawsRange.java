/*
 * VisualHasher Copyright (C) 2022 DAVID Axel
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.adavid.visualhasher.domain.utility;

import java.util.Objects;

public final class DrawsRange {
    private final Range interval;

    public DrawsRange(final NumberOfBoxes boxes) {
        super();
        this.interval = new Range(boxes.get() / 2, boxes.get());
    }

    private DrawsRange() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a DrawsRange without specifying the number of boxes. Please use the public constructor " + "and specified " + "the number of boxes.");
    }

    public boolean contains(final int value) {
        return this.interval.contains(value);
    }

    @Override
    public int hashCode() {
        return null != this.interval ? this.interval.hashCode() : 0;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (null == object || this.getClass() != object.getClass()) {
            return false;
        }

        final DrawsRange that = (DrawsRange) object;

        return Objects.equals(this.interval, that.interval());
    }

    private Range interval() {
        return this.interval;
    }

    @Override
    public String toString() {
        return "DrawsRange{" + "interval=" + this.interval + "}";
    }

    public int min() {
        return this.interval.min();
    }

    public int max() {
        return this.interval.max();
    }

    public Range convertToRange() {
        return this.interval();
    }
}
