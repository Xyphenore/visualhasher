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

package com.adavid.visualhasher.presentation.components.action.bar;

import com.adavid.visualhasher.domain.utility.NumberOfBoxes;
import com.adavid.visualhasher.presentation.components.selectors.BoxesSelector;
import com.adavid.visualhasher.presentation.components.selectors.DrawsSelector;
import com.adavid.visualhasher.presentation.components.selectors.HashFunctionSelector;

import javax.swing.JButton;

/**
 * ActionBar provided getters to elements of the bar.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ActionBar {
    /**
     * Get the box selector.
     *
     * @return BoxesSelector
     *
     * @see BoxesSelector
     * @since 1.0.0
     */
    BoxesSelector getBoxesSelector();

    /**
     * Get the draw selector.
     *
     * @return DrawsSelector
     *
     * @see DrawsSelector
     * @since 1.0.0
     */
    DrawsSelector getDrawsSelector();

    /**
     * Get the hash function selector.
     *
     * @return HashFunctionSelector
     *
     * @see HashFunctionSelector
     * @since 1.0.0
     */
    HashFunctionSelector getHashFunctionsSelector();

    /**
     * Get the run button.
     *
     * @return JButton
     *
     * @see RunButton
     * @since 1.0.0
     */
    JButton getRunButton();

    /**
     * Get the re-run button.
     *
     * @return JButton
     *
     * @see ReRunButton
     * @since 1.0.0
     */
    JButton getReRunButton();

    /**
     * Get the cancel button.
     *
     * @return JButton
     *
     * @see CancelButton
     * @since 1.0.0
     */
    JButton getCancelButton();

    /**
     * Get the name of the selected hash function.
     *
     * @return Return the name like a String.
     *
     * @see HashFunctionSelector
     * @since 1.0.0
     */
    String getSelectedHashFunction();

    /**
     * Get the number of box selected by the user.
     *
     * @return Returns an NumberOfBoxes.
     *
     * @see BoxesSelector
     * @since 1.0.0
     */
    NumberOfBoxes getNumberOfBoxes();

    /**
     * Get the number of draws selected by the user.
     *
     * @return Returns an integer.
     *
     * @see DrawsSelector
     * @since 1.0.0
     */
    int getNumberOfDraws();
}
