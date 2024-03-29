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

import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The double choice hash function.
 * Draws two box indexes between 1 and number_of_boxes, and increments the balls in the box less filled.
 * Repeat this n times.
 * Add to information, all boxes with the maximum balls.
 *
 * <br><br>
 * <u><b>Others HashFunction list:</b></u><br>
 * - {@link com.adavid.visualhasher.domain.ChainingHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker}<br>
 * - {@link com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @see ChainingHashFunctionWorker
 * @see LinearOpenAddressingHashFunctionWorker
 * @see QuadraticOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class DoubleChoiceHashFunctionWorker extends AbstractHashFunctionWorker {
    /**
     * Create a DoubleChoiceHashFunctionWorker with the NumberOfBoxes, and the number of draws.
     *
     * @param boxes NumberOfBoxes. The number of boxes, selected by the user.
     * @param draws Int. The number of draws, selected by the user.
     *
     * @since 1.0.0
     */
    public DoubleChoiceHashFunctionWorker(final NumberOfBoxes boxes, final int draws) {
        super(boxes, draws);
    }

    private DoubleChoiceHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super(new NumberOfBoxes(2), 2);
        throw new UnsupportedOperationException(
                "Cannot create a DoubleChoiceHashFunctionWorker without the number of boxes, and the number of draws. Please call a public constructor with the number of boxes, and the number of draws.");
    }

    @Override
    protected HashFunctionResult doInBackground() {
        // TODO use publish method to send data to process

        final int boxesSize = this.getBoxes();
        final var boxes = Collections.synchronizedList(new ArrayList<ComputedBox>(boxesSize));
        var maxBalls = 0;
        final var maxBoxesIndexes = Collections.synchronizedList(new ArrayList<Integer>());
        for (var i = 0; i < boxesSize; ++i) {
            boxes.add(new ComputedBox(0, 0, false, false));
        }

        final var draws = this.getDraws();

        this.setProgress(0);

        for (var i = 0; i < draws; ++i) {
            if (this.isCancelled()) {
                break;
            }

            final var indexBox1 = this.compute();
            final var indexBox2 = this.compute();

            var indexBox = indexBox1;
            if ((indexBox1 != indexBox2) && (boxes.get(indexBox1).getBalls() > boxes.get(indexBox2).getBalls())) {
                indexBox = indexBox2;
            }

            boxes.get(indexBox).incrementsBalls();

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
