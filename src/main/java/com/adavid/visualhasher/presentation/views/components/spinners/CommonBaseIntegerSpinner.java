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

package com.adavid.visualhasher.presentation.views.components.spinners;

import com.adavid.visualhasher.domain.utility.ranges.CommonIntegerRange;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.io.Serial;
import java.util.Objects;

/**
 * the common base of Spinners.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see DrawsSpinner
 * @see BoxesSpinner
 * @since 1.0.0
 */
public class CommonBaseIntegerSpinner extends JSpinner implements IntegerSpinner {
    @Serial
    private static final long serialVersionUID = 5331067496725947493L;
    private int step;
    private CommonIntegerRange interval;

    private CommonBaseIntegerSpinner() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a CommonBaseIntegerSpinner without arguments. Please call the protected constructor with the interval and the step.");
    }

    CommonBaseIntegerSpinner(final CommonIntegerRange interval, final int step) {
        super(new SpinnerNumberModel(interval.min(), interval.min(), interval.max(), Integer.valueOf(step)));
        this.interval = interval;
        this.step = step;
    }

    /**
     * Get the value of the spinner.
     *
     * @return Return an Integer.
     *
     * @since 1.0.0
     */
    @Override
    public final Integer getValue() {
        final var model = (SpinnerNumberModel) this.getModel();
        return model.getNumber().intValue();
    }

    /**
     * Get the interval of the spinner.
     *
     * @return Returns CommonIntegerRange.
     *
     * @since 1.0.0
     */
    public CommonIntegerRange getInterval() {
        return this.interval;
    }

    /**
     * Change the interval of the spinner.
     *
     * @param interval CommonIntegerRange. The newer interval.
     *
     * @see CommonIntegerRange
     * @since 1.0.0
     */
    public final void setInterval(final CommonIntegerRange interval) {
        if (!Objects.equals(this.interval, interval)) {
            final var minValue = interval.min();
            final var maxValue = interval.max();

            super.setModel(new SpinnerNumberModel(minValue, minValue, maxValue, Integer.valueOf(this.step)));

            this.interval = interval;
            this.setValue(maxValue);
        }
    }

    /**
     * Get the step of the spinner.
     *
     * @return Returns an integer.
     *
     * @since 1.0.0
     */
    public final int getStep() {
        return this.step;
    }

    /**
     * Change the step of the spinner.
     *
     * @param step Integer. The newer step.
     *
     * @since 1.0.0
     */
    public final void setStep(final int step) {
        if (step != this.step) {
            super.setModel(new SpinnerNumberModel(this.interval.min(),
                                                  this.interval.min(),
                                                  this.interval.max(),
                                                  Integer.valueOf(step)
            ));
            this.step = step;
        }
    }
}
