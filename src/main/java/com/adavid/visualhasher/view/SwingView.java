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

package com.adavid.visualhasher.view;

import com.adavid.visualhasher.view.component.BoxesSpinner;
import com.adavid.visualhasher.view.component.DrawsSpinner;
import com.adavid.visualhasher.view.component.HashFunctionSelector;
import com.adavid.visualhasher.view.exception.IllegalHashFunction;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class SwingView {
    private JPanel mainPanel;
    private JPanel buttonBar;
    private JScrollPane boxesPanel;
    private BoxesSpinner boxesNb;
    private DrawsSpinner drawsNb;
    private JComboBox hashFunction;
    private JButton runButton;
    private JButton reRunButton;
    private JButton cancelButton;
    private JProgressBar progressBar;

    public SwingView() {
        super();

        this.createUIComponents();

        this.boxesNb.addPropertyChangeListener((final PropertyChangeEvent ignored) -> {
            final int boxesNbValue = (int) this.boxesNb.getValue();
            this.drawsNb.setInterval(boxesNbValue / 2, boxesNbValue);
        });
        this.runButton.addPropertyChangeListener((final PropertyChangeEvent ignored) -> {
            this.runButton.setEnabled(false);
            this.runButton.setVisible(false);
            this.runButton.setFocusable(false);

            this.reRunButton.setEnabled(true);
            this.reRunButton.setVisible(true);
            this.reRunButton.setFocusable(true);

            this.cancelButton.setEnabled(true);
            this.cancelButton.setVisible(true);
            this.cancelButton.setFocusable(true);

            this.progressBar.setEnabled(true);
            this.progressBar.setVisible(true);
            this.progressBar.setValue(0);

            final var boxesNumber = (int) this.boxesNb.getValue();
            final var drawsNumber = (int) this.drawsNb.getValue();
            final var function = (String) this.hashFunction.getSelectedItem();

            this.progressBar.setMaximum(drawsNumber);

            switch (function) {
                case Constants.CHAINING_FUNCTION:
                    // TODO
                    // TODO Actualiser la barre de progression
                    break;

                case Constants.DOUBLE_CHOICES:
                    // TODO
                    break;

                case Constants.LINEAR_OPEN_ADDRESSING:
                    // TODO
                    break;

                case Constants.QUADRATIC_OPEN_ADDRESSING:
                    // TODO
                    break;

                default:
                    throw new IllegalHashFunction("The selected hash function is invalid. The selected value : " + hashFunction);
            }
        });
        this.reRunButton.addPropertyChangeListener(propertyChangeEvent -> {
            // TODO Cancel and rerun operation
            this.progressBar.setValue(0);

            final var boxesNumber = (int) this.boxesNb.getValue();
            final var drawsNumber = (int) this.drawsNb.getValue();
            final var function = (String) this.hashFunction.getSelectedItem();

            this.progressBar.setMaximum(drawsNumber);

            switch (function) {
                case Constants.CHAINING_FUNCTION:
                    // TODO
                    // TODO Actualiser la barre de progression
                    break;

                case Constants.DOUBLE_CHOICES:
                    // TODO
                    break;

                case Constants.LINEAR_OPEN_ADDRESSING:
                    // TODO
                    break;

                case Constants.QUADRATIC_OPEN_ADDRESSING:
                    // TODO
                    break;

                default:
                    throw new IllegalHashFunction("The selected hash function is invalid. The selected value : " + hashFunction);
            }
        });
        this.cancelButton.addPropertyChangeListener((final PropertyChangeEvent ignored) -> {
            // TODO Cancel operation

            this.reRunButton.setEnabled(false);
            this.reRunButton.setVisible(false);
            this.reRunButton.setFocusable(false);

            this.cancelButton.setEnabled(false);
            this.cancelButton.setVisible(false);
            this.cancelButton.setFocusable(false);

            this.runButton.setEnabled(true);
            this.runButton.setVisible(true);
            this.runButton.setFocusable(true);

            this.progressBar.setValue(0);
            this.progressBar.setEnabled(false);
            this.progressBar.setVisible(false);
        });
    }

    private void createUIComponents() {
        this.boxesNb = new BoxesSpinner();
        this.boxesNb.setEnabled(true);
        this.boxesNb.setVisible(true);
        this.boxesNb.setOpaque(true);
        this.boxesNb.setFocusable(true);
        this.boxesNb.setName("boxesNb");

        this.drawsNb = new DrawsSpinner();
        this.drawsNb.setEnabled(true);
        this.drawsNb.setVisible(true);
        this.drawsNb.setOpaque(true);
        this.drawsNb.setFocusable(true);
        this.drawsNb.setName("drawNb");

        this.hashFunction = new HashFunctionSelector();
        this.hashFunction.setEditable(false);
        this.hashFunction.setEnabled(true);
        this.hashFunction.setName("hashFunctions");
        this.hashFunction.setFocusable(true);
        this.hashFunction.setVisible(true);
        this.hashFunction.setOpaque(true);

        this.runButton = new JButton("Run");
        this.runButton.setEnabled(true);
        this.runButton.setVisible(true);
        this.runButton.setFocusable(true);
        this.runButton.setName("runButton");
        this.runButton.setOpaque(true);
        this.runButton.setToolTipText("Run the selected hash function with provided number of boxes and number of " + "draws.");
        this.runButton.setHideActionText(false);

        this.reRunButton = new JButton("Re-Run");
        this.reRunButton.setEnabled(false);
        this.reRunButton.setVisible(false);
        this.reRunButton.setFocusable(false);
        this.reRunButton.setName("reRunButton");
        this.reRunButton.setOpaque(true);
        this.reRunButton.setToolTipText(
                "Stop the running operation, and run the selected hash function with provided number of boxes and number of draws.");
        this.reRunButton.setHideActionText(false);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setEnabled(false);
        this.cancelButton.setVisible(false);
        this.cancelButton.setFocusable(false);
        this.cancelButton.setName("cancelButton");
        this.cancelButton.setOpaque(true);
        this.cancelButton.setToolTipText("Stop the running operation.");
        this.cancelButton.setHideActionText(false);

        this.progressBar = new JProgressBar();
        this.progressBar.setName("progressBar");
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
        this.progressBar.setFocusable(false);
        this.progressBar.setOpaque(true);
        this.progressBar.setMinimum(0);

        this.buttonBar = new JPanel(new FlowLayout());
        this.buttonBar.add(this.boxesNb);
        this.buttonBar.add(this.drawsNb);
        this.buttonBar.add(this.hashFunction);
        this.buttonBar.add(this.runButton);
        this.buttonBar.add(this.reRunButton);
        this.buttonBar.add(this.cancelButton);
        this.buttonBar.add(this.progressBar);

        this.boxesPanel = new JScrollPane();

        final var layout = new GridLayout(2, 1);
        this.mainPanel = new JPanel(layout);
        this.mainPanel.add(this.buttonBar);
        this.mainPanel.add(this.boxesPanel);
    }

    public static void main(final String[] args) {
        final var frame = new JFrame("SwingView");
        final var view = new SwingView();
        frame.setContentPane(view.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
