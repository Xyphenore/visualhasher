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
 * A computation box for AddressingHashFunction.
 *
 * @author Axel DAVID.
 * @version 1.0.0
 * @since 2.0.0
 */
public final class ColoredUniqueBallBox extends AbstractBox implements ColoredBox, UniqueBallBox {
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private Color color;

    private ColoredUniqueBallBox() {
        // Need to send an explicite error
        super(0, AbstractBox.DEFAULT_BALLS);
        throw new UnsupportedOperationException(
                "Cannot create a ColoredUniqueBallBox without its ID. Please call a public constructor and specify the ID of box.");
    }

    /**
     * Create a ColoredUniqueBallBox with the given ID.
     *
     * @param id Int.
     * The ID must be greater or equal to 0.
     *
     * @throws IllegalBoxIDException Thrown if the given ID is less than 0.
     * @throws IllegalNumberOfBallsException Thrown if the given number of balls is different from 0 and 1.
     * @since 1.0.0
     */
    public ColoredUniqueBallBox(final int id) {
        this(id, AbstractBox.DEFAULT_BALLS, ColoredUniqueBallBox.DEFAULT_COLOR);
    }

    /**
     * Create a ColoredUniqueBallBox with the given ID, number of balls and the given color.
     *
     * @param id Int.
     * The ID of the box.
     * @param balls Int.
     * The number of balls in the box.
     * @param color Color.
     * The color of the box.
     *
     * @throws IllegalBoxIDException Thrown if the given ID is less than 0.
     * @throws IllegalNumberOfBallsException Thrown if the given number of balls is different from 0 and 1.
     * @since 1.0.0
     */
    public ColoredUniqueBallBox(final int id, final int balls, final Color color) {
        super(id, balls);

        if (1 < balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot create a ColoredUniqueBallBox with an invalid number of balls. The number of balls is greater than 1. Please give a number between 0 and 1.");
        }

        this.color = color;
    }

    /**
     * Create a ColoredUniqueBallBox with the given ID and the given number of balls.
     *
     * @param id Int.
     * The ID of the box.
     * @param balls Int.
     * The number of balls in the box.
     *
     * @throws IllegalBoxIDException Thrown if the given ID is less than 0.
     * @throws IllegalNumberOfBallsException Thrown if the given number of balls is different from 0 and 1.
     * @since 1.0.0
     */
    public ColoredUniqueBallBox(final int id, final int balls) {
        this(id, balls, ColoredUniqueBallBox.DEFAULT_COLOR);
    }

    /**
     * Set the number of balls of the box.
     *
     * @param balls Int.
     * The number of balls must be 0 or 1.
     *
     * @throws IllegalNumberOfBallsException Thrown if the number of balls is different from 0 and 1.
     * @since 1.0.0
     */
    @Override
    public void setBalls(final int balls) {
        if (1 < balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot set the number of balls for a ColoredUniqueBallBox with an invalid number of balls. The number of balls is greater than 1. Please give a number between 0 and 1.");
        }

        super.setBallsBase(balls);
    }

    @Override
    public void setMaximumBalls(final int balls) {
        if (1 < balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot set the number of maxBalls for a ColoredUniqueBallBox with an invalid number of balls. The number of balls is greater than 1. Please give a number between 0 and 1.");
        }

        super.setMaximumBallsBase(balls);
    }

    /**
     * Increments the number of balls by 1.
     *
     * @throws IllegalNumberOfBallsException Thrown if the number of balls is already 1.
     * @since 1.0.0
     */
    @Override
    public void incrementsBalls() {
        if (1 >= this.getBalls()) {
            throw new IllegalNumberOfBallsException(
                    "Cannot increment the number of balls for a ColoredUniqueBallBox. The number of balls if equal or greater than 1. Value: " + this.getBalls() + " Please check the logic of this call.");
        }

        super.incrementsBallsBase();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public boolean containsBalls() {
        return 0 != this.getBalls();
    }
}
