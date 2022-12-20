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

package com.adavid.visualhasher.view.component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;
import java.util.ArrayList;

public class HashFunctionSelector extends JComboBox<String> {
    private static final Iterable<String> DEFAULT_FUNCTIONS = HashFunctionSelector.generateDefaultHashFunctions();

    public HashFunctionSelector() {
        this(HashFunctionSelector.DEFAULT_FUNCTIONS);
    }

    public HashFunctionSelector(final Iterable<String> functionNames) {
        super();

        final MutableComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (final var name : functionNames) {
            model.addElement(name);
        }

        super.setModel(model);
    }

    private static ArrayList<String> generateDefaultHashFunctions() {
        final var defaultFunctions = new ArrayList<String>(4);
        defaultFunctions.add("Chaining Hash Function");
        defaultFunctions.add("Double Choices Hash Function");
        defaultFunctions.add("Linear Open Addressing Hash Function");
        defaultFunctions.add("Quadratic Open Addressing Hash Function");

        return defaultFunctions;
    }
}
