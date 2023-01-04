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

package com.adavid.visualhasher.presentation.views.components.action.bar;

import javax.swing.JButton;
import java.io.Serial;

/**
 * ReRunButton.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ReRunButton extends JButton {
    @Serial
    private static final long serialVersionUID = 1474488170073873249L;

    /**
     * Create the default ReRunButton.
     *
     * @since 1.0.0
     */
    ReRunButton() {
        super("Re-Run");
        super.setEnabled(false);
        super.setVisible(false);
        super.setFocusable(false);
        super.setName("reRunButton");
        super.setOpaque(true);
        super.setToolTipText(
                "Stop the running operation and run the selected hash function with provided number of boxes and number of draws.");
        super.setHideActionText(false);
        super.setActionCommand("re-run");
    }
}
