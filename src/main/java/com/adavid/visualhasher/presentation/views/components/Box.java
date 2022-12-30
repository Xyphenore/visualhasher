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

package com.adavid.visualhasher.presentation.views.components;

import javax.swing.JComponent;
import java.io.Serial;

public final class Box extends JComponent {
    @Serial
    private static final long serialVersionUID = 5342606245543315329L;
    private int balls;

    public Box() {
        this(0);
    }

    public Box(final int balls) {
        this.balls = balls;
    }

    public void incrementBalls() {
        this.balls = Math.addExact(this.balls, 1);
    }

    public int getBalls() {
        return this.balls;
    }

    public void setBalls(final int balls) {
        if (0 > balls) {
            throw new IllegalArgumentException("The number of balls must be over or equal to zero.");
        }

        this.balls = balls;
    }

    public void decrementBalls() {
        if (this.balls <= 0) {
            throw new RuntimeException(
                    "Cannot decremente the number of balls. The number of balls must be over or equal to zero.");
        }
        --this.balls;
    }
}
