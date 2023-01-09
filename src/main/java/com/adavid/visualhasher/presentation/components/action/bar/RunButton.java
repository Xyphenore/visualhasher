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

package com.adavid.visualhasher.presentation.components.action.bar;

import javax.swing.JButton;
import java.io.Serial;

/**
 * RunButton to run something.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class RunButton extends JButton {
    @Serial
    private static final long serialVersionUID = -6423936197534019374L;

    /**
     * Create the default run button.
     *
     * @since 1.0.0
     */
    RunButton() {
        super("Run");
        super.setEnabled(true);
        super.setVisible(true);
        super.setFocusable(true);
        super.setName("runButton");
        super.setOpaque(true);
        super.setToolTipText("Run the selected hash function with provided number of box and number of draws.");
        super.setHideActionText(false);
        super.setActionCommand("run");
    }
}
