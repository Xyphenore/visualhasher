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

package com.adavid.visualhasher.presentation;

import com.adavid.visualhasher.domain.logics.AbstractHashFunctionWorker;
import com.adavid.visualhasher.domain.logics.ChainingHashFunctionWorker;
import com.adavid.visualhasher.domain.logics.DoubleChoiceHashFunctionWorker;
import com.adavid.visualhasher.domain.logics.HashFunctionIntermediaryResult;
import com.adavid.visualhasher.domain.logics.LinearOpenAddressingHashFunctionWorker;
import com.adavid.visualhasher.domain.utility.DrawsRange;
import com.adavid.visualhasher.presentation.components.action.bar.SwingActionBar;
import com.adavid.visualhasher.presentation.components.action.menu.SwingActionMenu;
import com.adavid.visualhasher.presentation.components.boxes.BoxesGeneratorWorker;
import com.adavid.visualhasher.presentation.components.boxes.BoxesPanel;
import com.adavid.visualhasher.presentation.components.boxes.ColoredBoxRenderer;
import com.adavid.visualhasher.presentation.components.boxes.ColoredBoxesGeneratorWorker;
import com.adavid.visualhasher.presentation.components.boxes.ComputationBoxRenderer;
import com.adavid.visualhasher.presentation.components.filemenu.SwingFileMenu;
import com.adavid.visualhasher.presentation.components.selectors.HashFunctionSelector;
import com.adavid.visualhasher.presentation.components.selectors.IllegalSelectedHashFunctionException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

/**
 * The user presentation, using Swing for the interface.
 *
 * @author Axel DAVID
 * @version 2.0.0
 * @since 1.0.0
 */
public final class SwingView {
    private static final String DEFAULT_TITLE = "VisualHasher";
    private static final Dimension MINIMUM_SIZE = new Dimension(900, 480);
    private static final Color SEPARATOR_COLOR = new Color(15, 23, 42);
    private static final String RUN_ACTION = "run";
    private static final String RE_RUN_ACTION = "re-run";
    private static final String CANCEL_ACTION = "cancel";
    private static final String QUIT_ACTION = "quit";
    private final JFrame frame;

    private final JTextArea information = new JTextArea();
    private final SwingActionMenu actionMenu = new SwingActionMenu();
    private final SwingActionBar actionBar = new SwingActionBar();
    private final BoxesPanel boxesPanel = new BoxesPanel();
    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private SwingWorker<Void, HashFunctionIntermediaryResult> hashFunctionWorker;
    private SwingWorker<Void, Integer> pregenerator;

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

        final var infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.information.setEditable(false);
        this.information.setText("Please launch a hash function.");
        this.information.setEnabled(true);
        this.information.setVisible(true);
        this.information.setFocusable(false);
        this.information.setRows(3);
        this.information.setLineWrap(true);
        this.information.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
        final var scrollInfo = new JScrollPane(this.information);
        scrollInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        infoPanel.add(scrollInfo, BorderLayout.CENTER);
        infoPanel.setMinimumSize(new Dimension(header.getWidth(), 75));
        header.add(infoPanel);

        final var mainLayout = new GridBagLayout();
        final var mainPanel = new JPanel(mainLayout);
        final var headerConstraints = new GridBagConstraints();
        headerConstraints.gridwidth = GridBagConstraints.REMAINDER;
        headerConstraints.gridheight = 1;
        headerConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainLayout.addLayoutComponent(header, headerConstraints);
        mainPanel.add(header);

        final var mainConstraints = new GridBagConstraints();
        mainConstraints.gridwidth = GridBagConstraints.REMAINDER;
        mainConstraints.gridheight = 2;
        mainConstraints.weighty = 2;
        mainConstraints.fill = GridBagConstraints.BOTH;
        mainLayout.addLayoutComponent(this.boxesPanel, mainConstraints);
        mainPanel.add(this.boxesPanel);

        final var footer = new JPanel();
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
        final var footerConstraints = new GridBagConstraints();
        footerConstraints.gridwidth = GridBagConstraints.REMAINDER;
        footerConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainLayout.addLayoutComponent(footer, footerConstraints);
        mainPanel.add(footer);

        final var menuBar = this.createMenuBar();

        this.frame.setJMenuBar(menuBar);
        this.frame.setContentPane(mainPanel);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.execute();
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

    private void execute() {
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     * Method executed when the event "run" is received.
     *
     * @see com.adavid.visualhasher.presentation.components.action.menu.RunItem
     * @see com.adavid.visualhasher.presentation.components.action.bar.RunButton
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
            case HashFunctionSelector.CHAINING_FUNCTION -> new ChainingHashFunctionWorker(boxesNumber,
                                                                                          drawsNumber,
                                                                                          this.information,
                                                                                          this.boxesPanel.getBoxesList()
            );
            case HashFunctionSelector.DOUBLE_CHOICES -> new DoubleChoiceHashFunctionWorker(boxesNumber,
                                                                                           drawsNumber,
                                                                                           this.information,
                                                                                           this.boxesPanel.getBoxesList()
            );
            case HashFunctionSelector.LINEAR_OPEN_ADDRESSING -> new LinearOpenAddressingHashFunctionWorker(boxesNumber,
                                                                                                           drawsNumber,
                                                                                                           this.information,
                                                                                                           this.boxesPanel.getBoxesList()
            );
            //            case HashFunctionSelector.QUADRATIC_OPEN_ADDRESSING -> new QuadraticOpenAddressingHashFunctionWorker(boxesNumber,
            //                                                                                                                 drawsNumber,
            //                                                                                                                 this.information,
            //                                                                                                                 this.boxesPanel.getBoxesList()
            //            );
            default -> throw new IllegalSelectedHashFunctionException(
                    "The selected hash function is invalid. The selected value : " + function);
        };

        this.boxesPanel.setCellRenderer(switch (function) {
            case HashFunctionSelector.CHAINING_FUNCTION, HashFunctionSelector.DOUBLE_CHOICES ->
                    new ComputationBoxRenderer();
            case HashFunctionSelector.LINEAR_OPEN_ADDRESSING, HashFunctionSelector.QUADRATIC_OPEN_ADDRESSING ->
                    new ColoredBoxRenderer();
            default -> throw new IllegalSelectedHashFunctionException(
                    "The selected hash function is invalid. The selected value : " + function);
        });

        this.pregenerator = switch (function) {
            case HashFunctionSelector.CHAINING_FUNCTION, HashFunctionSelector.DOUBLE_CHOICES ->
                    new BoxesGeneratorWorker(boxesNumber.getAsInt(), this.boxesPanel);
            case HashFunctionSelector.LINEAR_OPEN_ADDRESSING, HashFunctionSelector.QUADRATIC_OPEN_ADDRESSING ->
                    new ColoredBoxesGeneratorWorker(boxesNumber.getAsInt(), this.boxesPanel);
            default -> throw new IllegalSelectedHashFunctionException(
                    "The selected hash function is invalid. The selected value : " + function);
        };
        this.progressBar.setEnabled(true);
        this.progressBar.setValue(0);
        this.progressBar.setMaximum(100);
        this.progressBar.setIndeterminate(true);
        this.pregenerator.addPropertyChangeListener((final PropertyChangeEvent event) -> {
            if (null != this.pregenerator && !this.pregenerator.isCancelled()) {
                if ("progress".equals(event.getPropertyName())) {
                    if (this.progressBar.isIndeterminate()) {
                        this.progressBar.setIndeterminate(false);
                    }
                    this.progressBar.setValue(this.pregenerator.getProgress());
                }

                if ("state".equals(event.getPropertyName()) && !this.pregenerator.isCancelled() && this.pregenerator.isDone()) {
                    this.progressBar.setValue(0);
                    this.progressBar.setMaximum(100);
                    this.progressBar.setIndeterminate(true);
                    this.hashFunctionWorker.execute();
                }
            }
        });
        this.pregenerator.execute();

        this.hashFunctionWorker.addPropertyChangeListener((final PropertyChangeEvent event) -> {
            if (null != this.hashFunctionWorker && !this.hashFunctionWorker.isCancelled()) {
                if ("progress".equals(event.getPropertyName())) {
                    this.progressCallback();
                }

                if ("state".equals(event.getPropertyName()) && !this.hashFunctionWorker.isCancelled() && this.hashFunctionWorker.isDone()) {
                    this.doneCallback();
                }
            }
        });

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
     * @see AbstractHashFunctionWorker
     * @since 1.0.0
     */
    private void doneCallback() {

        //            final HashFunctionResult result = this.hashFunctionWorker.get();
        //
        //            result.boxes().forEach((final ComputationBox box) -> {
        //                if (box.isColored()) {
        //                    final var filled = box.getBalls() == 1;
        //                    final var color = box.isInTheFirstBox() ? ColoredBox.Color.GREEN : ColoredBox.Color.RED;
        //                    this.boxesPanel.addBox(new OpenAddessingBox(box.getID(), filled, color));
        //                }
        //                else {
        //                    this.boxesPanel.addBox(new NumberBox(box.getID(), box.getBalls()));
        //                }
        //            });
        //
        //            System.out.println(result.information());
        //            final class Output extends Thread {
        //                private final Collection<? extends Box> boxes;
        //
        //                private Output(final List<? extends Box> boxes) {
        //                    super();
        //                    this.boxes = new Vector<>(boxes);
        //
        //                }
        //
        //                @Override
        //                public void run() {
        //                    this.boxes.forEach(System.out::println);
        //                }
        //            }
        //            new Output(result.boxes()).start();

        this.disableReRunAction();
        this.disableCancelAction();
        this.enableRunAction();

        this.progressBar.setValue(0);
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
    }

    /**
     * Method executed when the event "re-run" is received.
     *
     * @see com.adavid.visualhasher.presentation.components.action.menu.ReRunItem
     * @see com.adavid.visualhasher.presentation.components.action.bar.ReRunButton
     * @since 1.0.0
     */
    private void reRunCallback() {
        if (null != this.pregenerator) {
            this.pregenerator.cancel(true);
            this.pregenerator = null;
            this.boxesPanel.clear();
        }

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

    /**
     * Method executed when the event "cancel" is received.
     *
     * @see com.adavid.visualhasher.presentation.components.action.menu.CancelItem
     * @see com.adavid.visualhasher.presentation.components.action.bar.CancelButton
     * @since 1.0.0
     */
    private void cancelCallback() {
        if (null != this.pregenerator) {
            this.pregenerator.cancel(true);
            this.pregenerator = null;
            this.boxesPanel.clear();
        }

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

        this.information.setText("Computation cancelled. Please run a hash function.");
    }
}
