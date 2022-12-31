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

package com.adavid.visualhasher.infrastructure;

import com.adavid.visualhasher.domain.exceptions.BlankStringException;
import com.adavid.visualhasher.domain.exceptions.EmptyStringException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The logger of the Application.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.Application
 * @since 1.0.0
 */
public final class ApplicationLogger {
    /**
     * Get the instance of logger associated to the given name.
     *
     * @param name String. The name must not be null, empty or blank.
     *
     * @return Returns Logger instance.
     *
     * @since 1.0.0
     */
    public static Logger getInstance(final String name) {
        // Verify preconditions
        try {
            final var notNullName = Objects.requireNonNull(name,
                                                           "The logger name is null. Please give a name not " + "null."
                                                          );

            if (notNullName.isEmpty()) {
                throw new EmptyStringException("The logger name is empty. Please give a filled name.");
            }

            if (notNullName.isBlank()) {
                throw new BlankStringException("The logger name is blank. Please give a filled name.");
            }
        }
        catch (final RuntimeException error) {
            throw new IllegalLoggerNameException("Cannot create an ApplicationLogger with an invalid name.", error);
        }

        final var logger = Logger.getLogger(name);
        logger.setLevel(Level.INFO);
        return logger;
    }
}
