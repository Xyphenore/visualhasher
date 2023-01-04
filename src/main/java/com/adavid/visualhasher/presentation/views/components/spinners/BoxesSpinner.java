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

package com.adavid.visualhasher.presentation.views.components.spinners;

import com.adavid.visualhasher.domain.utility.BoxesRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import java.io.Serial;

/**
 * BoxesSpinner.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see BoxesRange
 * @since 1.0.0
 */
public final class BoxesSpinner extends CommonBaseIntegerSpinner<BoxesRange> {
    static final int MAX_BOXES = 100_000;
    private static final int STEP_BOXES = 1;
    @Serial
    private static final long serialVersionUID = 1199412248082207425L;

    /**
     * Create the default BoxesSpinner.
     *
     * @since 1.0.0
     */
    public BoxesSpinner() {
        this(new BoxesRange(new NumberOfBoxes(MAX_BOXES)), STEP_BOXES);
    }

    /**
     * Create a BoxesSpinner with the specified interval, and the step.
     *
     * @param interval BoxesRange. The interval of available values.
     * @param step Int.
     */
    private BoxesSpinner(final BoxesRange interval, final int step) {
        super(interval, step);
        super.setEnabled(true);
        super.setVisible(true);
        super.setOpaque(true);
        super.setFocusable(true);
        super.setName("boxesNb");
    }
}
