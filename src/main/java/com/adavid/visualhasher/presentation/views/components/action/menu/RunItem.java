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

package com.adavid.visualhasher.presentation.views.components.action.menu;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.util.Locale;

/**
 * The run item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ActionMenu
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @see JMenuItem
 * @since 1.0.0
 */
public final class RunItem extends JMenuItem {
    @Serial
    private static final long serialVersionUID = 2425172550498041971L;
    private static final String INFORMATION = "Run the selected hash function.";
    private static final String RUN_LOWER_CASE = "run";
    private static final String RUN_INIT_CASE = RunItem.generateRunName();

    /**
     * Create the default run item.
     *
     * @since 1.0.0
     */
    RunItem() {
        super(RunItem.RUN_INIT_CASE);
        super.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        super.setActionCommand(RunItem.RUN_LOWER_CASE);
        super.setEnabled(true);
        super.setToolTipText(RunItem.INFORMATION);
        super.setName(RunItem.RUN_LOWER_CASE);
        super.setVisible(true);
        super.setOpaque(true);
        super.setHideActionText(false);
        super.getAccessibleContext().setAccessibleName(RunItem.RUN_INIT_CASE);
        super.getAccessibleContext().setAccessibleDescription(RunItem.INFORMATION);
    }

    private static String generateRunName() {
        return RunItem.RUN_LOWER_CASE.substring(0, 1).toUpperCase(Locale.ENGLISH) + RunItem.RUN_LOWER_CASE.substring(1);
    }
}
