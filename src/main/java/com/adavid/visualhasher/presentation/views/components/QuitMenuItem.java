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

package com.adavid.visualhasher.presentation.views.components;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

/**
 * Create a quit button for a JMenu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class QuitMenuItem extends JMenuItem {
    @Serial
    private static final long serialVersionUID = -4118181310071668747L;

    /**
     * Create the default quit menu item.
     *
     * @since 1.0.0
     */
    public QuitMenuItem() {
        super("Quit");
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        super.getAccessibleContext().setAccessibleName("Quit");
        super.getAccessibleContext().setAccessibleDescription("Quit the application.");
        super.setActionCommand("quit");
    }
}
