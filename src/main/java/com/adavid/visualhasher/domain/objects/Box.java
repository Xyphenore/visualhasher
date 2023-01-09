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
 * Box provided a getter of its ID {@link #getID()}, and getter/setter on the number of balls {@link #getBalls()} and
 * {@link #setBalls(int)}.
 *
 * @author Axel DAVID
 * @version 2.0.0
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
     * The number of balls must be greater or equal to 0.
     *
     * @throws IllegalNumberOfBallsException Thrown if the number of balls is less than 0.
     * @since 1.0.0
     */
    void setBalls(final int balls);

    /**
     * Get the ID of the box.
     *
     * @return Returns the ID like an integer.
     *
     * @since 1.0.0
     */
    int getID();

    /**
     * Get the maximum of the number of balls.
     *
     * @return Returns the maximum of balls that the box can contain.
     *
     * @since 2.0.0
     */
    int getMaximumBalls();

    /**
     * Change the maximum number of balls that the box can contain.
     *
     * @param balls Int.
     * The newer maximum.
     *
     * @since 2.0.0
     */
    void setMaximumBalls(final int balls);
}
