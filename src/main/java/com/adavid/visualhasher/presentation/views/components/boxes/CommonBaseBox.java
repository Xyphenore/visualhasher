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

package com.adavid.visualhasher.presentation.views.components.boxes;

import com.adavid.visualhasher.domain.Box;

import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.Serial;
import java.util.Objects;

/**
 * The common base for Boxes.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see NumberBox
 * @see OpenAddessingBox
 * @since 1.0.0
 */
public class CommonBaseBox extends JPanel implements Box {
    @Serial
    private static final long serialVersionUID = 1980945206824302788L;
    private final JTextArea idField;
    private final JTextArea ballsNumber;

    private final int id;
    private int balls;
    private ColoredBox.Color color = ColoredBox.Color.GREEN;

    private CommonBaseBox() {
        super();
        throw new UnsupportedOperationException(
                "Cannot create a CommonBaseBox without the ID and the number of balls. Please call a protected constructor with an ID and the number of balls.");
    }

    protected CommonBaseBox(final int id, final int balls) {
        super();

        if (0 > id) {
            throw new IllegalBoxIDException(
                    "Cannot create a box with an ID less than 0. Please give an ID greater or equal to 0.");
        }

        if (0 > balls) {
            throw new IllegalNumberOfBallsException(
                    "Cannot create a NumberBox with a number less than 0. Please give a number greater or equal to 0.");
        }

        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.balls = balls;
        this.id = id;

        this.idField = new JTextArea(String.valueOf(id));
        this.idField.setEnabled(true);
        this.idField.setOpaque(true);
        this.idField.setEditable(false);
        this.idField.setDragEnabled(false);
        this.idField.setDropMode(DropMode.INSERT);
        this.idField.setName("idField");
        this.idField.setVisible(true);

        this.ballsNumber = new JTextArea(String.valueOf(this.balls));
        this.ballsNumber.setEnabled(true);
        this.ballsNumber.setOpaque(true);
        this.ballsNumber.setEditable(false);
        this.ballsNumber.setDragEnabled(false);
        this.ballsNumber.setDropMode(DropMode.INSERT);
        this.ballsNumber.setName("ballsNumber");
        this.ballsNumber.setVisible(true);

        super.add(this.idField);
        super.add(this.ballsNumber);

        this.setEnabled(true);
        this.setOpaque(true);
        this.setAutoscrolls(true);
        this.setName("box");
        this.setVisible(true);
    }

    protected CommonBaseBox(final int id, final boolean filled, final ColoredBox.Color color) {
        if (0 > id) {
            throw new IllegalBoxIDException(
                    "Cannot create a box with an ID less than 0. Please give an ID greater or equal to 0.");
        }

        this.balls = filled ? 1 : 0;
        this.color = color;
        this.id = id;

        this.idField = new JTextArea(String.valueOf(id));
        this.idField.setEnabled(true);
        this.idField.setOpaque(true);
        this.idField.setEditable(false);
        this.idField.setDragEnabled(false);
        this.idField.setDropMode(DropMode.INSERT);
        this.idField.setName("idField");
        this.idField.setVisible(true);

        var text = "0";
        if (0 < this.balls) {
            if (ColoredBox.Color.RED == color) {
                text = "R";
            }
            else if (ColoredBox.Color.GREEN == color) {
                text = "O";
            }
        }

        this.ballsNumber = new JTextArea(text);
        this.ballsNumber.setEnabled(true);
        this.ballsNumber.setOpaque(true);
        this.ballsNumber.setEditable(false);
        this.ballsNumber.setDragEnabled(false);
        this.ballsNumber.setDropMode(DropMode.INSERT);
        this.ballsNumber.setName("ballsNumber");
        this.ballsNumber.setVisible(true);

        this.add(this.idField);
        this.add(this.ballsNumber);

        // TODO Faire la mise en page

        this.setEnabled(true);
        this.setOpaque(true);
        this.setAutoscrolls(true);
        this.setName("box");
        this.setVisible(true);
    }

    @Override
    public final int getBalls() {
        return this.balls;
    }

    @Override
    public void setBalls(final int balls) {
        if (0 > balls) {
            throw new IllegalArgumentException("The number of balls must be over or equal to zero.");
        }

        if (balls != this.balls) {
            if (0 == balls) {
                this.clear();
            }
            else if (balls > this.balls) {
                for (var i = 0; i < balls - this.balls; ++i) {
                    this.incrementsBalls();
                }
            }
            else {
                for (var i = 0; i < this.balls - balls; ++i) {
                    this.decrementBalls();
                }
            }
        }
    }

    @Override
    public final void clear() {
        if (0 != this.balls) {
            this.balls = 0;
            this.ballsNumber.setText(String.valueOf(this.balls));
        }
    }

    @Override
    public void incrementsBalls() {
        this.balls = Math.addExact(this.balls, 1);
        this.ballsNumber.setText(String.valueOf(this.balls));
    }

    @Override
    public final void decrementBalls() {
        if (0 >= this.balls) {
            throw new RuntimeException(
                    "Cannot decrease the number of balls. The number of balls must be over or equal to zero.");
        }
        --this.balls;
        this.ballsNumber.setText(String.valueOf(this.balls));
    }

    @Override
    public int getID() {
        return this.id;
    }

    protected ColoredBox.Color getColor() {
        return this.color;
    }

    protected final void setColor(final ColoredBox.Color color) {
        var text = "O";
        if (ColoredBox.Color.RED == color) {
            text = "R";
        }
        this.ballsNumber.setText(text);
    }

    final void setTextballs(final String text) {
        final var goodtext = Objects.requireNonNullElse(text, "");
        this.ballsNumber.setText(goodtext);
    }
}
