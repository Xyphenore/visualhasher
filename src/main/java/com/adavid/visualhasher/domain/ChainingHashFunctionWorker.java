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

import com.adavid.visualhasher.presentation.views.components.Box;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ChainingHashFunctionWorker extends AbstractHashFunctionWorker {
    public ChainingHashFunctionWorker(final int boxes, final int draws) {
        super(boxes, draws);
    }

    private ChainingHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super(2, 2);
        throw new UnsupportedOperationException(
                "Cannot create a ChainingHashFunctionWorker without the number of boxes and the number of draws. Please call a public constructor with the number of boxes and the number of draws.");
    }

    @Override
    protected HashFunctionResult doInBackground() {
        // TODO use publish method to send data to process

        final int boxesSize = this.getBoxes();
        final var boxes = new Box[boxesSize];
        var maxBalls = 0;
        final Collection<Integer> maxBoxesIndexes = new ArrayList<>();

        final var draws = this.getDraws();

        this.setProgress(0);

        for (var i = 0; i < draws; ++i) {
            final var indexBox = this.compute();

            if (null == boxes[indexBox]) {
                boxes[indexBox] = new Box(0);
            }
            boxes[indexBox].incrementBalls();

            if (maxBalls != Math.max(maxBalls, boxes[indexBox].getBalls())) {
                maxBalls = Math.max(maxBalls, boxes[indexBox].getBalls());
                maxBoxesIndexes.clear();
            }

            if (boxes[indexBox].getBalls() == maxBalls) {
                maxBoxesIndexes.add(indexBox);
            }

            this.setProgress(Math.min(98, i * 100 / draws));
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

        return new HashFunctionResult(information.toString(), List.of(boxes));
    }

    /**
     * Compute a random value between 0, and the number of boxes less 1.
     *
     * @return Returns a value in [0, number of boxes - 1]
     */
    private int compute() {
        return Math.toIntExact(Math.round(Math.random() * (this.getBoxes() - 1)));
    }

    @Override
    protected void process(final List<HashFunctionResult> chunks) {
        // TODO Implement
    }

    @Override
    protected void done() {
        // TODO May be implement the function executed after the doInBG
    }
}
