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
import java.util.Locale;

/**
 * The cancel item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ActionMenu
 * @see SwingView
 * @see JMenuItem
 * @since 1.0.0
 */
public final class CancelItem extends JMenuItem {
    private static final String CANCEL_LOWER_CASE = "cancel";
    private static final String CANCEL_INIT_CASE = CancelItem.generateCancelName();
    private static final String INFORMATION = "Cancel the computing hash function.";
    @Serial
    private static final long serialVersionUID = 2588506472229935277L;

    /**
     * Create the default cancel item.
     *
     * @since 1.0.0
     */
    CancelItem() {
        super(CancelItem.CANCEL_INIT_CASE);
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        super.setActionCommand(CancelItem.CANCEL_LOWER_CASE);
        super.setEnabled(false);
        super.setToolTipText(CancelItem.INFORMATION);
        super.setName(CancelItem.CANCEL_LOWER_CASE);
        super.setVisible(true);
        super.setOpaque(true);
        super.setHideActionText(false);
        super.getAccessibleContext().setAccessibleName(CancelItem.CANCEL_INIT_CASE);
        super.getAccessibleContext().setAccessibleDescription(CancelItem.INFORMATION);
    }

    private static String generateCancelName() {
        return CancelItem.CANCEL_LOWER_CASE.substring(0, 1)
                                           .toUpperCase(Locale.ENGLISH) + CancelItem.CANCEL_LOWER_CASE.substring(1);
    }
}
