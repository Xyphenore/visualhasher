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

import com.adavid.visualhasher.domain.exceptions.AlreadyMadeLoopException;
import com.adavid.visualhasher.domain.exceptions.DrawsOutOfBoundsException;
import com.adavid.visualhasher.domain.exceptions.IllegalNumberOfBoxesException;
import com.adavid.visualhasher.domain.exceptions.IllegalNumberOfDrawsException;
import com.adavid.visualhasher.domain.objects.Box;
import com.adavid.visualhasher.domain.objects.Color;
import com.adavid.visualhasher.domain.objects.ColoredUniqueBallBox;
import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
 * - {@link ChainingHashFunctionWorker}<br>
 * - {@link DoubleChoiceHashFunctionWorker}<br>
 * - {@link LinearOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @see ChainingHashFunctionWorker
 * @see DoubleChoiceHashFunctionWorker
 * @see LinearOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class QuadraticOpenAddressingHashFunctionWorker extends SwingWorker<Void, HashFunctionIntermediaryResult> {
    private static final int MAX_TRY = 50;
    private static final int MAX_COMPUTE = 3;
    private final int draws;

    private final List<ColoredUniqueBallBox> boxes;
    private final JTextArea information;
    private final JList<? extends Box> boxesPanel;
    private final List<Integer> maxBoxesIndexes = Collections.synchronizedList(new ArrayList<>());

    private int maxBalls;

    private String finalInformation = null;

    /**
     * Create a QuadraticOpenAddressingHashFunctionWorker with the NumberOfBoxes, and the number of draws.
     *
     * @param boxes NumberOfBoxes. The number of box, selected by the user.
     * @param draws Int. The number of draws, selected by the user.
     *
     * @since 1.0.0
     */
    public QuadraticOpenAddressingHashFunctionWorker(
            final NumberOfBoxes boxes, final int draws, final JTextArea information, final JList<? extends Box> boxesPanel
                                                    ) {
        super();
        try {
            final var drawsInterval = new DrawsRange(boxes);

            if (!drawsInterval.contains(draws)) {
                throw new IllegalNumberOfDrawsException(
                        "Cannot create an AbstractHashFunctionWorker with an invalid number of draws.",
                        new DrawsOutOfBoundsException(draws, drawsInterval)
                );
            }

            this.draws = draws;
        }
        catch (final IllegalNumberOfBoxesException error) {
            throw new IllegalNumberOfDrawsException("Cannot create an AbstractHashFunctionWorker with an invalid number of box.",
                                                    error
            );
        }

        final var boxesSize = boxes.getAsInt();

        this.boxes = Collections.synchronizedList(new ArrayList<>(boxesSize));
        for (var i = 0; i < boxesSize; ++i) {
            this.boxes.add(new ColoredUniqueBallBox(i, 0));
        }

        this.information = information;
        this.boxesPanel = boxesPanel;
    }

    private QuadraticOpenAddressingHashFunctionWorker() {
        throw new UnsupportedOperationException(
                "Cannot create a QuadraticOpenAddressingHashFunctionWorker without the number of box, and the number of draws. Please call a public constructor with the number of box, and the number of draws.");
    }

    @Override
    protected Void doInBackground() throws AlreadyMadeLoopException {
        this.setProgress(0);

        var retry = 0;

        //        for (var i = 0; i < draws; ++i) {
        //            if (this.isCancelled()) {
        //                break;
        //            }
        //
        //            retry = 0;
        //            //            firstIndexes.clear();
        //
        //            //            while (!this.isCancelled() && QuadraticOpenAddressingHashFunctionWorker.MAX_COMPUTE > retry) {
        //            //                final var firstIndex = this.compute();
        //            //                firstIndexes.add(firstIndex);
        //            //
        //            //                indexBox = firstIndex;
        //            //                var delta = 0;
        //            //
        //            //                while (!this.isCancelled() && 0 != boxes.get(indexBox)
        //            //                                                        .getBalls() && QuadraticOpenAddressingHashFunctionWorker.MAX_TRY >= delta) {
        //            //                    ++delta;
        //            //
        //            //                    indexBox = (firstIndex + delta * delta) % boxesSize;
        //            //                }
        //            //
        //            //                if (this.isCancelled()) {
        //            //                    break;
        //            //                }
        //            //
        //            //                if (QuadraticOpenAddressingHashFunctionWorker.MAX_TRY >= delta) {
        //            //                    final var color = (0 == retry && 0 == delta) ? Color.GREEN : Color.RED;
        //            //                    boxes.get(indexBox).setColor(color);
        //            //                    boxes.get(indexBox).incrementsBalls();
        //            //
        //            //                    break;
        //            //                }
        //            //
        //            //                ++retry;
        //            //            }
        //
        //            if (this.isCancelled()) {
        //                break;
        //            }
        //
        //            if (QuadraticOpenAddressingHashFunctionWorker.MAX_COMPUTE <= retry) {
        //                final var errorMessage = new StringBuilder(
        //                        "Cannot compute the box index. Try 50 quadratics increments with this first information: ");
        //
        //                for (final var index : firstIndexes) {
        //                    errorMessage.append(index);
        //                    errorMessage.append(", ");
        //                }
        //                if (errorMessage.toString().endsWith(", ")) {
        //                    errorMessage.setLength(errorMessage.length() - 2);
        //                }
        //
        //                throw new CannotComputeIndexException(errorMessage.toString());
        //            }
        //
        //            //            if (maxBalls != Math.max(maxBalls, boxes.get(indexBox).getBalls())) {
        //            //                maxBalls = Math.max(maxBalls, boxes.get(indexBox).getBalls());
        //            //                maxBoxesIndexes.clear();
        //            //            }
        //            //
        //            //            if (boxes.get(indexBox).getBalls() == maxBalls) {
        //            //                maxBoxesIndexes.add(indexBox);
        //            //            }
        //
        //            this.setProgress(Math.min(98, i * 100 / draws));
        //        }

        for (var i = 0; i < this.draws && !this.isCancelled(); ++i) {
            var indexBox = this.compute();
            final var firstIndex = indexBox;
            var doLoop = false;
            while (!this.isCancelled() && 0 != this.boxes.get(indexBox).getBalls()) {
                if (!doLoop) {
                    doLoop = true;
                }

                indexBox = (indexBox + 1) % this.boxes.size();

                if (firstIndex == indexBox) {
                    throw new AlreadyMadeLoopException(
                            "The computed index is equal to the first index. It is impossible.");
                }
            }

            if (this.isCancelled()) {
                break;
            }

            final var color = doLoop ? Color.RED : Color.GREEN;

            this.boxes.get(indexBox).setColor(color);
            this.boxes.get(indexBox).incrementsBalls();

            if (this.maxBalls != Math.max(this.maxBalls, this.boxes.get(indexBox).getBalls())) {
                this.maxBalls = Math.max(this.maxBalls, this.boxes.get(indexBox).getBalls());
                this.maxBoxesIndexes.clear();
            }

            if (this.boxes.get(indexBox).getBalls() == this.maxBalls) {
                this.maxBoxesIndexes.add(indexBox);
            }

            this.publish(new HashFunctionIntermediaryResult(generateInformation(this.maxBalls, this.maxBoxesIndexes),
                                                            this.boxes.get(indexBox)
            ));

            this.setProgress(Math.min(98, i * 100 / this.draws));
        }

        if (this.isCancelled()) {
            return null;
        }

        this.setProgress(99);

        final var information = new StringBuilder("Most filled box (" + this.maxBalls + " balls): ");

        final var maxBoxesIndexesOrdered = this.maxBoxesIndexes.parallelStream().sorted().toList();
        for (final var index : maxBoxesIndexesOrdered) {
            information.append(index).append(", ");
        }
        if (information.toString().endsWith(", ")) {
            information.setLength(information.length() - 2);
        }

        this.setProgress(100);

        //        return new HashFunctionResult(information.toString(), boxes);
        return null;
    }

    /**
     * Compute a random value between 0, and the number of box less 1.
     *
     * @return Returns a value in [0, number of box - 1]
     */
    private int compute() {
        // noinspection UnsecureRandomNumberGeneration
        //        return Math.toIntExact(Math.round(Math.random() * (this.boxes - 1)));
        return ThreadLocalRandom.current().nextInt(0, this.boxes.size());
    }

    private static String generateInformation(final int maxBalls, final Collection<Integer> maxBoxesIndexes) {
        final var information = new StringBuilder("Most filled box (" + maxBalls + " balls): ");

        final var maxBoxesIndexesOrdered = maxBoxesIndexes.parallelStream().sorted().toList();
        for (final var index : maxBoxesIndexesOrdered) {
            information.append(index).append(", ");
        }
        if (information.toString().endsWith(", ")) {
            information.setLength(information.length() - 2);
        }

        return information.toString();
    }


    @Override
    protected void process(final List<HashFunctionIntermediaryResult> chunks) {
        if (!this.isCancelled()) {
            for (final var entry : chunks) {
                final var box = (ColoredUniqueBallBox) entry.box();
                this.boxesPanel.getModel().getElementAt(box.getID()).setBalls(box.getBalls());
                final var colored = (ColoredUniqueBallBox) this.boxesPanel.getModel().getElementAt(box.getID());
                colored.setColor(box.getColor());
            }

            this.information.setVisible(false);
            this.information.setText(chunks.get(chunks.size() - 1).information());
            this.information.setVisible(true);
        }
    }

    @Override
    protected void done() {
        if (!this.isCancelled()) {
            this.information.setVisible(false);
            this.information.setText(this.finalInformation);
            this.information.setVisible(true);
        }
    }
}

