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

package com.adavid.visualhasher.presentation.components.spinners;

import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import java.io.Serial;

/**
 * DrawsSpinner.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see DrawsRange
 * @see CommonBaseIntegerSpinner
 * @since 1.0.0
 */
public final class DrawsSpinner extends CommonBaseIntegerSpinner<DrawsRange> {
    private static final int MAX_DRAWS = BoxesSpinner.MAX_BOXES;
    private static final int STEP_DRAWS = 1;
    @Serial
    private static final long serialVersionUID = -2775872471524394961L;

    /**
     * Create the default DrawsSpinner.
     *
     * @since 1.0.0
     */
    public DrawsSpinner() {
        this(new DrawsRange(new NumberOfBoxes(MAX_DRAWS)), STEP_DRAWS);
    }

    /**
     * Create a DrawsSpinner with the specified interval.
     *
     * @param interval DrawsRange. The interval of available values.
     * @param step Integer.
     *
     * @see DrawsRange
     * @since 1.0.0
     */
    private DrawsSpinner(final DrawsRange interval, final int step) {
        super(interval, step);
        super.setEnabled(true);
        super.setVisible(true);
        super.setOpaque(true);
        super.setFocusable(true);
        super.setName("drawNb");
    }
}
