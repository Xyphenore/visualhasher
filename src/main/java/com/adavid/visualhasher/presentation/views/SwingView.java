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

package com.adavid.visualhasher.presentation.views;

import com.adavid.visualhasher.domain.AbstractHashFunctionWorker;
import com.adavid.visualhasher.domain.Box;
import com.adavid.visualhasher.domain.ChainingHashFunctionWorker;
import com.adavid.visualhasher.domain.ComputedBox;
import com.adavid.visualhasher.domain.DoubleChoiceHashFunctionWorker;
import com.adavid.visualhasher.domain.HashFunctionResult;
import com.adavid.visualhasher.domain.LinearOpenAddressingHashFunctionWorker;
import com.adavid.visualhasher.domain.QuadraticOpenAddressingHashFunctionWorker;
import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.presentation.views.components.action.bar.SwingActionBar;
import com.adavid.visualhasher.presentation.views.components.action.menu.SwingActionMenu;
import com.adavid.visualhasher.presentation.views.components.boxes.BoxesPanel;
import com.adavid.visualhasher.presentation.views.components.boxes.ColoredBox;
import com.adavid.visualhasher.presentation.views.components.boxes.NumberBox;
import com.adavid.visualhasher.presentation.views.components.boxes.OpenAddessingBox;
import com.adavid.visualhasher.presentation.views.components.filemenu.SwingFileMenu;
import com.adavid.visualhasher.presentation.views.components.selectors.HashFunctionSelector;
import com.adavid.visualhasher.presentation.views.components.selectors.IllegalSelectedHashFunctionException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
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
    private static final Dimension MINIMUM_SIZE = new Dimension(900, 480);
    private static final Color SEPARATOR_COLOR = new Color(15, 23, 42);
    private static final String RUN_ACTION = "run";
    private static final String RE_RUN_ACTION = "re-run";
    private static final String CANCEL_ACTION = "cancel";
    private static final String QUIT_ACTION = "quit";
    private final JFrame frame;

    private final SwingActionMenu actionMenu = new SwingActionMenu();
    private final SwingActionBar actionBar = new SwingActionBar();
    private final BoxesPanel boxesPanel = new BoxesPanel();
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

    /**
     * Create the SwingView with the specified title.
     *
     * @param title String. The title of the view.
     *
     * @since 1.0.0
     */
    public SwingView(final String title) {
        super();

        this.frame = new JFrame(Objects.requireNonNull(title, "The title is null. Please give a title not null."));
        this.frame.setMinimumSize(SwingView.MINIMUM_SIZE);
        this.frame.setLocationRelativeTo(null);


        this.actionBar.getDrawsSelector().setInterval(new DrawsRange(this.actionBar.getNumberOfBoxes()));

        this.initializeActionItemListeners();

        final var header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.PAGE_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        this.actionBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SwingView.SEPARATOR_COLOR));

        header.add(this.actionBar);
        header.add(this.progressBar);

        this.progressBar.setEnabled(false);
        this.progressBar.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, SwingView.SEPARATOR_COLOR));
        this.progressBar.setStringPainted(true);
        this.progressBar.setVisible(false);

        final var mainLayout = new GridBagLayout();
        final var mainPanel = new JPanel(mainLayout);
        final var headerConstraints = new GridBagConstraints();
        headerConstraints.gridwidth = GridBagConstraints.REMAINDER;
        headerConstraints.gridheight = 1;
        headerConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainLayout.addLayoutComponent(header, headerConstraints);
        mainPanel.add(header);

        // TODO Add the text area

        final var mainConstraints = new GridBagConstraints();
        mainConstraints.gridwidth = GridBagConstraints.REMAINDER;
        mainConstraints.gridheight = 2;
        mainConstraints.weighty = 1;
        mainConstraints.fill = GridBagConstraints.BOTH;
        mainLayout.addLayoutComponent(this.boxesPanel, mainConstraints);
        mainPanel.add(this.boxesPanel);

        final var menuBar = this.createMenuBar();

        this.frame.setJMenuBar(menuBar);
        this.frame.setContentPane(mainPanel);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JMenuBar createMenuBar() {
        final var fileMenu = new SwingFileMenu();
        fileMenu.getQuit().addActionListener((final ActionEvent event) -> {
            if (SwingView.QUIT_ACTION.equals(event.getActionCommand())) {
                this.frame.dispose();
            }
        });

        final var menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(this.actionMenu);

        return menuBar;
    }

    /**
     * Create listeners on all component action.
     *
     * @since 1.0.0
     */
    private void initializeActionItemListeners() {
        this.actionBar.getRunButton().addActionListener((final ActionEvent event) -> {
            if (SwingView.RUN_ACTION.equals(event.getActionCommand())) {
                this.runCallback();
            }
        });
        this.actionBar.getReRunButton().addActionListener((final ActionEvent event) -> {
            if (SwingView.RE_RUN_ACTION.equals(event.getActionCommand())) {
                this.reRunCallback();
            }
        });
        this.actionBar.getCancelButton().addActionListener((final ActionEvent event) -> {
            if (SwingView.CANCEL_ACTION.equals(event.getActionCommand())) {
                this.cancelCallback();
            }
        });

        this.actionMenu.getRunItem().addActionListener((final ActionEvent event) -> {
            if (SwingView.RUN_ACTION.equals(event.getActionCommand())) {
                this.runCallback();
            }
        });
        this.actionMenu.getReRunItem().addActionListener((final ActionEvent event) -> {
            if (SwingView.RE_RUN_ACTION.equals(event.getActionCommand())) {
                this.reRunCallback();
            }
        });
        this.actionMenu.getCancelItem().addActionListener((final ActionEvent event) -> {
            if (SwingView.CANCEL_ACTION.equals(event.getActionCommand())) {
                this.cancelCallback();
            }
        });
    }

    /**
     * Method executed when the event "run" is received.
     *
     * @see com.adavid.visualhasher.presentation.views.components.action.menu.RunItem
     * @see com.adavid.visualhasher.presentation.views.components.action.bar.RunButton
     * @since 1.0.0
     */
    private void runCallback() {
        this.disableRunAction();
        this.enableReRunAction();
        this.enableCancelAction();

        this.actionBar.getReRunButton().setEnabled(false);
        this.actionBar.getCancelButton().setEnabled(false);
        this.actionMenu.getReRunItem().setEnabled(false);
        this.actionMenu.getCancelItem().setEnabled(false);

        this.progressBar.setVisible(true);
        this.progressBar.setEnabled(true);

        this.runAction();
    }

    /**
     * Method executed when the event "re-run" is received.
     *
     * @see com.adavid.visualhasher.presentation.views.components.action.menu.ReRunItem
     * @see com.adavid.visualhasher.presentation.views.components.action.bar.ReRunButton
     * @since 1.0.0
     */
    private void reRunCallback() {
        if (null != this.hashFunctionWorker) {
            this.hashFunctionWorker.cancel(true);
            this.hashFunctionWorker = null;
        }

        this.actionBar.getReRunButton().setEnabled(false);
        this.actionBar.getCancelButton().setEnabled(false);

        this.actionMenu.getReRunItem().setEnabled(false);
        this.actionMenu.getCancelItem().setEnabled(false);

        this.runAction();
    }

    /**
     * All actions need to run the selected hash function.
     *
     * @since 1.0.0
     */
    private void runAction() {
        this.progressBar.setIndeterminate(true);
        this.progressBar.setValue(0);

        final var boxesNumber = this.actionBar.getNumberOfBoxes();
        final int drawsNumber = this.actionBar.getNumberOfDraws();
        final var function = Objects.requireNonNull(this.actionBar.getSelectedHashFunction(),
                                                    "The selected hash function name is null."
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
                    "The selected hash function is invalid. The selected value : " + function);
        };
        this.hashFunctionWorker.addPropertyChangeListener((final PropertyChangeEvent event) -> {
            if ("progress".equals(event.getPropertyName())) {
                this.progressCallback();
            }

            if ("state".equals(event.getPropertyName()) && !this.hashFunctionWorker.isCancelled() && this.hashFunctionWorker.isDone()) {
                this.doneCallback();
            }
        });
        this.hashFunctionWorker.execute();

        this.actionBar.getReRunButton().setEnabled(true);
        this.actionBar.getCancelButton().setEnabled(true);
        this.actionMenu.getCancelItem().setEnabled(true);
        this.actionMenu.getReRunItem().setEnabled(true);
    }

    /**
     * Method executed on the event "progress" from the Hash Function Worker.
     *
     * @see AbstractHashFunctionWorker
     * @since 1.0.0
     */
    private void progressCallback() {
        if (this.progressBar.isIndeterminate()) {
            this.progressBar.setIndeterminate(false);
        }
        this.progressBar.setValue(this.hashFunctionWorker.getProgress());
    }

    /**
     * Method executed when the hash function worker done it compute.
     *
     * @see com.adavid.visualhasher.domain.AbstractHashFunctionWorker
     * @since 1.0.0
     */
    private void doneCallback() {
        try {
            final HashFunctionResult result = this.hashFunctionWorker.get();

            result.boxes().forEach((final ComputedBox box) -> {
                if (box.isColored()) {
                    final var filled = box.getBalls() == 1;
                    final var color = box.isInTheFirstBox() ? ColoredBox.Color.GREEN : ColoredBox.Color.RED;
                    this.boxesPanel.addBox(new OpenAddessingBox(box.getID(), filled, color));
                }
                else {
                    this.boxesPanel.addBox(new NumberBox(box.getID(), box.getBalls()));
                }
            });

            System.out.println(result.information());
            final class Output extends Thread {
                private final Collection<? extends Box> boxes;

                private Output(final List<? extends Box> boxes) {
                    super();
                    this.boxes = new Vector<>(boxes);

                }

                @Override
                public void run() {
                    this.boxes.parallelStream().forEach(System.out::println);
                }
            }
            new Output(result.boxes()).start();

            final var box = result.boxes().get(0);
            //                        this.boxesPanel.add(box);

            this.disableReRunAction();
            this.disableCancelAction();
            this.enableRunAction();

            this.progressBar.setValue(0);
            this.progressBar.setEnabled(false);
            this.progressBar.setVisible(false);
        }
        catch (final InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method executed when the event "cancel" is received.
     *
     * @see com.adavid.visualhasher.presentation.views.components.action.menu.CancelItem
     * @see com.adavid.visualhasher.presentation.views.components.action.bar.CancelButton
     * @since 1.0.0
     */
    private void cancelCallback() {
        if (null != this.hashFunctionWorker) {
            this.hashFunctionWorker.cancel(true);
            this.hashFunctionWorker = null;
        }

        this.disableReRunAction();
        this.disableCancelAction();
        this.enableRunAction();

        this.progressBar.setValue(0);
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
    }

    private void disableRunAction() {
        final var runButton = this.actionBar.getRunButton();
        runButton.setEnabled(false);
        runButton.setVisible(false);
        runButton.setFocusable(false);

        final var runItem = this.actionMenu.getRunItem();
        runItem.setEnabled(false);
        runItem.setFocusable(false);
    }

    private void enableRunAction() {
        final var runButton = this.actionBar.getRunButton();
        runButton.setEnabled(true);
        runButton.setVisible(true);
        runButton.setFocusable(true);

        final var runItem = this.actionMenu.getRunItem();
        runItem.setEnabled(true);
        runItem.setFocusable(true);
    }

    private void disableReRunAction() {
        final var reRunButton = this.actionBar.getReRunButton();
        reRunButton.setEnabled(false);
        reRunButton.setVisible(false);
        reRunButton.setFocusable(false);

        final var reRunItem = this.actionMenu.getReRunItem();
        reRunItem.setEnabled(false);
        reRunItem.setFocusable(false);
    }

    private void enableReRunAction() {
        final var reRunButton = this.actionBar.getReRunButton();
        reRunButton.setVisible(true);
        reRunButton.setEnabled(true);
        reRunButton.setFocusable(true);

        final var reRunItem = this.actionMenu.getReRunItem();
        reRunItem.setEnabled(true);
        reRunItem.setFocusable(true);
    }

    private void disableCancelAction() {
        final var cancelButton = this.actionBar.getCancelButton();
        cancelButton.setEnabled(false);
        cancelButton.setVisible(false);
        cancelButton.setFocusable(false);

        final var cancelItem = this.actionMenu.getCancelItem();
        cancelItem.setEnabled(false);
        cancelItem.setFocusable(false);
    }

    private void enableCancelAction() {
        final var cancelButton = this.actionBar.getCancelButton();
        cancelButton.setVisible(true);
        cancelButton.setFocusable(true);
        cancelButton.setEnabled(true);

        final var cancelItem = this.actionMenu.getCancelItem();
        cancelItem.setFocusable(true);
        cancelItem.setEnabled(true);
    }


    @Override
    public void execute() {
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
