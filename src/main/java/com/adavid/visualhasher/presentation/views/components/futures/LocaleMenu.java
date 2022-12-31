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

import com.adavid.visualhasher.presentation.views.exceptions.EmptyLocalesListException;
import com.adavid.visualhasher.presentation.views.exceptions.IllegalLocalesListException;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A LocaleMenu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @implNote Not implemented, not finish (for v1.1.0).
 * @see LocaleMenuItem
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.1.0
 */
public final class LocaleMenu extends JMenu {
    private final ButtonGroup languageGroup;

    /**
     * Create the default LocaleMenu with default locales (English, French).
     * The English Locale is the default-selected locale.
     *
     * @since 1.0.0
     */
    public LocaleMenu() {
        this(LocaleMenu.getDefaultLocales());
    }

    /**
     * Create the LocaleMenu with the specified locale names list.
     * The first element was the default-selected locale.
     *
     * @param names List{@literal <}String{@literal >}. The list of locales.
     * The list must not be null or empty.
     * The names contained in the list, must not be null, empty or blank.
     *
     * @since 1.0.0
     */
    public LocaleMenu(final List<String> names) {
        super("Language");

        try {
            final var notNullList = Objects.requireNonNull(names,
                                                           "The list of locales is null. Please give a list not null."
                                                          );

            if (notNullList.isEmpty()) {
                throw new EmptyLocalesListException("The list of locales is empty. Please give a filled list.");
            }

            this.languageGroup = new ButtonGroup();

            final var size = names.size();
            for (var i = 0; i < size; ++i) {
                final var selected = 0 == i;

                final var localItem = new LocaleMenuItem(names.get(i), selected);
                this.languageGroup.add(localItem);
                super.add(localItem);
            }

            super.getAccessibleContext().setAccessibleName("Language");
            super.getAccessibleContext().setAccessibleDescription("Change the application language.");
            super.setActionCommand("language");
        }
        catch (final RuntimeException error) {
            throw new IllegalLocalesListException("Cannot create a LocaleMenu with an invalid locale list.", error);
        }
    }

    private static List<String> getDefaultLocales() {
        final List<String> locales = new ArrayList<>(2);
        locales.add("English");
        locales.add("French");

        return locales;
    }
}
