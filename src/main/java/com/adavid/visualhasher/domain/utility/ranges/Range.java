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

package com.adavid.visualhasher.domain.utility.ranges;

/**
 * An interval T type.
 *
 * @param <T> Type. The type of value contained in the interval.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see IntegerRange
 * @since 1.0.0
 */
public interface Range<T> {
    /**
     * Check if the value is contained in the interval.
     *
     * @param value T. The value, which be checked if it is in the interval.
     *
     * @return Returns boolean.
     *
     * @since 1.0.0
     */
    boolean contains(final T value);

    /**
     * Get the minimum of the interval.
     *
     * @return Return T object, which is the minimum.
     *
     * @since 1.0.0
     */
    T min();

    /**
     * Get the maximum of the interval.
     *
     * @return Return T object, which is the maximum.
     *
     * @since 1.0.0
     */
    T max();
}
