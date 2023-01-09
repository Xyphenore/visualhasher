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

package com.adavid.visualhasher.presentation.components.selectors;

import com.adavid.ranges.CommonIntegerRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;
import com.adavid.visualhasher.presentation.components.spinners.CommonBaseIntegerSpinner;
import com.adavid.visualhasher.presentation.components.spinners.IntegerSpinner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import java.io.Serial;
import java.util.Objects;

class CommonBaseSelector<T extends CommonIntegerRange> extends JPanel implements IntegerSpinner<T>, Selector<Integer> {
    private static final int DEFAULT_COLUMN_SIZE = 7;
    @Serial
    private static final long serialVersionUID = -7001795826469549037L;
    private final CommonBaseIntegerSpinner<T> spinner;

    private CommonBaseSelector() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a CommonBaseSelector without the spinner. Please call the protected constructor with a JSpinner object.");
    }

    /**
     * @param spinner CommonBaseIntegerSpinner{@literal <}T{@literal >} The spinner.
     * @param label String The label text.
     *
     * @since 1.0.0
     */
    CommonBaseSelector(final CommonBaseIntegerSpinner<T> spinner, final String label) {
        super();
        this.spinner = spinner;

        final var labelObject = new JLabel(Objects.requireNonNull(label,
                                                                  "Cannot create the CommonBaseSelector with a null label. Please give not null label."
                                                                 ));
        labelObject.setLabelFor(this.spinner);

        final var editor = this.spinner.getEditor();
        ((JSpinner.DefaultEditor) editor).getTextField().setColumns(CommonBaseSelector.DEFAULT_COLUMN_SIZE);

        super.add(labelObject);
        super.add(this.spinner);
    }

    /**
     * Get the number of box.
     *
     * @return Returns a NumberOfBoxes.
     *
     * @see NumberOfBoxes
     * @since 1.0.0
     */
    public final Integer getValue() {
        return this.spinner.getValue();
    }

    public final void setValue(final Integer value) {
        this.spinner.setValue(value);
    }

    @Override
    public final T getInterval() {
        return this.spinner.getInterval();
    }

    @Override
    public final void setInterval(final T interval) {
        this.spinner.setInterval(interval);
    }

    @Override
    public final int getStep() {
        return this.spinner.getStep();
    }

    @Override
    public final void setStep(final int step) {
        this.spinner.setStep(step);
    }

    @Override
    public final void addChangeListener(final ChangeListener listener) {
        this.spinner.addChangeListener(listener);
    }

    @Override
    public final void removeChangeListener(final ChangeListener listener) {
        this.spinner.removeChangeListener(listener);
    }
}
