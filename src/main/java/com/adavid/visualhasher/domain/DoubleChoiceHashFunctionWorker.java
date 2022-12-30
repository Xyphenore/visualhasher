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

    public DoubleChoiceHashFunctionWorker(final int boxes, final int draws) {
        super(boxes, draws);
    }

    private DoubleChoiceHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super(2, 2);
        throw new UnsupportedOperationException(
                "Cannot create a DoubleChoiceHashFunctionWorker without the number of boxes and the number of draws. Please call a public constructor with the number of boxes and the number of draws.");
    }

    @Override
    protected HashFunctionResult doInBackground() throws Exception {
        // TODO use publish method to send data to process
        // TODO Use setProgress to update the progress
        return null;
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
