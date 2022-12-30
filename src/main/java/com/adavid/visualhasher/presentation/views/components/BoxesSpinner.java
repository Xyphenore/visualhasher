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

package com.adavid.visualhasher.presentation.views.components;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public final class BoxesSpinner extends JSpinner {
    public static final Integer MIN_BOXES = 2;
    public static final Integer MAX_BOXES = 100_000;
    public static final Integer STEP_BOXES = 1;

    public BoxesSpinner() {
        this(MIN_BOXES, MAX_BOXES, STEP_BOXES);
    }

    public BoxesSpinner(final Integer minValue, final Integer maxValue, final Integer step) {
        super(new SpinnerNumberModel(minValue, minValue, maxValue, step));
    }


    @Override
    public Integer getValue() {
        final var model = (SpinnerNumberModel) this.getModel();
        return model.getNumber().intValue();
    }
}
