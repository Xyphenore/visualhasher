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

package com.adavid.visualhasher.presentation.views.components.futures;

import com.adavid.visualhasher.domain.exceptions.BlankStringException;
import com.adavid.visualhasher.domain.exceptions.EmptyStringException;
import com.adavid.visualhasher.presentation.views.exceptions.IllegalLocaleNameException;

import javax.swing.JRadioButtonMenuItem;
import java.util.Objects;

/**
 * LocaleMenuItem component, permits creating MenuItem with specified locale.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @implNote Not implemented, not finish (for v1.1.0).
 * @see LocaleMenu
 * @since 1.1.0
 */
public final class LocaleMenuItem extends JRadioButtonMenuItem {
    /**
     * Create a not selected LocaleMenuItem with the given name.
     *
     * @param name String. The locale's name. The name must not be null, empty or blank.
     *
     * @since 1.0.0
     */
    public LocaleMenuItem(final String name) {
        this(name, false);
    }

    /**
     * Create a LocaleMenuItem with the given name, and the state selected.
     *
     * @param name String. The locale's name. The name must not be null, empty or blank.
     * @param selected Boolean. True, if the locale is in default selected.
     *
     * @since 1.0.0
     */
    public LocaleMenuItem(final String name, final boolean selected) {
        super();

        // Verify preconditions
        try {
            final var notNullName = Objects.requireNonNull(name,
                                                           "The locale name is null. Please give a not null name."
                                                          );

            if (notNullName.isEmpty()) {
                throw new EmptyStringException("The locale name is empty. Please give a filled name.");
            }

            if (notNullName.isBlank()) {
                throw new BlankStringException("The locale name is blank. Please give a filled name.");
            }
        }
        catch (final RuntimeException error) {
            throw new IllegalLocaleNameException("Cannot create a LocaleMenuItem with an invalid name.", error);
        }

        final var lowerLocale = name.toLowerCase();
        final var initCapLocale = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        super.setText(initCapLocale);
        super.setSelected(selected);
        super.setActionCommand(lowerLocale);
        super.setEnabled(true);
        super.setOpaque(true);
        super.setName(lowerLocale);
        super.setVisible(true);
        super.getAccessibleContext().setAccessibleName(initCapLocale + " language");
        super.getAccessibleContext()
             .setAccessibleDescription("Change the locale to the " + initCapLocale + " language.");
    }

    private LocaleMenuItem() {
        super();
        throw new UnsupportedOperationException("cannot create a LocaleMenuItem without the locale name.");
    }
}
