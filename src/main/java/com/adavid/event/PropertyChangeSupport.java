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

package com.adavid.event;

import java.beans.PropertyChangeListener;

/**
 * Interface provided methods to add and remove a {@link PropertyChangeListener} to a component.
 *
 * @author Java (Methods)
 * @version 1.0.0
 * @see PropertyChangeListener
 * @since 1.0.0
 */
public interface PropertyChangeSupport {
    /**
     * Adds a {@link PropertyChangeListener} to the listener list.
     * The listener is registered for all properties.
     *
     * @param listener {@link PropertyChangeListener} to be added.
     * If {@code listener} is {@code null}, no action is taken.
     *
     * @apiNote The same listener object may be added more than once, and will be called as many times as it is added.
     * @since 1.0.0
     */
    void addPropertyChangeListener(final PropertyChangeListener listener);

    /**
     * Removes a {@link PropertyChangeListener} from the listener list.
     * This removes a {@link PropertyChangeListener} that was registered for all properties.
     *
     * @param listener {@link PropertyChangeListener} to be removed.
     * If {@code listener} is {@code null}, or was never added, no action is taken.
     *
     * @apiNote If {@code listener} was added more than once to the same event source, it will be notified one less
     * time after being removed.
     * @since 1.0.0
     */
    void removePropertyChangeListener(final PropertyChangeListener listener);
}
