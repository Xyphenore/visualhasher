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

import com.adavid.visualhasher.domain.exceptions.CannotComputeIndexException;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The quadratic open addressing hash function.
 * Draws a box index between 1, and the number_of_boxes, if the box is empty, increments balls, else increment the box
 * index like: (box_index + n²) % number_of_boxes.
 * Where n start from 1 to positive integer infinity.
 * Repeat this k times.
 * Add nothing to information.
 *
 * <br><br>
 * <u><b>Others HashFunction list:</b></u><br>
 * - {@link com.adavid.visualhasher.domain.ChainingHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.DoubleChoiceHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @see ChainingHashFunctionWorker
 * @see DoubleChoiceHashFunctionWorker
 * @see LinearOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class QuadraticOpenAddressingHashFunctionWorker extends AbstractHashFunctionWorker {
    private static final int MAX_TRY = 50;
    private static final int MAX_COMPUTE = 3;

    /**
     * Create a QuadraticOpenAddressingHashFunctionWorker with the NumberOfBoxes, and the number of draws.
     *
     * @param boxes NumberOfBoxes. The number of boxes, selected by the user.
     * @param draws Int. The number of draws, selected by the user.
     *
     * @since 1.0.0
     */
    public QuadraticOpenAddressingHashFunctionWorker(final NumberOfBoxes boxes, final int draws) {
        super(boxes, draws);
    }

    private QuadraticOpenAddressingHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super(new NumberOfBoxes(2), 2);
        throw new UnsupportedOperationException(
                "Cannot create a QuadraticOpenAddressingHashFunctionWorker without the number of boxes, and the number of draws. Please call a public constructor with the number of boxes, and the number of draws.");
    }

    @Override
    protected HashFunctionResult doInBackground() throws CannotComputeIndexException {
        // TODO use publish method to send data to process

        final int boxesSize = this.getBoxes();
        final var boxes = Collections.synchronizedList(new ArrayList<ComputedBox>(boxesSize));
        var maxBalls = 0;
        final var maxBoxesIndexes = Collections.synchronizedList(new ArrayList<Integer>());
        for (var i = 0; i < boxesSize; ++i) {
            boxes.add(new ComputedBox(i, 0, true, false));
        }

        final var draws = this.getDraws();

        this.setProgress(0);

        final var firstIndexes = Collections.synchronizedList(new ArrayList<Integer>(
                QuadraticOpenAddressingHashFunctionWorker.MAX_COMPUTE));
        var retry = 0;
        var indexBox = 0;

        for (var i = 0; i < draws; ++i) {
            if (this.isCancelled()) {
                break;
            }

            retry = 0;
            firstIndexes.clear();

            while (!this.isCancelled() && QuadraticOpenAddressingHashFunctionWorker.MAX_COMPUTE > retry) {
                final var firstIndex = this.compute();
                firstIndexes.add(firstIndex);

                indexBox = firstIndex;
                var delta = 0;

                while (!this.isCancelled() && 0 != boxes.get(indexBox)
                                                        .getBalls() && QuadraticOpenAddressingHashFunctionWorker.MAX_TRY >= delta) {
                    ++delta;

                    indexBox = (firstIndex + delta * delta) % boxesSize;
                }

                if (this.isCancelled()) {
                    break;
                }

                if (QuadraticOpenAddressingHashFunctionWorker.MAX_TRY >= delta) {
                    boxes.get(indexBox).setFirstBox(0 == retry && 0 == delta);
                    boxes.get(indexBox).incrementsBalls();

                    break;
                }

                ++retry;
            }

            if (this.isCancelled()) {
                break;
            }

            if (QuadraticOpenAddressingHashFunctionWorker.MAX_COMPUTE <= retry) {
                final var errorMessage = new StringBuilder(
                        "Cannot compute the box index. Try 50 quadratics increments with this first indexes: ");

                for (final var index : firstIndexes) {
                    errorMessage.append(index);
                    errorMessage.append(", ");
                }
                if (errorMessage.toString().endsWith(", ")) {
                    errorMessage.setLength(errorMessage.length() - 2);
                }

                throw new CannotComputeIndexException(errorMessage.toString());
            }

            if (maxBalls != Math.max(maxBalls, boxes.get(indexBox).getBalls())) {
                maxBalls = Math.max(maxBalls, boxes.get(indexBox).getBalls());
                maxBoxesIndexes.clear();
            }

            if (boxes.get(indexBox).getBalls() == maxBalls) {
                maxBoxesIndexes.add(indexBox);
            }

            this.setProgress(Math.min(98, i * 100 / draws));
        }

        if (this.isCancelled()) {
            return null;
        }

        this.setProgress(99);

        final var information = new StringBuilder("Most filled boxes (" + maxBalls + " balls): ");

        final var maxBoxesIndexesOrdered = maxBoxesIndexes.parallelStream().sorted().toList();
        for (final var index : maxBoxesIndexesOrdered) {
            information.append(index).append(", ");
        }
        if (information.toString().endsWith(", ")) {
            information.setLength(information.length() - 2);
        }

        this.setProgress(100);

        return new HashFunctionResult(information.toString(), boxes);
    }

    @Override
    protected void process(final List<HashFunctionResult> chunks) {
        // TODO Implement
    }
}
