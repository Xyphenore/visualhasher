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

/**
 * The action menu interface.
 * Provided getters for elements of the action menu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public interface ActionMenu {
    /**
     * Get the run menu item.
     *
     * @return JMenuItem Returns the runItem.
     *
     * @see RunItem
     * @since 1.0.0
     */
    JMenuItem getRunItem();

    /**
     * Get the re-run menu item.
     *
     * @return JMenuItem Returns the reRunItem.
     *
     * @see ReRunItem
     * @since 1.0.0
     */
    JMenuItem getReRunItem();

    /**
     * Get the cancel menu item.
     *
     * @return JMenuItem Returns the cancelItem.
     *
     * @see CancelItem
     * @since 1.0.0
     */
    JMenuItem getCancelItem();
}
