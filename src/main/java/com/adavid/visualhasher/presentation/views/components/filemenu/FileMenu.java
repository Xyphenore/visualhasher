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

package com.adavid.visualhasher.presentation.views.components.filemenu;

import javax.swing.JMenu;

/**
 * Create a file menu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class FileMenu extends JMenu {
    private QuitMenuItem quit;

    /**
     * Create the default file menu.
     *
     * @since 1.0.0
     */
    public FileMenu() {
        this(null);
    }

    /**
     * Create a FileMenu with the QuitMenuItem button.
     *
     * @param quit QuitMenuItem. The quit button.
     *
     * @since 1.0.0
     */
    public FileMenu(final QuitMenuItem quit) {
        super("File");
        super.getAccessibleContext().setAccessibleName("File");
        super.getAccessibleContext().setAccessibleDescription(
                "File menu permits accessing to settings or exit the application.");
        this.quit = quit;
    }

    /**
     * Get the quit button.
     *
     * @return QuitMenuItem or null
     *
     * @since 1.0.0
     */
    public QuitMenuItem getQuit() {
        return quit;
    }

    /**
     * Set the quit button.
     *
     * @param quit QuitMenuItem or null.
     *
     * @since 1.0.0
     */
    public void setQuit(final QuitMenuItem quit) {
        this.quit = quit;
    }
}
