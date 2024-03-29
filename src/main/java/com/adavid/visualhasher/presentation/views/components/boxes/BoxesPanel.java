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

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * The boxes panel.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BoxesPanel extends JScrollPane {
    private final DefaultListModel<CommonBaseBox> model = new DefaultListModel<>();
    private final JList<CommonBaseBox> boxes = new JList<>(this.model);

    /**
     * Create the default boxes panel.
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
        super.setViewportView(this.boxes);
        super.add(this.boxes);
        this.boxes.setVisibleRowCount(0);
    }

    public JList<CommonBaseBox> getBoxesList() {
        return this.boxes;
    }

    public void addBox(final CommonBaseBox box) {
        final var goodBox = (CommonBaseBox) box;
        this.model.addElement(goodBox);
    }

    public void clear() {
        this.model.clear();
    }
}
