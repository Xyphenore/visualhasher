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

package com.adavid.visualhasher;

import com.adavid.visualhasher.presentation.SwingView;

import javax.swing.SwingUtilities;

/**
 * The VisualHasher application.
 * Use the static method main to start the application.
 * Use Swing to show information and interact with the user.
 *
 * @author Axel DAVID
 * @version 2.0.0
 * @since 1.0.0
 */
public final class SwingApplication {
    private final SwingView view;

    /**
     * Create a SwingApplication with the default title.
     *
     * @since 1.0.0
     */
    public SwingApplication() {
        this(null);
    }

    /**
     * Create a SwingApplication with the given title.
     *
     * @param title {@link String} The application's title.
     *
     * @since 1.0.0
     */
    public SwingApplication(final String title) {
        super();

        if (null == title) {
            this.view = new SwingView();
        }
        else {
            this.view = new SwingView(title);
        }
    }

    /**
     * The entry point of the application.
     * Give the name of the application in the first argument of the run command.
     *
     * @param args {@link String}[] Arguments of application.
     *
     * @since 1.0.0
     */
    public static void main(final String[] args) {
        final String title = ((null == args) || (0 == args.length)) ? null : args[0];

        SwingUtilities.invokeLater(() -> new SwingApplication(title));
    }
}
