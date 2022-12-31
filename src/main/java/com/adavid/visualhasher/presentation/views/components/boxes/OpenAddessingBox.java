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

package com.adavid.visualhasher.presentation.views.components.boxes;

import java.io.Serial;

/**
 * A box, which can contains 0 or 1 balls, the ball can be colored in GREEN or RED.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker
 * @see com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker
 * @see ColoredBox
 * @since 1.0.0
 */
public final class OpenAddessingBox extends CommonBaseBox implements ColoredBox {
    private static final Color DEFAULT_COLOR = Color.GREEN;
    @Serial
    private static final long serialVersionUID = 8065556761789566795L;

    /**
     * Create an OpenAddressingBox with the ID and the boolean filled.
     *
     * @param id Int.
     * @param filled Boolean. True, the box contains a ball, else no.
     *
     * @since 1.0.0
     */
    public OpenAddessingBox(final int id, final boolean filled) {
        this(id, filled, OpenAddessingBox.DEFAULT_COLOR);
    }

    /**
     * Create an OpenAddressingBox with the ID and the boolean filled.
     *
     * @param id Int.
     * @param filled Boolean. True, the box contains a ball, else no.
     * @param color Color. The color of the ball.
     *
     * @since 1.0.0
     */
    public OpenAddessingBox(final int id, final boolean filled, final Color color) {
        super(id, filled, color);
    }

    @Override
    public void incrementsBalls() {
        this.incrementsBalls(OpenAddessingBox.DEFAULT_COLOR);
    }

    @Override
    public void incrementsBalls(final Color color) {
        super.incrementsBalls();
        this.setColor(color);
    }

    public Color getColor() {
        return super.getColor();
    }
}
