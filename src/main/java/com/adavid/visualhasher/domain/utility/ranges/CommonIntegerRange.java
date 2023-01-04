/*
 * VisualHasher Copyright (C) 2023 DAVID Axel
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

package com.adavid.visualhasher.domain.utility.ranges;

import java.io.Serial;
import java.io.Serializable;

/**
 * The common implementation of an IntegerRange.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see Range
 * @see IntegerRange
 * @since 1.0.0
 */
public class CommonIntegerRange implements Range<Integer>, Serializable {
    @Serial
    private static final long serialVersionUID = -3332345500411834850L;
    private final int min;
    private final int max;

    /**
     * Create a CommonIntegerRange with the two given bounds. Determine the max and the min of the interval.
     *
     * @param bound1 Int. The first bound.
     * @param bound2 Int. The second bound.
     *
     * @since 1.0.0
     */
    protected CommonIntegerRange(final int bound1, final int bound2) {
        super();
        this.min = Math.min(bound1, bound2);
        this.max = Math.max(bound1, bound2);
    }

    private CommonIntegerRange() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a range without specified bounds. Please use the public constructor and specify the interval.");
    }

    @Override
    public final boolean contains(final Integer value) {
        return Math.max(value, this.min) == Math.min(value, this.max);
    }

    @Override
    public final Integer min() {
        return this.min;
    }

    @Override
    public final Integer max() {
        return this.max;
    }

    @Override
    public final int hashCode() {
        var result = this.min;
        result = 31 * result + this.max;
        return result;
    }

    @Override
    public final boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (null == object || this.getClass() != object.getClass()) {
            return false;
        }

        final var range = (CommonIntegerRange) object;

        if (this.min != range.min()) {
            return false;
        }
        return this.max == range.max();
    }
}
