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

package com.adavid.visualhasher.presentation.components.boxes;

import com.adavid.visualhasher.domain.objects.ColoredUniqueBallBox;

import javax.swing.SwingWorker;
import java.util.List;

public final class ColoredBoxesGeneratorWorker extends SwingWorker<Void, Integer> {
    private final int boxesNb;
    private final BoxesPanel panel;

    public ColoredBoxesGeneratorWorker(final int boxesNb, final BoxesPanel panel) {
        super();
        this.boxesNb = boxesNb;
        this.panel = panel;
        this.panel.getBoxesList().setVisible(false);
        this.panel.clear();
    }

    @Override
    protected Void doInBackground() {
        for (var i = 0; i < this.boxesNb; ++i) {
            this.publish(i);
            this.setProgress(i * 100 / this.boxesNb);
        }

        return null;
    }

    @Override
    protected void process(final List<Integer> chunks) {
        for (final var index : chunks) {
            this.panel.addBox(new ColoredUniqueBallBox(index));
        }
    }

    @Override
    protected void done() {
        this.panel.getBoxesList().setVisible(true);
    }
}
