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
import com.adavid.visualhasher.domain.objects.Box;
import com.adavid.visualhasher.domain.objects.ComputationBox;
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
 * The double choice hash function.
 * Draws two box information between 1 and number_of_boxes, and increments the balls in the box less filled.
 * Repeat this n times.
 * Add to information, all box with the maximum balls.
 *
 * <br><br>
 * <u><b>Others HashFunction list:</b></u><br>
 * - {@link ChainingHashFunctionWorker}<br>
 * - {@link LinearOpenAddressingHashFunctionWorker}<br>
 * - {@link QuadraticOpenAddressingHashFunctionWorker}<br>
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see AbstractHashFunctionWorker
 * @see ChainingHashFunctionWorker
 * @see LinearOpenAddressingHashFunctionWorker
 * @see QuadraticOpenAddressingHashFunctionWorker
 * @since 1.0.0
 */
public final class DoubleChoiceHashFunctionWorker extends SwingWorker<Void, HashFunctionIntermediaryResult> {
    private final List<ComputationBox> boxes;
    private final int draws;
    private final JTextArea information;
    private final JList<? extends Box> boxesPanel;
    private String finalInformation = null;

    /**
     * Create a DoubleChoiceHashFunctionWorker with the NumberOfBoxes, and the number of draws.
     *
     * @param boxes NumberOfBoxes. The number of box, selected by the user.
     * @param draws Int. The number of draws, selected by the user.
     *
     * @since 1.0.0
     */
    public DoubleChoiceHashFunctionWorker(
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
            this.boxes.add(new ComputationBox(i, 0));
        }

        this.information = information;
        this.boxesPanel = boxesPanel;
    }

    private DoubleChoiceHashFunctionWorker() {
        // Note: Permit creating a valid AbstractWorker
        // To throw after an explicit exception.
        super();
        throw new UnsupportedOperationException(
                "Cannot create a ChainingHashFunctionWorker without the number of box, and the number of draws. Please call a public constructor with the number of box, and the number of draws.");
    }

    @Override
    protected Void doInBackground() {
        var maxBalls = 0;
        final var maxBoxesIndexes = Collections.synchronizedList(new ArrayList<Integer>());

        this.setProgress(0);

        for (var i = 0; (i < this.draws) && (!this.isCancelled()); ++i) {
            final var indexBox1 = this.compute();
            final var indexBox2 = this.compute();

            var indexBox = indexBox1;
            if ((indexBox1 != indexBox2) && (
                    this.boxes.get(indexBox1).getBalls() > this.boxes.get(indexBox2).getBalls()
            )) {
                indexBox = indexBox2;
            }

            this.boxes.get(indexBox).incrementsBalls();

            if (maxBalls != Math.max(maxBalls, this.boxes.get(indexBox).getBalls())) {
                maxBalls = Math.max(maxBalls, this.boxes.get(indexBox).getBalls());
                maxBoxesIndexes.clear();
            }

            if (this.boxes.get(indexBox).getBalls() == maxBalls) {
                maxBoxesIndexes.add(indexBox);
            }

            this.publish(new HashFunctionIntermediaryResult(generateInformation(maxBalls, maxBoxesIndexes),
                                                            this.boxes.get(indexBox)
            ));

            this.setProgress(Math.min(98, i * 100 / this.draws));
        }

        if (this.isCancelled()) {
            return null;
        }

        for (final var box : this.boxes) {
            box.setMaximumBalls(maxBalls);
        }

        this.setProgress(99);

        this.finalInformation = generateInformation(maxBalls, maxBoxesIndexes);

        this.setProgress(100);

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
        for (final var entry : chunks) {
            final var box = entry.box();
            this.boxesPanel.getModel().getElementAt(box.getID()).setBalls(box.getBalls());
        }

        this.information.setVisible(false);
        this.information.setText(chunks.get(chunks.size() - 1).information());
        this.information.setVisible(true);
    }

    @Override
    protected void done() {
        this.information.setVisible(false);
        this.information.setText(this.finalInformation);
        this.information.setVisible(true);
    }
}
