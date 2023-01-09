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

package com.adavid.visualhasher.presentation.components.filemenu;

import com.adavid.visualhasher.presentation.SwingView;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Create a file menu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see SwingView
 * @since 1.0.0
 */
public final class SwingFileMenu extends JMenu implements FileMenu {
    private final JMenuItem quit = new QuitMenuItem();

    /**
     * Create a SwingFileMenu with the QuitMenuItem button.
     *
     * @since 1.0.0
     */
    public SwingFileMenu() {
        super("File");
        super.getAccessibleContext().setAccessibleName("File");
        super.getAccessibleContext().setAccessibleDescription(
                "File menu permits accessing to settings or exit the application.");
        super.add(this.quit);
    }

    @Override
    public JMenuItem getQuit() {
        return this.quit;
    }
}
