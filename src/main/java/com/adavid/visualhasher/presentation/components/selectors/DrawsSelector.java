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

import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.presentation.SwingView;
import com.adavid.visualhasher.presentation.components.spinners.DrawsSpinner;

import java.io.Serial;

/**
 * The selector for draws for SwingView.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see SwingView
 * @since 1.0.0
 */
public final class DrawsSelector extends CommonBaseSelector<DrawsRange> {
    @Serial
    private static final long serialVersionUID = -1660644508272171907L;

    /**
     * Create the default BoxesSelector.
     *
     * @since 1.0.0
     */
    public DrawsSelector() {
        super(new DrawsSpinner(), "Number of draws");
    }
}
