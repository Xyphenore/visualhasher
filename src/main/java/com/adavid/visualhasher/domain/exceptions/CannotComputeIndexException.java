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

import com.adavid.visualhasher.domain.logics.QuadraticOpenAddressingHashFunctionWorker;

import java.io.Serial;

/**
 * Thrown to indicate, the quadratic open addressing hash function cannot compute an available index for a ball.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see QuadraticOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class CannotComputeIndexException extends Exception {
    @Serial
    private static final long serialVersionUID = -3075172788554142624L;

    /**
     * Constructs a CannotComputeIndexException with no detail message and no cause.
     *
     * @since 1.0.0
     */
    public CannotComputeIndexException() {
        super();
    }

    /**
     * Constructs a CannotComputeIndexException with the specified detail message.
     *
     * @param message String. Specified detail message. The message should be filled with a useful message.
     *
     * @since 1.0.0
     */
    public CannotComputeIndexException(final String message) {
        super(message);
    }

    /**
     * Constructs a CannotComputeIndexException with the specified detail message and cause.
     *
     * @param message String. Specified detail message. The message should be filled with a useful message.
     * @param cause Throwable. The cause of the exception.
     *
     * @since 1.0.0
     */
    public CannotComputeIndexException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a CannotComputeIndexException with the specified cause.
     *
     * @param cause Throwable. The cause of the exception.
     *
     * @since 1.0.0
     */
    public CannotComputeIndexException(final Throwable cause) {
        super(cause);
    }
}
