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
 * The re-run item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ActionMenu
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class ReRunItem extends JMenuItem {
    /**
     * Create the default re-run item.
     *
     * @since 1.0.0
     */
    public ReRunItem() {
        super("Re-Run");
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
        super.setActionCommand("re-run");
        super.setEnabled(false);
        super.setToolTipText("Cancel the computing hash function, and run the selected hash function.");
        super.setName("re-run");
        super.setVisible(true);
        super.setOpaque(true);
        super.setHideActionText(false);
        super.getAccessibleContext().setAccessibleName("Re-Run");
        super.getAccessibleContext().setAccessibleDescription(
                "Cancel the computing hash function, and run the selected hash function.");
    }
}
