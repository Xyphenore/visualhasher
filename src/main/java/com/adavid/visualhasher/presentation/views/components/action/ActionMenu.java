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

import javax.swing.JMenu;
import java.io.Serial;

/**
 * The action menu.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see RunItem
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class ActionMenu extends JMenu {
    @Serial
    private static final long serialVersionUID = 6693041031716416612L;

    private RunItem runItem;
    private ReRunItem reRunItem;
    private CancelItem cancelItem;

    /**
     * Create the default action menu.
     *
     * @since 1.0.0
     */
    public ActionMenu() {
        this(null, null, null);
    }

    public ActionMenu(final RunItem runItem, final ReRunItem reRunItem, final CancelItem cancelItem) {
        super("Action");
        super.getAccessibleContext().setAccessibleName("Action");
        super.getAccessibleContext().setAccessibleDescription(
                "Action menu permits running, cancel or re-run the computing of the hash function.");

        this.runItem = runItem;
        this.reRunItem = reRunItem;
        this.cancelItem = cancelItem;
    }

    public RunItem getRunItem() {
        return this.runItem;
    }

    public void setRunItem(final RunItem runItem) {
        this.runItem = runItem;
    }

    public ReRunItem getReRunItem() {
        return this.reRunItem;
    }

    public void setReRunItem(final ReRunItem reRunItem) {
        this.reRunItem = reRunItem;
    }

    public CancelItem getCancelItem() {
        return this.cancelItem;
    }

    public void setCancelItem(final CancelItem cancelItem) {
        this.cancelItem = cancelItem;
    }
}
