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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.Serial;

public final class ComputationBoxRenderer extends JPanel implements ListCellRenderer<Box> {
    private static final Color BG_CONTENT = new Color(224, 224, 224);
    @Serial
    private static final long serialVersionUID = 6964001316717728032L;

    private final JLabel header = new JLabel();
    private final JLabel content = new JLabel();
    private final JLabel footer = new JLabel();
    private boolean init = true;

    public ComputationBoxRenderer() {
        super();
    }

    @Override
    public Component getListCellRendererComponent(final JList<? extends Box> list, final Box value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final var balls = value.getBalls();

        if (this.init) {
            this.init = false;
            this.setLayout(new BorderLayout());

            this.header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            this.content.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            this.footer.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            this.header.setHorizontalAlignment(SwingConstants.CENTER);
            this.footer.setHorizontalAlignment(SwingConstants.CENTER);
            this.content.setHorizontalAlignment(SwingConstants.CENTER);

            this.header.setOpaque(true);
            this.header.setBackground(Color.LIGHT_GRAY);
            this.footer.setOpaque(true);
            this.footer.setBackground(Color.LIGHT_GRAY);
            this.content.setBackground(ComputationBoxRenderer.BG_CONTENT);
            this.content.setOpaque(true);

            this.add(this.header, BorderLayout.PAGE_START);
            this.add(this.content, BorderLayout.CENTER);
            this.add(this.footer, BorderLayout.PAGE_END);

            this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        }

        this.header.setText(String.valueOf(value.getID()));
        this.footer.setText("Balls: " + balls);
        this.content.setText("\u2B24".repeat(Math.max(0, balls)));
        if ("".equals(this.content.getText())) {
            this.content.setText("NOTHING");
        }

        return this;
    }
}
