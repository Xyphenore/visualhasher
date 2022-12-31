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

package com.adavid.visualhasher.presentation.views.components;

import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.Serial;

public final class Box extends JPanel {
    @Serial
    private static final long serialVersionUID = 5342606245543315329L;
    public static Color DEFAULT_COLOR = Color.GREEN;
    private final JTextArea idField;
    private final JTextArea ballsNumber;
    private JPanel ballsPanel;
    private int balls;

    public Box() {
        this(0);
    }

    public Box(final int balls) {
        this.balls = balls;

        this.idField = new JTextArea("0");
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

        this.ballsPanel = new JPanel();
        this.ballsNumber.setAutoscrolls(false);
        this.ballsPanel.setEnabled(true);
        this.ballsPanel.setOpaque(true);
        this.ballsPanel.setName("ballsPanel");
        this.ballsPanel.setVisible(true);

        this.add(this.idField);
        this.add(this.ballsPanel);
        this.add(this.ballsNumber);

        this.setEnabled(true);
        this.setOpaque(true);
        this.setAutoscrolls(true);
        this.setName("box");
        this.setVisible(true);
    }

    public int getBalls() {
        return this.balls;
    }

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
                    this.incrementBalls();
                }
            }
            else {
                for (var i = 0; i < this.balls - balls; ++i) {
                    this.decrementBalls();
                }
            }
        }
    }

    public void clear() {
        if (0 != this.balls) {
            this.balls = 0;
            this.ballsNumber.setText(String.valueOf(this.balls));
            this.ballsPanel = new JPanel();
        }
    }

    public void incrementBalls() {
        this.incrementBalls(Box.DEFAULT_COLOR);
    }

    public void decrementBalls() {
        if (0 >= this.balls) {
            throw new RuntimeException(
                    "Cannot decremente the number of balls. The number of balls must be over or equal to zero.");
        }
        --this.balls;
        this.ballsNumber.setText(String.valueOf(this.balls));
        this.ballsPanel.remove(this.balls - 1);
    }

    public void incrementBalls(final Color ballColor) {
        this.balls = Math.addExact(this.balls, 1);
        this.ballsNumber.setText(String.valueOf(this.balls));
        //        this.ballsPanel.add();// TODO
    }

    private void drawBall() {

    }

    @Override
    public String toString() {
        return "Box{balls=" + this.balls + "}";
    }

    public enum Color {
        RED, GREEN
    }
}
