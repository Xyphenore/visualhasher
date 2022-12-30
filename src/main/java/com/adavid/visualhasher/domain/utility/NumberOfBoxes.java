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

import java.util.function.IntSupplier;

/**
 * A strong type integer, which represent a valid number of boxes.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see DrawsRange
 * @since 1.0.0
 */
public final class NumberOfBoxes implements IntSupplier {
    private final int boxes;

    /**
     * Create a NumberOfBoxes with the given number of boxes.
     *
     * @param boxes Int. The number must be between 2 and the positive integer infinity.
     */
    public NumberOfBoxes(final int boxes) {
        super();
        if (2 > boxes) {
            throw new IllegalNumberOfBoxesException("Cannot create a NumberOfBoxes with an invalid number of boxes. " + "Value: " + boxes + ", min: 2, max: inf. Please give a " + "value between 2 and the positive integer infinity.");
        }

        this.boxes = boxes;
    }

    private NumberOfBoxes() {
        super();
        throw new UnsupportedOperationException("Cannot create a NumberOfBoxes without the number of boxes. Please " + "call the public contructor with a number of boxes.");
    }

    @Override
    public int getAsInt() {
        return this.boxes;
    }
}
