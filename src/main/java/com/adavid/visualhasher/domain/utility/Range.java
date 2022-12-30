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

public final class Range {
    private final int min;
    private final int max;

    public Range(final int bound1, final int bound2) {
        super();
        this.min = Math.min(bound1, bound2);
        this.max = Math.max(bound1, bound2);
    }

    private Range() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a range without specified bounds. Please use the public constructor and specified the interval.");
    }

    public boolean contains(final int value) {
        return Math.max(value, this.min) == Math.min(value, this.max);
    }

    @Override
    public int hashCode() {
        var result = this.min();
        result = 31 * result + this.max();
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (null == object || this.getClass() != object.getClass()) {
            return false;
        }

        final var range = (Range) object;

        if (this.min() != range.min()) {
            return false;
        }
        return this.max() == range.max();
    }

    @Override
    public String toString() {
        return "Range{" + "min=" + this.min() + ", max=" + this.max() + "}";
    }

    public int min() {
        return this.min;
    }

    public int max() {
        return this.max;
    }
}
