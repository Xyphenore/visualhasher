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
 * Add a getter/setter for the color of the {@link Box}.<br>
 * - {@link #getColor()}<br>
 * - {@link #setColor(Color)}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see Box
 * @since 2.0.0
 */
public interface ColoredBox extends Box {
    /**
     * Get the color of the box.
     *
     * @return Returns the color of the box.
     *
     * @since 1.0.0
     */
    Color getColor();

    /**
     * Set the color of the box.
     *
     * @param color {@link Color} The new color of the box.
     *
     * @since 1.0.0
     */
    void setColor(Color color);
}
