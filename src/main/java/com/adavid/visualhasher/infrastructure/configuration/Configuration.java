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

package com.adavid.visualhasher.infrastructure.configuration;

import com.adavid.visualhasher.infrastructure.locales.Language;

/**
 * The application configuration.
 * Can access to version: {@link #VERSION}
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Configuration {
    /**
     * The application version.
     *
     * @since 1.0.0
     */
    public static final String VERSION = "1.0.0";

    private static Language lang = Language.ENGLISH;

    private Configuration() {
        super();
        throw new UnsupportedOperationException("Cannot create a configuration.");
    }

    /**
     * Get the selected language.
     *
     * @return Return the selected language.
     *
     * @implNote The language logic is not implemented. For the future, version 1.1.0.
     * @see Language
     * @since 1.1.0
     */
    public static Language language() {
        return Configuration.lang;
    }

    /**
     * Change the application language.
     *
     * @param language Language. A value of {@link Language} enum.
     *
     * @implNote The language logic is not implemented. For the future, version 1.1.0.
     * @see Language
     * @since 1.1.0
     */
    public static void changeLocale(final Language language) {
        // TODO(Axel) Change all locale
        Configuration.lang = language;
    }
}
