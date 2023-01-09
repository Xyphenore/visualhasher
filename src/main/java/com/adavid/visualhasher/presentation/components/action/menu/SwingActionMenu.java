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

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.io.Serial;

/**
 * Implementation of the ActionMenu for the SwingView.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see SwingView
 * @see ActionMenu
 * @see JMenu
 * @since 1.0.0
 */
public final class SwingActionMenu extends JMenu implements ActionMenu {
    @Serial
    private static final long serialVersionUID = 6693041031716416612L;
    private static final String NAME = "Action";

    private final JMenuItem runItem = new RunItem();
    private final JMenuItem reRunItem = new ReRunItem();
    private final JMenuItem cancelItem = new CancelItem();

    /**
     * Create the default action menu.
     *
     * @since 1.0.0
     */
    public SwingActionMenu() {
        super(SwingActionMenu.NAME);
        super.getAccessibleContext().setAccessibleName(SwingActionMenu.NAME);
        super.getAccessibleContext().setAccessibleDescription(
                "Action menu permits running, cancel or re-run the computing of the hash function.");
        super.add(this.runItem);
        super.add(this.reRunItem);
        super.add(this.cancelItem);
    }

    @Override
    public JMenuItem getRunItem() {
        return this.runItem;
    }

    @Override
    public JMenuItem getReRunItem() {
        return this.reRunItem;
    }

    @Override
    public JMenuItem getCancelItem() {
        return this.cancelItem;
    }
}
