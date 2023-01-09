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

package com.adavid.ranges;

/**
 * An integer interval.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see CommonIntegerRange
 * @see Range
 * @since 1.0.0
 */
public final class IntegerRange extends CommonIntegerRange {
    /**
     * Create an IntegerRange with the two given bounds. Determine the max and the min of the interval.
     *
     * @param bound1 Int. The first bound.
     * @param bound2 Int. The second bound.
     *
     * @since 1.0.0
     */
    public IntegerRange(final int bound1, final int bound2) {
        super(bound1, bound2);
    }

    private IntegerRange() {
        // Necessary to throw an explicit exception
        super(0, 0);
        throw new UnsupportedOperationException(
                "Cannot create an IntegerRange without specified bounds. Please use the public constructor and specify the interval.");
    }

    @Override
    public String toString() {
        return "IntegerRange{" + "min=" + this.min() + ", max=" + this.max() + "}";
    }
}
