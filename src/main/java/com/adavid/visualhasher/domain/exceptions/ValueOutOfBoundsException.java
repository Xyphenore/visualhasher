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

import com.adavid.visualhasher.domain.utility.ranges.CommonIntegerRange;

import java.io.Serial;

/**
 * Thrown to indicate the value is outside the interval, but the program indicates the value is inside.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.domain.exceptions.ValueInBoundsException
 * @since 1.0.0
 */
final class ValueOutOfBoundsException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -2008033644432226109L;

    private ValueOutOfBoundsException() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a ValueOutOfBoundsException without a value and an interval. Please call a public constructor with the value and the interval.");
    }

    /**
     * Constructs a ValueOutOfBoundsException with the value and the interval.
     *
     * @param value Int. The value seems to be inside.
     * @param interval CommonIntegerRange. The interval of available values.
     *
     * @since 1.0.0
     */
    ValueOutOfBoundsException(final int value, final CommonIntegerRange interval) {
        this(value, interval, null);
    }

    /**
     * Constructs a ValueOutOfBoundsException with the value and the interval.
     *
     * @param value Int. The value seems to be inside.
     * @param interval CommonIntegerRange. The interval of available values.
     * @param cause Throwable. The cause.
     *
     * @since 1.0.0
     */
    ValueOutOfBoundsException(final int value, final CommonIntegerRange interval, final Throwable cause) {
        super(ValueOutOfBoundsException.generateErrorMessage(value, interval), cause);
    }

    private static String generateErrorMessage(final int value, final CommonIntegerRange interval) {
        if (interval.contains(value)) {
            throw new ValueInBoundsException(value, interval);
        }

        final var min = interval.min();
        final var max = interval.max();

        return "The number '" + value + "' is outside the interval. Value: " + value + ", min: " + min + ", max: " + max + "." + "Please give a value inside the interval: " + min + " and " + max + ".";
    }
}
