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

package com.adavid.visualhasher.presentation.views;

import com.adavid.visualhasher.domain.AbstractHashFunctionWorker;
import com.adavid.visualhasher.domain.ChainingHashFunctionWorker;
import com.adavid.visualhasher.domain.DoubleChoiceHashFunctionWorker;
import com.adavid.visualhasher.domain.HashFunctionResult;
import com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker;
import com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker;
import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.domain.utility.NumberOfBoxes;
import com.adavid.visualhasher.infrastructure.Configuration;
import com.adavid.visualhasher.presentation.views.components.BoxesSpinner;
import com.adavid.visualhasher.presentation.views.components.DrawsSpinner;
import com.adavid.visualhasher.presentation.views.components.HashFunctionSelector;
import com.adavid.visualhasher.presentation.views.exceptions.IllegalSelectedHashFunctionException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * The default implementation of the user presentation, using Swing for the interface.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see View
 * @since 1.0.0
 */
public final class SwingView implements View {
    private static final String DEFAULT_TITLE = "VisualHasher";

    // Panels
    private final JPanel mainPanel = new JPanel(new GridLayout(2, 1));
    private final JScrollPane boxesPanel = new JScrollPane();

    // Selectors
    private final BoxesSpinner boxesNb = new BoxesSpinner();
    private final DrawsSpinner drawsNb = new DrawsSpinner();
    private final HashFunctionSelector hashFunction = new HashFunctionSelector();

    // Buttons and progress bar
    private final JButton runButton = new JButton("Run");
    private final JButton reRunButton = new JButton("Re-Run");
    private final JButton cancelButton = new JButton("Cancel");
    private final JProgressBar progressBar = new JProgressBar(0, 100);

    private AbstractHashFunctionWorker hashFunctionWorker;

    public SwingView(final String title) {
        super();

        var finalTitle = title;
        if (null == title) {
            finalTitle = SwingView.DEFAULT_TITLE;
        }
    }

    public SwingView() {
        super();

        this.boxesNb.setEnabled(true);
        this.boxesNb.setVisible(true);
        this.boxesNb.setOpaque(true);
        this.boxesNb.setFocusable(true);
        this.boxesNb.setName("boxesNb");

        this.drawsNb.setEnabled(true);
        this.drawsNb.setInterval(new DrawsRange(new NumberOfBoxes(this.boxesNb.getValue())));
        this.drawsNb.setVisible(true);
        this.drawsNb.setOpaque(true);
        this.drawsNb.setFocusable(true);
        this.drawsNb.setName("drawNb");

        this.hashFunction.setEditable(false);
        this.hashFunction.setEnabled(true);
        this.hashFunction.setName("hashFunctions");
        this.hashFunction.setFocusable(true);
        this.hashFunction.setVisible(true);
        this.hashFunction.setOpaque(true);

        this.runButton.setEnabled(true);
        this.runButton.setVisible(true);
        this.runButton.setFocusable(true);
        this.runButton.setName("runButton");
        this.runButton.setOpaque(true);
        this.runButton.setToolTipText("Run the selected hash function with provided number of boxes and number of " + "draws.");
        this.runButton.setHideActionText(false);
        this.runButton.setActionCommand("run");

        this.reRunButton.setEnabled(false);
        this.reRunButton.setVisible(false);
        this.reRunButton.setFocusable(false);
        this.reRunButton.setName("reRunButton");
        this.reRunButton.setOpaque(true);
        this.reRunButton.setToolTipText(
                "Stop the running operation, and run the selected hash function with provided number of boxes and number of draws.");
        this.reRunButton.setHideActionText(false);
        this.reRunButton.setActionCommand("re-run");

        this.cancelButton.setEnabled(false);
        this.cancelButton.setVisible(false);
        this.cancelButton.setFocusable(false);
        this.cancelButton.setName("cancelButton");
        this.cancelButton.setOpaque(true);
        this.cancelButton.setToolTipText("Stop the running operation.");
        this.cancelButton.setHideActionText(false);
        this.cancelButton.setActionCommand("cancel");

        this.progressBar.setName("progressBar");
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
        this.progressBar.setFocusable(false);
        this.progressBar.setOpaque(true);
        this.progressBar.setStringPainted(true);

        final var buttonBar = new JPanel(new FlowLayout());
        buttonBar.add(this.boxesNb);
        buttonBar.add(this.drawsNb);
        buttonBar.add(this.hashFunction);
        buttonBar.add(this.runButton);
        buttonBar.add(this.reRunButton);
        buttonBar.add(this.cancelButton);
        buttonBar.add(this.progressBar);

        this.boxesPanel.setEnabled(true);
        this.boxesPanel.setVisible(true);
        this.boxesPanel.setName("boxesPanel");
        this.boxesPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.boxesPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.mainPanel.add(buttonBar);
        this.mainPanel.add(this.boxesPanel);

        this.boxesNb.addChangeListener((final ChangeEvent ignored) -> {
            final var boxesNbValue = this.boxesNb.getValue();
            this.drawsNb.setInterval(new DrawsRange(new NumberOfBoxes(boxesNbValue)));
            this.drawsNb.setValue(boxesNbValue);
        });

        this.runButton.addActionListener((final ActionEvent event) -> {
            if ("run".equals(event.getActionCommand())) {
                this.runButton.setEnabled(false);
                this.runButton.setVisible(false);
                this.runButton.setFocusable(false);

                this.reRunButton.setVisible(true);
                this.reRunButton.setFocusable(true);

                this.cancelButton.setVisible(true);
                this.cancelButton.setFocusable(true);

                this.progressBar.setEnabled(true);
                this.progressBar.setVisible(true);

                this.runAction();
            }
        });

        this.reRunButton.addActionListener((final ActionEvent event) -> {
            if ("re-run".equals(event.getActionCommand())) {
                if (null != this.hashFunctionWorker) {
                    this.hashFunctionWorker.cancel(true);
                    this.hashFunctionWorker = null;
                }

                this.reRunButton.setEnabled(false);
                this.cancelButton.setEnabled(false);

                this.runAction();
            }
        });

        this.cancelButton.addActionListener((final ActionEvent event) -> {
            if ("cancel".equals(event.getActionCommand())) {
                if (null != this.hashFunctionWorker) {
                    this.hashFunctionWorker.cancel(true);
                    this.hashFunctionWorker = null;
                }

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
            }
        });
    }

    private void runAction() {
        this.progressBar.setIndeterminate(true);
        this.progressBar.setValue(0);

        final int boxesNumber = this.boxesNb.getValue();
        final var drawsNumber = (int) this.drawsNb.getValue();
        final var function = (String) Objects.requireNonNull(this.hashFunction.getSelectedItem());

        this.hashFunctionWorker = switch (function) {
            case HashFunctionSelector.CHAINING_FUNCTION -> new ChainingHashFunctionWorker(boxesNumber, drawsNumber);
            case HashFunctionSelector.DOUBLE_CHOICES -> new DoubleChoiceHashFunctionWorker(boxesNumber, drawsNumber);
            case HashFunctionSelector.LINEAR_OPEN_ADDRESSING -> new LinearOpenAddressingHashFunctionWorker(boxesNumber,
                                                                                                           drawsNumber
            );
            case HashFunctionSelector.QUADRATIC_OPEN_ADDRESSING -> new QuadraticOpenAddressingHashFunctionWorker(boxesNumber,
                                                                                                                 drawsNumber
            );
            default -> throw new IllegalSelectedHashFunctionException(
                    "The selected hash function is invalid. The selected value : " + hashFunction);
        };

        this.hashFunctionWorker.addPropertyChangeListener((final PropertyChangeEvent event) -> {
            if ("progress".equals(event.getPropertyName())) {
                if (this.progressBar.isIndeterminate()) {
                    this.progressBar.setIndeterminate(false);
                }
                this.progressBar.setValue(this.hashFunctionWorker.getProgress());
            }

            if ("state".equals(event.getPropertyName())) {
                if (this.hashFunctionWorker.isDone()) {
                    try {
                        final HashFunctionResult result = this.hashFunctionWorker.get();
                        result.boxes().forEach(this.boxesPanel::add);
                    }
                    catch (final InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        this.hashFunctionWorker.execute();

        this.reRunButton.setEnabled(true);
        this.cancelButton.setEnabled(true);
    }

    @Override
    public void execute() {
        final var frame = new JFrame("SwingView");
        frame.setMinimumSize(new Dimension(720, 480));

        final var fileMenu = new JMenu("File");
        fileMenu.getAccessibleContext().setAccessibleName("File");
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "File menu permits to access to settings or exit the application.");

        final var languages = new JMenu("Language");
        final var languageGroup = new ButtonGroup();

        final var english = new JRadioButtonMenuItem("English");
        english.setSelected(true);
        english.setActionCommand("english");
        english.setEnabled(true);
        english.setOpaque(true);
        english.setName("english");
        english.setVisible(true);
        english.getAccessibleContext().setAccessibleName("English language");
        english.getAccessibleContext().setAccessibleDescription("Change the locale to the English language.");

        final var french = new JMenuItem("French");
        french.setSelected(false);
        french.setActionCommand("french");
        french.setEnabled(true);
        french.setOpaque(true);
        french.setName("french");
        french.setVisible(true);
        french.getAccessibleContext().setAccessibleName("French language");
        french.getAccessibleContext().setAccessibleDescription("Change the locale to the French language.");

        languageGroup.add(english);
        languageGroup.add(french);

        languages.add(english);
        languages.add(french);

        languages.getAccessibleContext().setAccessibleName("Language");
        languages.getAccessibleContext().setAccessibleDescription("Change the application language.");
        languages.setActionCommand("language");
        fileMenu.add(languages);


        fileMenu.addSeparator();

        final var quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        quit.getAccessibleContext().setAccessibleName("Quit");
        quit.getAccessibleContext().setAccessibleDescription("Quit the application.");
        quit.setActionCommand("quit");
        quit.addActionListener((final ActionEvent event) -> {
            if ("quit".equals(event.getActionCommand())) {
                frame.dispose();
            }
        });
        fileMenu.add(quit);

        final var helpMenu = new JMenu("?");
        helpMenu.getAccessibleContext().setAccessibleName("Help");
        helpMenu.getAccessibleContext().setAccessibleDescription(
                "Help menu permits accessing to quick help and about information");

        final var about = new JMenuItem("About");
        about.getAccessibleContext().setAccessibleName("About");
        about.getAccessibleContext().setAccessibleDescription("Show about information.");
        about.setEnabled(true);
        about.setOpaque(true);
        about.setName("about");
        about.setToolTipText("Show about information.");
        about.setHideActionText(false);
        about.setActionCommand("about");
        about.addActionListener((final ActionEvent event) -> {
            if ("about".equals(event.getActionCommand())) {
                final var aboutDialog = new JDialog(frame, "About", Dialog.ModalityType.APPLICATION_MODAL);
                aboutDialog.setVisible(true);
                aboutDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                aboutDialog.setAlwaysOnTop(true);
                aboutDialog.setFocusable(true);
                aboutDialog.setEnabled(true);
                aboutDialog.setResizable(false);
                aboutDialog.setSize(new Dimension(480, 360));
                aboutDialog.setMinimumSize(new Dimension(480, 360));
                aboutDialog.setMaximumSize(new Dimension(480, 360));

                final var title = new JTextArea("VisualHasher - v" + Configuration.VERSION);
                final var information = new JTextArea("Show the repartition of different hash functions for " + "customizable number of boxes and number of draws." + " ");
                final var license = new JTextArea("VisualHasher Copyright (C) 2022-2023 DAVID Axel - GPLv3");

                aboutDialog.add(title);
                aboutDialog.add(information);
                aboutDialog.add(license);
            }
        });

        helpMenu.add(about);

        final var actionMenu = new JMenu("Action");
        actionMenu.getAccessibleContext().setAccessibleName("Action");
        actionMenu.getAccessibleContext().setAccessibleDescription(
                "Action menu permits to run, cancel or re-run the computing of the hash function.");

        final var runButton = new JMenuItem("Run");
        runButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        runButton.setActionCommand("run");
        runButton.setEnabled(true);
        runButton.setToolTipText("Run the selected hash function.");
        runButton.setName("run");
        runButton.setVisible(true);
        runButton.setOpaque(true);
        runButton.setHideActionText(false);
        runButton.getAccessibleContext().setAccessibleName("Run");
        runButton.getAccessibleContext().setAccessibleDescription("Run the selected hash function.");

        final var reRunButton = new JMenuItem("Re-Run");
        reRunButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
        reRunButton.setActionCommand("re-run");
        reRunButton.setEnabled(false);
        reRunButton.setToolTipText("Cancel the computing hash function, and run the selected hash function.");
        reRunButton.setName("re-run");
        reRunButton.setVisible(true);
        reRunButton.setOpaque(true);
        reRunButton.setHideActionText(false);
        reRunButton.getAccessibleContext().setAccessibleName("Re-Run");
        reRunButton.getAccessibleContext().setAccessibleDescription(
                "Cancel the computing hash function, and run the selected hash function.");

        final var cancelButton = new JMenuItem("Cancel");
        cancelButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        cancelButton.setActionCommand("cancel");
        cancelButton.setEnabled(false);
        cancelButton.setToolTipText("Cancel the computing hash function.");
        cancelButton.setName("cancel");
        cancelButton.setVisible(true);
        cancelButton.setOpaque(true);
        cancelButton.setHideActionText(false);
        cancelButton.getAccessibleContext().setAccessibleName("Cancel");
        cancelButton.getAccessibleContext().setAccessibleDescription("Cancel the computing hash function.");

        actionMenu.add(runButton);
        actionMenu.add(reRunButton);
        actionMenu.add(cancelButton);

        final var menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(actionMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(this.getViewport());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Container getViewport() {
        return this.mainPanel;
    }
}
