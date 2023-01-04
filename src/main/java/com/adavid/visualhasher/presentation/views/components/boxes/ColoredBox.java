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

package com.adavid.visualhasher.presentation.views.components.boxes;

import com.adavid.visualhasher.domain.Box;

/**
 * The interface for OpenAddressingBox for Open Addressing Hash Functions.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker
 * @see com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public interface ColoredBox extends Box {
    /**
     * Add a ball to the box with the given color.
     *
     * @param color Color. The color of the added ball.
     *
     * @since 1.0.0
     */
    void incrementsBalls(final Color color);

    /**
     * Get the color of the ball in the box.
     *
     * @return Returns Color.
     *
     * @since 1.0.0
     */
    Color getColor();

    /**
     * The color of a ball.
     *
     * @author Axel DAVID
     * @version 1.0.0
     * @since 1.0.0
     */
    enum Color {
        /**
         * The first box draw was full, so the ball was added to another box, with red color.
         */
        RED,
        /**
         * The first box draw was empty, so the ball was added in with green color.
         */
        GREEN
    }
}
