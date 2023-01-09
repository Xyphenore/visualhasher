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

package com.adavid.visualhasher.presentation.components.action.menu;

import com.adavid.visualhasher.presentation.SwingView;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;

/**
 * The re-run item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ActionMenu
 * @see SwingView
 * @see JMenuItem
 * @since 1.0.0
 */
public final class ReRunItem extends JMenuItem {
    @Serial
    private static final long serialVersionUID = 4461792047936751583L;
    private static final String INFORMATION = "Cancel the computing hash function, and run the selected hash function.";
    private static final String RE_RUN_LOWER_CASE = "re-run";
    private static final String RE_RUN_INIT_CASE = "Re-Run";

    /**
     * Create the default re-run item.
     *
     * @since 1.0.0
     */
    ReRunItem() {
        super(ReRunItem.RE_RUN_INIT_CASE);
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
        super.setActionCommand(ReRunItem.RE_RUN_LOWER_CASE);
        super.setEnabled(false);
        super.setToolTipText(ReRunItem.INFORMATION);
        super.setName(ReRunItem.RE_RUN_LOWER_CASE);
        super.setVisible(true);
        super.setOpaque(true);
        super.setHideActionText(false);
        super.getAccessibleContext().setAccessibleName(ReRunItem.RE_RUN_INIT_CASE);
        super.getAccessibleContext().setAccessibleDescription(ReRunItem.INFORMATION);
    }
}
