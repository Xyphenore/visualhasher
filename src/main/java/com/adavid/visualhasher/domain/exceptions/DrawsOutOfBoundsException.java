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

package com.adavid.visualhasher.domain.exceptions;

import com.adavid.visualhasher.domain.utility.Range;

import java.io.Serial;

public final class DrawsOutOfBoundsException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -8849772928383299324L;

    private DrawsOutOfBoundsException() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a DrawsOutOfBoundsException without a value and an interval." + " Please call a public constructor with a value and an interval.");
    }

    public DrawsOutOfBoundsException(final int value, final Range interval) {
        this(value, interval, null);
    }

    public DrawsOutOfBoundsException(final int value, final Range interval, final Throwable cause) {
        super(DrawsOutOfBoundsException.generateErrorMessage(value, interval), cause);
    }

    private static String generateErrorMessage(final int value, final Range interval) {
        if (interval.contains(value)) {
            throw new ValueInBoundsException(value, interval);
        }

        final var min = interval.min();
        final var max = interval.max();

        return "The number of draws is outside the interval. Value: " + value + ", min: " + min + ", max: " + max + "." + " Please give a value between: " + min + " and " + max + ".";
    }
}
