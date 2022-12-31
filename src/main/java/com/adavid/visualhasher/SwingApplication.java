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

package com.adavid.visualhasher;

import com.adavid.visualhasher.presentation.views.SwingView;
import com.adavid.visualhasher.presentation.views.View;

/**
 * Default implementation of Application. Use Swing for the user presentation.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see Application
 * @since 1.0.0
 */
public final class SwingApplication implements Application {
    private final View view;

    /**
     * Create a SwingApplication with the default title.
     *
     * @since 1.0.0
     */
    public SwingApplication() {
        super();
        this.view = new SwingView();
    }

    /**
     * Create a SwingApplication with the given title.
     *
     * @param title String. The title of the application, it might be null.
     *
     * @since 1.0.0
     */
    public SwingApplication(final String title) {
        super();
        this.view = new SwingView(title);
    }

    @Override
    public void execute() {
        this.view.execute();
    }
}
