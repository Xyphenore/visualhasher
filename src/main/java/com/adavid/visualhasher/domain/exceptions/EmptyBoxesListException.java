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

import com.adavid.visualhasher.domain.HashFunctionResult;

import java.io.Serial;

/**
 * Thrown to indicate that a method has been passed an empty boxes list argument.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see HashFunctionResult
 * @since 1.0.0
 */
public final class EmptyBoxesListException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -1323021214166237342L;

    /**
     * Constructs an EmptyBoxesListException with no detail message and no cause.
     *
     * @since 1.0.0
     */
    public EmptyBoxesListException() {
        super();
    }

    /**
     * Constructs an EmptyBoxesListException with the specified detail message.
     *
     * @param message String. Specified detail message. The message should be filled with a useful message.
     *
     * @since 1.0.0
     */
    public EmptyBoxesListException(final String message) {
        super(message);
    }

    /**
     * Constructs an EmptyBoxesListException with the specified detail message and cause.
     *
     * @param message String. Specified detail message. The message should be filled with a useful message.
     * @param cause Throwable. The cause of the exception.
     *
     * @since 1.0.0
     */
    public EmptyBoxesListException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an EmptyBoxesListException with the specified cause.
     *
     * @param cause Throwable. The cause of the exception.
     *
     * @since 1.0.0
     */
    public EmptyBoxesListException(final Throwable cause) {
        super(cause);
    }
}
