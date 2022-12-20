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

package com.adavid.visualhasher.view.component;

import com.adavid.visualhasher.view.Constants;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public final class BoxesSpinner extends JSpinner {
    public BoxesSpinner() {
        this(Constants.MIN_BOXES, Constants.MAX_BOXES, Constants.STEP_BOXES);
    }

    public BoxesSpinner(final int minValue, final int maxValue, final int step) {
        super(new SpinnerNumberModel(minValue, minValue, maxValue, step));
    }
}
