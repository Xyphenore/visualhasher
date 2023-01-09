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

package com.adavid.visualhasher.presentation.components.boxes;

import com.adavid.visualhasher.domain.objects.Box;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

/**
 * The box panel.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BoxesPanel extends JScrollPane {
    private final DefaultListModel<Box> model = new DefaultListModel<>();
    private final JList<Box> boxes = new JList<>(this.model);

    /**
     * Create the default box panel.
     *
     * @since 1.0.0
     */
    public BoxesPanel() {
        super();
        super.setEnabled(true);
        super.setVisible(true);
        super.setName("boxesPanel");
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.boxes.setVisible(true);
        this.boxes.setEnabled(true);
        this.boxes.setOpaque(true);
        this.boxes.setCellRenderer(new ComputationBoxRenderer());
        this.boxes.setFixedCellHeight(100);
        this.boxes.setFixedCellWidth(100);
        this.boxes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        super.setViewportView(this.boxes);
        this.boxes.setVisibleRowCount(0);
    }

    public JList<Box> getBoxesList() {
        return this.boxes;
    }

    public void setCellRenderer(final ListCellRenderer<Box> cellRenderer) {
        this.boxes.setCellRenderer(cellRenderer);
    }

    public void addBox(final Box box) {
        final var goodBox = box;
        this.model.addElement(goodBox);
    }

    public void removeBox(final int index) {
        this.model.remove(index);
    }

    public void clear() {
        this.model.removeAllElements();
    }
}
