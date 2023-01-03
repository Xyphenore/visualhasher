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

package com.adavid.visualhasher.presentation.views.components.action;

import javax.swing.JButton;
import java.io.Serial;

/**
 * CancelButton.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CancelButton extends JButton {
    @Serial
    private static final long serialVersionUID = -1781554196091775746L;

    /**
     * Create the default CancelButton.
     *
     * @since 1.0.0
     */
    public CancelButton() {
        super("Cancel");
        super.setEnabled(false);
        super.setVisible(false);
        super.setFocusable(false);
        super.setName("cancelButton");
        super.setOpaque(true);
        super.setToolTipText("Stop the running operation.");
        super.setHideActionText(false);
        super.setActionCommand("cancel");
    }
}
