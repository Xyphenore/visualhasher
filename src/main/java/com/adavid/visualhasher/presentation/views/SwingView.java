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
import com.adavid.visualhasher.presentation.views.components.ActionMenu;
import com.adavid.visualhasher.presentation.views.components.Box;
import com.adavid.visualhasher.presentation.views.components.BoxesSpinner;
import com.adavid.visualhasher.presentation.views.components.CancelItem;
import com.adavid.visualhasher.presentation.views.components.DrawsSpinner;
import com.adavid.visualhasher.presentation.views.components.FileMenu;
import com.adavid.visualhasher.presentation.views.components.HashFunctionSelector;
import com.adavid.visualhasher.presentation.views.components.QuitMenuItem;
import com.adavid.visualhasher.presentation.views.components.ReRunItem;
import com.adavid.visualhasher.presentation.views.components.RunItem;
import com.adavid.visualhasher.presentation.views.components.futures.AboutMenuItem;
import com.adavid.visualhasher.presentation.views.components.futures.HelpMenu;
import com.adavid.visualhasher.presentation.views.components.futures.LocaleMenu;
import com.adavid.visualhasher.presentation.views.exceptions.IllegalSelectedHashFunctionException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
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
    private final JFrame frame;
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

    /**
     * Create the SwingView with the default name.
     *
     * @since 1.0.0
     */
    public SwingView() {
        this(SwingView.DEFAULT_TITLE);
    }

    public SwingView(final String title) {
        super();

        final var finalTitle = Objects.requireNonNull(title, "The title is null. Please give a title not null.");

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

        this.frame = new JFrame(finalTitle);
        this.frame.setMinimumSize(new Dimension(720, 480));

        final var quit = new QuitMenuItem();
        quit.addActionListener((final ActionEvent event) -> {
            if ("quit".equals(event.getActionCommand())) {
                this.frame.dispose();
            }
        });

        final var fileMenu = new FileMenu();
        fileMenu.add(quit);

        final var runButton = new RunItem();
        final var reRunButton = new ReRunItem();
        final var cancelButton = new CancelItem();

        final var actionMenu = new ActionMenu();
        actionMenu.add(runButton);
        actionMenu.add(reRunButton);
        actionMenu.add(cancelButton);

        final var menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(actionMenu);

        this.frame.setJMenuBar(menuBar);
        this.frame.setContentPane(this.getViewport());
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void runAction() {
        this.progressBar.setIndeterminate(true);
        this.progressBar.setValue(0);

        final var boxesNumber = new NumberOfBoxes(this.boxesNb.getValue());
        final var drawsNumber = (int) this.drawsNb.getValue();
        final var function = (String) Objects.requireNonNull(this.hashFunction.getSelectedItem(),
                                                             "Selected hash function name is null."
                                                            );

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
                        // TODO Ajouter les boxes à l'écran

                        //                        result.boxes().forEach(this.boxesPanel::add);

                        System.out.println(result.information());
                        class Output extends Thread {
                            private final Vector<Box> boxes;

                            public Output(final List<Box> boxes) {
                                this.boxes = new Vector<>(boxes);

                            }

                            public void run() {
                                this.boxes.parallelStream().forEach(System.out::println);
                            }
                        }
                        //                        result.boxes().parallelStream().forEach(System.out::println);
                        new Output(result.boxes()).run();

                        final var box = result.boxes().get(0);
                        this.boxesPanel.add(box);

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

    private Container getViewport() {
        return this.mainPanel;
    }

    /**
     * For the v1.1.0
     *
     * @param fileMenu JMenuBar. The file menu.
     *
     * @implNote For v1.1.0
     * @since 1.1.0
     */
    private void addLanguageMenu(final JMenu fileMenu) {
        final var notNullMenu = Objects.requireNonNull(fileMenu, "The file menu is null. Please give a menu not null.");

        final var languages = new LocaleMenu();
        notNullMenu.add(languages);

        notNullMenu.addSeparator();
    }

    /**
     * For the v1.1.0
     *
     * @param bar JMenuBar. The menu bar.
     * @param frame JFrame. The frame where the about dialog was open.
     *
     * @implNote For v1.1.0
     * @since 1.1.0
     */
    private void addHelpMenu(final JMenuBar bar, final JFrame frame) {
        final var notNullMenu = Objects.requireNonNull(bar, "The menu bar is null. Please give a menu not null.");
        final var notNullFrame = Objects.requireNonNull(frame, "The frame is null. Please give a frame not null.");


        final var helpMenu = new HelpMenu();
        final var about = new AboutMenuItem();
        about.addActionListener((final ActionEvent event) -> {
            if ("about".equals(event.getActionCommand())) {
                final var aboutDialog = new JDialog(notNullFrame, "About", Dialog.ModalityType.APPLICATION_MODAL);
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

        notNullMenu.add(helpMenu);
    }

    @Override
    public void execute() {
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
