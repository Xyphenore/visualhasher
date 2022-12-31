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

import com.adavid.visualhasher.domain.utility.DrawsRange;

import java.io.Serial;

/**
 * Thrown to indicate the number of draws is outside the draw interval.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.domain.AbstractHashFunctionWorker
 * @since 1.0.0
 */
public final class DrawsOutOfBoundsException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -8849772928383299324L;

    private DrawsOutOfBoundsException() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a DrawsOutOfBoundsException without a value and an interval." + " Please call a public constructor with a value and an interval.");
    }

    /**
     * Constructs a DrawsOutOfBoundsException with the user value and the draw interval.
     *
     * @param value Int. The user draws value.
     * @param interval DrawsRange. The interval of available draws value.
     *
     * @since 1.0.0
     */
    public DrawsOutOfBoundsException(final int value, final DrawsRange interval) {
        this(value, interval, null);
    }

    /**
     * Constructs a DrawsOutOfBoundsException with the user value and the draw interval, and the cause.
     *
     * @param value Int. The user draws value.
     * @param interval DrawsRange. The interval of available draws value.
     * @param cause Throwable. The cause.
     *
     * @since 1.0.0
     */
    public DrawsOutOfBoundsException(final int value, final DrawsRange interval, final Throwable cause) {
        super(DrawsOutOfBoundsException.generateErrorMessage(value, interval), cause);
    }

    private static String generateErrorMessage(final int value, final DrawsRange interval) {
        if (interval.contains(value)) {
            throw new ValueInBoundsException(value, interval);
        }

        final var min = interval.min();
        final var max = interval.max();

        return "The number of draws is outside the interval. Value: " + value + ", min: " + min + ", max: " + max + "." + " Please give a value between: " + min + " and " + max + ".";
    }
}
