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
 * The common base for implementations of Box.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ComputationBox
 * @see ColoredUniqueBallBox
 * @since 2.0.0
 */
public abstract class AbstractBox implements ResettableBox, IncrementableBox {
    static final int DEFAULT_BALLS = 0;
    private final int id;
    private int balls;

    private int maxBalls;

    AbstractBox(final int id, final int balls) {
        super();

        if (0 > id) {
            throw new IllegalBoxIDException(
                    "Cannot create an AbstractBox with an invalid ID. The ID is less than 0. Please give an ID greater or equal to 0.");
        }

        if (0 > balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot create an AbstractBox with an invalid number of balls. The number of balls is less than 0. Please give a number between 0 and 1.");
        }

        this.id = id;
        this.balls = balls;
    }

    private AbstractBox() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create an AbstractBox without the ID and the number of balls. Please call a public constructor and specify the ID and number of balls.");
    }

    @Override
    public final int getBalls() {
        return this.balls;
    }

    @Override
    public final int getID() {
        return this.id;
    }

    @Override
    public final int getMaximumBalls() {
        return this.maxBalls;
    }

    /**
     * The common base of the setter of balls
     *
     * @param balls Int.
     *
     * @since 1.0.0
     */
    final void setBallsBase(final int balls) {
        if (0 > balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot set the number of balls for an AbstractBox with an invalid number of balls. The number of balls is less than 0. Please give a number greater or equal to 0.");
        }
        this.balls = balls;
    }

    /**
     * The common base of the incrementsBalls.
     *
     * @since 1.0.0
     */
    final void incrementsBallsBase() {
        this.balls = Math.incrementExact(this.balls);
    }

    @Override
    public final void decrementBalls() {
        if (0 < this.balls) {
            --this.balls;
        }
    }

    @Override
    public final void resetBalls() {
        this.balls = 0;
    }

    /**
     * The common base of the setter of maxBalls.
     *
     * @param maxBalls Int.
     *
     * @since 1.0.0
     */
    protected void setMaximumBallsBase(final int maxBalls) {
        if (0 > maxBalls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot set the number of maxBalls for an AbstractBox with an invalid number of balls. The number of balls is less than 0. Please give a number greater or equal to 0.");
        }
        this.maxBalls = maxBalls;
    }
}
