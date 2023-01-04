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

package com.adavid.visualhasher.domain;

/**
 * Implementation of the box interface for computing.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @since 1.0.0
 */
public final class ComputedBox implements Box {
    private final boolean colored;
    private final int id;
    private boolean original;
    private int balls;

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
    ComputedBox(final int id, final int balls, final boolean colored, final boolean originalDraw) {
        super();
        this.id = id;
        this.balls = balls;
        this.colored = colored;
        this.original = originalDraw;
    }

    /**
     * Indicate if the box is colored, for Addressing Hash Function.
     *
     * @return boolean
     *
     * @see LinearOpenAddressingHashFunctionWorker
     * @see QuadraticOpenAddressingHashFunctionWorker
     * @since 1.0.0
     */
    public boolean isColored() {
        return this.colored;
    }

    /**
     * Indicate if the ball stored in the first drawed box.
     *
     * @return boolean
     *
     * @see LinearOpenAddressingHashFunctionWorker
     * @see QuadraticOpenAddressingHashFunctionWorker
     * @since 1.0.0
     */
    public boolean isInTheFirstBox() {
        return this.original;
    }

    public void setFirstBox(final boolean original) {
        this.original = original;
    }

    @Override
    public int getBalls() {
        return this.balls;
    }

    @Override
    public void setBalls(final int balls) {
        this.balls = balls;
    }

    @Override
    public void clear() {
        this.balls = 0;
    }

    @Override
    public void incrementsBalls() {
        this.balls++;
    }

    @Override
    public void decrementBalls() {
        this.balls--;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ComputedBox{" + "colored=" + this.colored + ", id=" + this.id + ", original=" + this.original + ", balls=" + this.balls + '}';
    }
}
