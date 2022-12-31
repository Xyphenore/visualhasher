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

package com.adavid.visualhasher.presentation.views.components.boxes;

import java.io.Serial;

/**
 * A Box, which can contains multiple balls.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see Box
 * @since 1.0.0
 */
public final class NumberBox extends CommonBaseBox {
    @Serial
    private static final long serialVersionUID = 5342606245543315329L;

    private NumberBox() {
        super(0, 0);
        throw new UnsupportedOperationException(
                "Cannot create a NumberBox without an ID. Please call a public constructor and give it an ID [and a number of balls].");
    }

    /**
     * Create a box with the given ID and 0 balls.
     *
     * @param id Int. The ID of the box.
     *
     * @since 1.0.0
     */
    public NumberBox(final int id) {
        this(id, 0);
    }

    /**
     * Create a box with the ID and the number of balls.
     *
     * @param id Int. The ID of box.
     * @param balls Int. The number must be greater or equal to 0.
     *
     * @since 1.0.0
     */
    public NumberBox(final int id, final int balls) {
        super(id, balls);
    }

    @Override
    public String toString() {
        return "NumberBox{" + ", balls=" + this.getBalls() + "}";
    }
}
