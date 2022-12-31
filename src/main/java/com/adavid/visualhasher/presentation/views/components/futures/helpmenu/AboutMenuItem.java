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

package com.adavid.visualhasher.presentation.views.components.futures.helpmenu;

import javax.swing.JMenuItem;

/**
 * Create an about menu item.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @implNote Not implemented, not finish (for v1.1.0).
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.1.0
 */
public final class AboutMenuItem extends JMenuItem {
    /**
     * Create the default AboutMenuItem.
     *
     * @since 1.0.0
     */
    public AboutMenuItem() {
        super("About");
        super.getAccessibleContext().setAccessibleName("About");
        super.getAccessibleContext().setAccessibleDescription("Show about information.");
        super.setEnabled(true);
        super.setOpaque(true);
        super.setName("about");
        super.setToolTipText("Show about information.");
        super.setHideActionText(false);
        super.setActionCommand("about");
    }
}
