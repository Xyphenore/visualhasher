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

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.*;
import java.io.Serial;

/**
 * The progress bar to show the progress of the task.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ProgressBar extends JPanel {
    @Serial
    private static final long serialVersionUID = 6065908178935085150L;

    private final JProgressBar progressBar = new JProgressBar(0, 100);

    /**
     * Create the default progress bar.
     *
     * @since 1.0.0
     */
    public ProgressBar() {
        super(new FlowLayout());

        this.progressBar.setName("progressBar");
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
        this.progressBar.setFocusable(false);
        this.progressBar.setOpaque(true);
        this.progressBar.setStringPainted(true);

        super.add(this.progressBar);
    }
}
