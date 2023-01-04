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

package com.adavid.visualhasher.presentation.views.components.selectors;

import com.adavid.visualhasher.domain.utility.BoxesRange;
import com.adavid.visualhasher.presentation.views.components.spinners.BoxesSpinner;

import java.io.Serial;

/**
 * The selector for the number of box for the SwingView.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.presentation.views.SwingView
 * @since 1.0.0
 */
public final class BoxesSelector extends CommonBaseSelector<BoxesRange> {
    @Serial
    private static final long serialVersionUID = -5568095296570387632L;

    /**
     * Create the default BoxesSelector.
     *
     * @since 1.0.0
     */
    public BoxesSelector() {
        super(new BoxesSpinner(), "Number of boxes");
    }
}
