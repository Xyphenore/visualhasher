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

package com.adavid.visualhasher.presentation.views.components.action.bar;

import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;
import com.adavid.visualhasher.presentation.views.components.selectors.BoxesSelector;
import com.adavid.visualhasher.presentation.views.components.selectors.DrawsSelector;
import com.adavid.visualhasher.presentation.views.components.selectors.HashFunctionSelector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import java.awt.FlowLayout;
import java.io.Serial;

/**
 * The button bar to execute and select the hash function.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SwingActionBar extends JPanel implements ActionBar {
    @Serial
    private static final long serialVersionUID = -6893180532781527538L;
    private final BoxesSelector boxes = new BoxesSelector();
    private final DrawsSelector draws = new DrawsSelector();
    private final HashFunctionSelector hashFunction = new HashFunctionSelector();
    private final JButton run = new RunButton();
    private final JButton rerun = new ReRunButton();
    private final JButton cancel = new CancelButton();

    /**
     * Create the action bar.
     *
     * @since 1.0.0
     */
    public SwingActionBar() {
        super(new FlowLayout());
        super.add(this.boxes);
        super.add(this.draws);
        super.add(this.hashFunction);
        super.add(this.run);
        super.add(this.rerun);
        super.add(this.cancel);

        this.boxes.addChangeListener((final ChangeEvent ignored) -> {
            final var boxesNbValue = this.boxes.getValue();
            this.draws.setInterval(new DrawsRange(new NumberOfBoxes(boxesNbValue)));
            this.draws.setValue(boxesNbValue);
        });
    }

    @Override
    public BoxesSelector getBoxesSelector() {
        return this.boxes;
    }

    @Override
    public DrawsSelector getDrawsSelector() {
        return this.draws;
    }

    @Override
    public HashFunctionSelector getHashFunctionsSelector() {
        return this.hashFunction;
    }

    @Override
    public JButton getRunButton() {
        return this.run;
    }

    @Override
    public JButton getReRunButton() {
        return this.rerun;
    }

    @Override
    public JButton getCancelButton() {
        return this.cancel;
    }

    @Override
    public String getSelectedHashFunction() {
        return this.hashFunction.getValue();
    }

    @Override
    public NumberOfBoxes getNumberOfBoxes() {
        return new NumberOfBoxes(this.boxes.getValue());
    }

    @Override
    public int getNumberOfDraws() {
        return this.draws.getValue();
    }
}
