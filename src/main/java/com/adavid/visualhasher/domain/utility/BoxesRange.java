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

package com.adavid.visualhasher.domain.utility;

import com.adavid.ranges.CommonIntegerRange;
import com.adavid.ranges.Range;

/**
 * An interval for number of box.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see CommonIntegerRange
 * @see Range
 * @since 1.0.0
 */
public class BoxesRange extends CommonIntegerRange {
    /**
     * Create the interval for box.
     *
     * @param boxes NumberOfBoxes. Number of box selected by the user.
     *
     * @since 1.0.0
     */
    public BoxesRange(final NumberOfBoxes boxes) {
        super(2, boxes.getAsInt());
    }

    private BoxesRange() {
        // Necessary to throw an explicit exception
        super(0, 0);
        throw new UnsupportedOperationException(
                "Cannot create a BoxesRange without specifying the number of box. Please use the public constructor and specify the number of box.");
    }

    @Override
    public String toString() {
        return "BoxesRange{" + "min=" + this.min() + ", max=" + this.max() + "}";
    }
}
