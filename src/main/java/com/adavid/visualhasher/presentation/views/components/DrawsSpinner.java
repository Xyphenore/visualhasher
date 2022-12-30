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

import com.adavid.visualhasher.domain.utility.DrawsRange;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public final class DrawsSpinner extends JSpinner {
    public static final Integer MIN_DRAWS = BoxesSpinner.MAX_BOXES / 2;
    public static final Integer MAX_DRAWS = BoxesSpinner.MAX_BOXES;
    public static final Integer STEP_DRAWS = 1;
    private int step;
    private Integer min;
    private Integer max;

    public DrawsSpinner() {
        this(MIN_DRAWS, MAX_DRAWS, STEP_DRAWS);
    }

    public DrawsSpinner(final Integer minValue, final Integer maxValue, final Integer step) {
        super(new SpinnerNumberModel(minValue, minValue, maxValue, step));
        this.min = minValue;
        this.max = maxValue;
        this.step = step;
    }

    public void setInterval(final DrawsRange interval) {
        final var minValue = interval.min();
        final var maxValue = interval.max();

        if (minValue != this.min || maxValue != this.max) {
            super.setModel(new SpinnerNumberModel(minValue, minValue, maxValue, this.step));
            this.min = minValue;
            this.max = maxValue;
            this.setValue(maxValue);
        }
    }

    public int getMinimum() {
        return this.min;
    }

    public int getMaximum() {
        return this.max;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(final Integer step) {
        if (step != this.step) {
            super.setModel(new SpinnerNumberModel(this.min, this.min, this.max, step));
            this.step = step;
        }
    }
}
