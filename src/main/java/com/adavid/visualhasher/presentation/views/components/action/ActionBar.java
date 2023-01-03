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

package com.adavid.visualhasher.presentation.views.components.action;

import com.adavid.visualhasher.presentation.views.components.hashfunctionsselector.HashFunctionSelector;
import com.adavid.visualhasher.presentation.views.components.selectors.BoxesSelector;
import com.adavid.visualhasher.presentation.views.components.selectors.DrawsSelector;

import javax.swing.JPanel;
import java.awt.*;

/**
 * The button bar to execute and select the hash function.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ActionBar extends JPanel {
    private BoxesSelector boxes;
    private DrawsSelector draws;
    private HashFunctionSelector hashFunction;
    private RunButton run;
    private ReRunButton rerun;
    private CancelButton cancel;

    /**
     * Create the default action bar.
     *
     * @since 1.0.0
     */
    public ActionBar() {
        this(null, null, null, null, null, null);
    }

    /**
     * Create the action bar.
     *
     * @param boxes BoxesSelector
     * @param draws DrawsSelector
     * @param functions HashFunctionsSelector
     * @param run RunButton
     * @param rerun ReRunButton
     * @param cancel CancelButton
     *
     * @since 1.0.0
     */
    public ActionBar(
            final BoxesSelector boxes, final DrawsSelector draws, final HashFunctionSelector functions, final RunButton run, final ReRunButton rerun, final CancelButton cancel
                    ) {
        super(new FlowLayout());
        this.boxes = boxes;
        this.draws = draws;
        this.hashFunction = functions;
        this.run = run;
        this.rerun = rerun;
        this.cancel = cancel;

        super.add(this.boxes);
        super.add(this.draws);
        super.add(this.hashFunction);
        super.add(this.run);
        super.add(this.rerun);
        super.add(this.cancel);
    }

    public BoxesSelector getBoxesSelector() {
        return this.boxes;
    }

    public void setBoxesSelector(final BoxesSelector boxes) {
        this.boxes = boxes;
    }

    public DrawsSelector getDrawsSelector() {
        return this.draws;
    }

    public void setDrawsSelector(final DrawsSelector draws) {
        this.draws = draws;
    }

    public HashFunctionSelector getHashFunctionsSelector() {
        return this.hashFunction;
    }

    public void setHashFunctionsSelector(final HashFunctionSelector hashFunction) {
        this.hashFunction = hashFunction;
    }

    public RunButton getRunButton() {
        return this.run;
    }

    public void setRunButton(final RunButton run) {
        this.run = run;
    }

    public ReRunButton getReRunButton() {
        return this.rerun;
    }

    public void setReRunButton(final ReRunButton rerun) {
        this.rerun = rerun;
    }

    public CancelButton getCancelButton() {
        return this.cancel;
    }

    public void setCancelButton(final CancelButton cancel) {
        this.cancel = cancel;
    }
}
