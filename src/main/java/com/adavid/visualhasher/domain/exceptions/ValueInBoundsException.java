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

package com.adavid.visualhasher.domain.exceptions;

import com.adavid.ranges.CommonIntegerRange;

import java.io.Serial;

/**
 * Thrown to indicate the value is inside the interval, but the program indicates the value is outside.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.domain.exceptions.DrawsOutOfBoundsException
 * @see com.adavid.visualhasher.domain.exceptions.ValueOutOfBoundsException
 * @since 1.0.0
 */
final class ValueInBoundsException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -2022885818925304387L;

    private ValueInBoundsException() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a ValueInBoundsException without a value and an interval. Please call a public constructor with the value and the interval.");
    }

    /**
     * Constructs a ValueInBoundsException with the value and the interval.
     *
     * @param value Int. The value seems to be outside.
     * @param interval CommonIntegerRange. The interval of available values.
     *
     * @since 1.0.0
     */
    ValueInBoundsException(final int value, final CommonIntegerRange interval) {
        this(value, interval, null);
    }

    /**
     * Constructs a ValueInBoundsException with the value and the interval.
     *
     * @param value Int. The value seems to be outside.
     * @param interval CommonIntegerRange. The interval of available values.
     * @param cause Throwable. The cause.
     *
     * @since 1.0.0
     */
    ValueInBoundsException(final int value, final CommonIntegerRange interval, final Throwable cause) {
        super(ValueInBoundsException.generateErrorMessage(value, interval), cause);
    }

    private static String generateErrorMessage(final int value, final CommonIntegerRange interval) {
        if (!interval.contains(value)) {
            throw new ValueOutOfBoundsException(value, interval);
        }

        final var min = interval.min();
        final var max = interval.max();

        return "The number '" + value + "' is in the interval. Value: " + value + ", min: " + min + ", max: " + max + "." + "Please give a value outside the interval: " + min + " and " + max + ".";
    }
}
