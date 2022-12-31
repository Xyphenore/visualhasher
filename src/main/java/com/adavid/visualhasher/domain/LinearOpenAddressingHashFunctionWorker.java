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

package com.adavid.visualhasher.domain;

import com.adavid.visualhasher.domain.exceptions.AlreadyMadeLoopException;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;
import com.adavid.visualhasher.presentation.views.components.Box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The linear open addressing hash function.
 * Draws a box index between 1, and the number_of_boxes, if the box is empty, increments balls, else increment the box
 * index like: (box_index + 1 * n) % number_of_boxes.
 * Where n start from 1 to positive integer infinity.
 * Repeat this k times.
 * Add nothing to information.
 *
 * <br><br>
 * <u><b>Others HashFunction list:</b></u><br>
 * - {@link com.adavid.visualhasher.domain.ChainingHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.DoubleChoiceHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @see ChainingHashFunctionWorker
 * @see DoubleChoiceHashFunctionWorker
 * @see QuadraticOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class LinearOpenAddressingHashFunctionWorker extends AbstractHashFunctionWorker {
    /**
     * Create a LinearOpenAddressingHashFunctionWorker with the NumberOfBoxes, and the number of draws.
     *
     * @param boxes NumberOfBoxes. The number of boxes, selected by the user.
     * @param draws Int. The number of draws, selected by the user.
     *
     * @since 1.0.0
     */
    public LinearOpenAddressingHashFunctionWorker(final NumberOfBoxes boxes, final int draws) {
        super(boxes, draws);
    }

    private LinearOpenAddressingHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super(new NumberOfBoxes(2), 2);
        throw new UnsupportedOperationException(
                "Cannot create a LinearOpenAddressingHashFunctionWorker without the number of boxes, and the number of draws. Please call a public constructor with the number of boxes, and the number of draws.");
    }

    @Override
    protected HashFunctionResult doInBackground() throws AlreadyMadeLoopException {
        // TODO use publish method to send data to process

        final int boxesSize = this.getBoxes();
        final var boxes = Collections.synchronizedList(new ArrayList<Box>(boxesSize));
        var maxBalls = 0;
        final var maxBoxesIndexes = Collections.synchronizedList(new ArrayList<Integer>());
        for (var i = 0; i < boxesSize; ++i) {
            boxes.add(new Box(0));
        }

        final var draws = this.getDraws();

        this.setProgress(0);

        for (var i = 0; i < draws; ++i) {
            if (this.isCancelled()) {
                break;
            }

            var indexBox = this.compute();
            final var firstIndex = indexBox;
            var doLoop = false;
            while (!this.isCancelled() && 0 != boxes.get(indexBox).getBalls()) {
                if (!doLoop) {
                    doLoop = true;
                }

                indexBox = (indexBox + 1) % boxesSize;

                if (firstIndex == indexBox) {
                    throw new AlreadyMadeLoopException(
                            "The computed index is equal to the first index. It is impossible.");
                }
            }

            if (this.isCancelled()) {
                break;
            }

            final var color = doLoop ? Box.Color.RED : Box.Color.GREEN;
            boxes.get(indexBox).incrementBalls(color);

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
