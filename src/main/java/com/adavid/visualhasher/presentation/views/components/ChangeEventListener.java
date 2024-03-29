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

package com.adavid.visualhasher.presentation.views.components;

import javax.swing.event.ChangeListener;

/**
 * The interface for component implemented listeners on change.
 *
 * @author Java (Methods)
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ChangeEventListener {
    /**
     * Add a change listener.
     *
     * @param listener ChangeListener
     *
     * @see ChangeListener
     * @since 1.0.0
     */
    void addChangeListener(final ChangeListener listener);

    /**
     * Remove the change listener.
     *
     * @param listener ChangeListener. The listener to remove.
     *
     * @see ChangeListener
     * @since 1.0.0
     */
    void removeChangeListener(final ChangeListener listener);
}
