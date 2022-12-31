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

package com.adavid.visualhasher.presentation.views.components.actionmenu;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The run item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ActionMenu
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class RunItem extends JMenuItem {
    /**
     * Create the default run item.
     *
     * @since 1.0.0
     */
    public RunItem() {
        super("Run");
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        super.setActionCommand("run");
        super.setEnabled(true);
        super.setToolTipText("Run the selected hash function.");
        super.setName("run");
        super.setVisible(true);
        super.setOpaque(true);
        super.setHideActionText(false);
        super.getAccessibleContext().setAccessibleName("Run");
        super.getAccessibleContext().setAccessibleDescription("Run the selected hash function.");
    }
}
