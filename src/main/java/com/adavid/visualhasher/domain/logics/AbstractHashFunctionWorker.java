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

package com.adavid.visualhasher.domain.logics;

import com.adavid.visualhasher.domain.exceptions.DrawsOutOfBoundsException;
import com.adavid.visualhasher.domain.exceptions.IllegalNumberOfBoxesException;
import com.adavid.visualhasher.domain.exceptions.IllegalNumberOfDrawsException;
import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import javax.swing.SwingWorker;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The common base of HashFunctionWorker.
 *
 * <br><br>
 * <u><b>HashFunctions list:</b></u><br>
 * - {@link ChainingHashFunctionWorker}<br>
 * - {@link DoubleChoiceHashFunctionWorker}<br>
 * - {@link LinearOpenAddressingHashFunctionWorker}<br>
 * - {@link QuadraticOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see ChainingHashFunctionWorker
 * @see DoubleChoiceHashFunctionWorker
 * @see LinearOpenAddressingHashFunctionWorker
 * @see QuadraticOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public abstract class AbstractHashFunctionWorker extends SwingWorker<HashFunctionResult, HashFunctionResult> {
    private final int boxes;
    private final int draws;

    AbstractHashFunctionWorker(final NumberOfBoxes boxes, final int draws) {
        super();

        try {
            final var drawsInterval = new DrawsRange(boxes);

            if (!drawsInterval.contains(draws)) {
                throw new IllegalNumberOfDrawsException(
                        "Cannot create an AbstractHashFunctionWorker with an invalid number of draws.",
                        new DrawsOutOfBoundsException(draws, drawsInterval)
                );
            }

            this.boxes = boxes.getAsInt();
            this.draws = draws;
        }
        catch (final IllegalNumberOfBoxesException error) {
            throw new IllegalNumberOfDrawsException("Cannot create an AbstractHashFunctionWorker with an invalid number of box.",
                                                    error
            );
        }
    }

    private AbstractHashFunctionWorker() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create an AbstractHashFunctionWorker without the number of box, and the number of draws. Please call the protected constructor with the number of box, and the number of draws.");
    }

    final int getBoxes() {
        return this.boxes;
    }

    final int getDraws() {
        return this.draws;
    }

    /**
     * Compute a random value between 0, and the number of box less 1.
     *
     * @return Returns a value in [0, number of box - 1]
     */
    final int compute() {
        // noinspection UnsecureRandomNumberGeneration
        //        return Math.toIntExact(Math.round(Math.random() * (this.boxes - 1)));
        return ThreadLocalRandom.current().nextInt(0, this.boxes);
    }

    @Override
    public void done() {

    }
}
