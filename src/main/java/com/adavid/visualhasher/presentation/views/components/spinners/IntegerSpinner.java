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

import com.adavid.visualhasher.domain.utility.ranges.CommonIntegerRange;
import com.adavid.visualhasher.domain.utility.ranges.Range;
import com.adavid.visualhasher.presentation.views.components.ChangeEventListener;

/**
 * The interface of spinners.
 *
 * @param <T> The type of range, extends Range{@literal <}Integer{@literal >}
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see BoxesSpinner
 * @see DrawsSpinner
 * @since 1.0.0
 */
public interface IntegerSpinner<T extends Range<Integer>> extends ChangeEventListener {
    /**
     * Get the value of the spinner.
     *
     * @return Return an Integer.
     *
     * @since 1.0.0
     */
    Integer getValue();

    /**
     * Set the value of the spinner.
     *
     * @param value Integer
     *
     * @since 1.0.0
     */
    void setValue(final Integer value);

    /**
     * Get the interval of the spinner.
     *
     * @return Returns T.
     *
     * @since 1.0.0
     */
    T getInterval();

    /**
     * Change the interval of the spinner.
     *
     * @param interval T. The newer interval.
     *
     * @see CommonIntegerRange
     * @since 1.0.0
     */
    void setInterval(final T interval);

    /**
     * Get the step of the spinner.
     *
     * @return Returns an integer.
     *
     * @since 1.0.0
     */
    int getStep();

    /**
     * Change the step of the spinner.
     *
     * @param step Int. The newer step.
     *
     * @since 1.0.0
     */
    void setStep(final int step);
}
