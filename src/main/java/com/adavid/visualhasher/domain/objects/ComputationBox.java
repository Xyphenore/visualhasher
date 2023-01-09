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
 * Box used for computing in HashFunctionWorkers.
 *
 * @author Axel DAVID
 * @version 2.0.0
 * @see com.adavid.visualhasher.domain.logics.ChainingHashFunctionWorker
 * @see com.adavid.visualhasher.domain.logics.DoubleChoiceHashFunctionWorker
 * @since 1.0.0
 */
public final class ComputationBox extends AbstractBox {
    /**
     * Create a ComputationBox with the given ID and 0 balls.
     *
     * @param id Int.
     *
     * @since 2.0.0
     */
    public ComputationBox(final int id) {
        this(id, AbstractBox.DEFAULT_BALLS);
    }

    /**
     * Create a box with specified settings.
     *
     * @param id int
     * @param balls Integer
     * @param colored boolean
     * @param originalDraw boolean
     *
     * @since 1.0.0
     */
    public ComputationBox(final int id, final int balls) {
        super(id, balls);
    }

    private ComputationBox() {
        // Need to send an explicit error
        super(0, AbstractBox.DEFAULT_BALLS);

        throw new UnsupportedOperationException(
                "Cannot create a ComputationBox without the ID of the box. Please call a public constructor with the ID of the box.");
    }

    @Override
    public void incrementsBalls() {
        super.incrementsBallsBase();
    }

    @Override
    public void setBalls(final int balls) {
        super.setBallsBase(balls);
    }

    @Override
    public void setMaximumBalls(final int balls) {
        super.setMaximumBallsBase(balls);
    }

    @Override
    public String toString() {
        return "ComputationBox{id: " + this.getID() + ", balls: " + this.getBalls() + ", maxBalls: " + this.getMaximumBalls() + "}";
    }
}
