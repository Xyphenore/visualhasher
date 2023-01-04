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

import com.adavid.visualhasher.infrastructure.Configuration;
import com.adavid.visualhasher.presentation.views.components.futures.helpmenu.AboutMenuItem;
import com.adavid.visualhasher.presentation.views.components.futures.helpmenu.HelpMenu;
import com.adavid.visualhasher.presentation.views.components.futures.locales.LocaleMenu;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Objects;

class FutureView {
    /**
     * For the v1.1.0
     *
     * @param fileMenu JMenuBar. The file menu.
     *
     * @implNote For v1.1.0
     * @since 1.1.0
     */
    private static void addLanguageMenu(final JMenu fileMenu) {
        final var notNullMenu = Objects.requireNonNull(fileMenu, "The file menu is null. Please give a menu not null.");

        final var languages = new LocaleMenu();
        notNullMenu.add(languages);

        notNullMenu.addSeparator();
    }

    /**
     * For the v1.1.0
     *
     * @param bar JMenuBar. The menu bar.
     * @param frame JFrame.
     *
     * @implNote For v1.1.0
     * @since 1.1.0
     */
    private static void addHelpMenu(final JMenuBar bar, final JFrame frame) {
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
                final var dim = new Dimension(480, 360);
                aboutDialog.setSize(dim);
                aboutDialog.setMinimumSize(dim);
                aboutDialog.setMaximumSize(dim);

                final var title = new JTextArea("VisualHasher – v" + Configuration.VERSION);
                final var information = new JTextArea(
                        "Show the repartition of different hash functions for a customizable number of boxes and number of draws.");
                final var license = new JTextArea("VisualHasher Copyright (C) 2022-2023 DAVID Axel - GPLv3");

                aboutDialog.add(title);
                aboutDialog.add(information);
                aboutDialog.add(license);
            }
        });
        helpMenu.add(about);

        notNullMenu.add(helpMenu);
    }
}
