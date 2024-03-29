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

/**
 * The VisualHasher application.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see SwingApplication
 * @since 1.0.0
 */
@FunctionalInterface
public interface Application {
    /**
     * Main function. Create and execute the default application SwingApplication.
     *
     * @param args String[]. Launch arguments.
     *
     * @since 1.0.0
     */
    static void main(final String[] args) {
        Application.getDefaultApplication().execute();
    }

    /**
     * Run the application.
     *
     * @since 1.0.0
     */
    void execute();

    /**
     * Return the default implementation of Application.
     *
     * @return Returns Application.
     *
     * @see SwingApplication
     * @since 1.0.0
     */
    static Application getDefaultApplication() {
        return new SwingApplication();
    }
}
