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

import com.adavid.visualhasher.domain.exceptions.IllegalNumberOfBoxesException;

public final class NumberOfBoxes {
    private final int boxes;

    public NumberOfBoxes(final int boxes) {
        super();
        if (2 > boxes) {
            throw new IllegalNumberOfBoxesException("Cannot create a NumberOfBoxes with an invalid number of boxes. " + "Value: " + boxes + ", min: 2, max: inf. Please give a " + "value between 2 and the positive integer infinity.");
        }

        this.boxes = boxes;
    }

    int get() {
        return this.boxes;
    }
}
