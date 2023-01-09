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

package com.adavid.visualhasher.domain.objects;

/**
 * Add the method {@link #resetBalls} to the Box.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see Box
 * @since 2.0.0
 */
public interface ResettableBox extends Box {
    /**
     * Reset the number of balls to 0.
     *
     * @since 1.0.0
     */
    void resetBalls();
}
