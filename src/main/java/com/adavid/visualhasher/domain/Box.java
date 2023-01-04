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

package com.adavid.visualhasher.domain;

/**
 * The interface of a Box.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Box {
    /**
     * Get the numbers of balls in the box.
     *
     * @return Returns an integer.
     *
     * @since 1.0.0
     */
    int getBalls();

    /**
     * Change the numbers of balls in the box.
     *
     * @param balls Int.
     *
     * @since 1.0.0
     */
    void setBalls(final int balls);

    /**
     * Reset the number of balls to 0.
     *
     * @since 1.0.0
     */
    void clear();

    /**
     * Increments the number of balls by 1.
     *
     * @since 1.0.0
     */
    void incrementsBalls();

    /**
     * Decrements the number of balls by 1.
     *
     * @since 1.0.0
     */
    void decrementBalls();

    /**
     * Get the ID of the box.
     *
     * @return Integer
     *
     * @since 1.0.0
     */
    int getID();
}
