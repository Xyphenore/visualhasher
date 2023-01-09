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

package com.adavid.visualhasher.presentation.components.selectors;

import com.adavid.visualhasher.domain.exceptions.BlankStringException;
import com.adavid.visualhasher.domain.exceptions.EmptyFunctionNamesListException;
import com.adavid.visualhasher.domain.exceptions.EmptyStringException;
import com.adavid.visualhasher.domain.exceptions.IllegalBoxesListException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Hash functions selector component. A JComboBox with String, which contains all hash functions of the application.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @since 1.0.0
 */
public final class HashFunctionSelector extends JComboBox<String> implements Selector<String> {
    // Hash functions
    /**
     * The chaining hash function. Draws a box number and increments the balls in.
     *
     * @since 1.0.0
     */
    public static final String CHAINING_FUNCTION = "Chaining Hash Function";

    /**
     * The double choices hash function. Draws two box numbers and increments the balls in the less filled box.
     *
     * @since 1.0.0
     */
    public static final String DOUBLE_CHOICES = "Double Choices Hash Function";

    /**
     * The linear open addressing hash function.
     * Draws a box number, if the box is empty, increments balls, else while the box is not empty, increments the
     * box number like: (box_number + 1) % number_of_boxes.
     * After the computing of the function, all box have 0 or 1 balls.
     *
     * @since 1.0.0
     */
    public static final String LINEAR_OPEN_ADDRESSING = "Linear Open Addressing Hash Function";

    /**
     * The linear open addressing hash function.
     * Draws a box number, if the box is empty, increments balls, else while the box is not empty, increments the
     * box number like: (box_number + n²) % number_of_boxes.
     * Where n
     * start from 1 to positive integer infinity.
     * After the computing of the function, all box have 0 or 1 balls.
     *
     * @since 1.0.0
     */
    public static final String QUADRATIC_OPEN_ADDRESSING = "Quadratic Open Addressing Hash Function";
    @Serial
    private static final long serialVersionUID = 9196033524365240482L;

    /**
     * Create the default hash functions selector.
     *
     * @since 1.0.0
     */
    public HashFunctionSelector() {
        this(HashFunctionSelector.generateDefaultHashFunctions());
    }

    /**
     * Create the hash functions selector with the collection of strings.
     *
     * @param functionNames Collection{@literal <}String{@literal >}.
     * List of hash functions, and it must not be empty.
     * The strings contained in the collection, must not be null, empty or blank.
     *
     * @since 1.0.0
     */
    public HashFunctionSelector(final Collection<String> functionNames) {
        super();

        try {
            if (functionNames.isEmpty()) {
                throw new EmptyFunctionNamesListException(
                        "The collection of function names is empty. Please give a filled collection.");
            }

            final MutableComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (final var name : functionNames) {
                final var notNullName = Objects.requireNonNull(name, "The name is null. Please give a filled string.");

                if (notNullName.isEmpty()) {
                    throw new EmptyStringException("The name is empty. Please give a filled name.");
                }

                if (notNullName.isBlank()) {
                    throw new BlankStringException("The name is blank. Please give a filled name.");
                }

                model.addElement(notNullName);
            }

            super.setModel(model);
        }
        catch (final RuntimeException error) {
            throw new IllegalBoxesListException("Cannot create a HashFunctionSelector with an invalid function names collection.",
                                                error
            );
        }

        super.setEditable(false);
        super.setEnabled(true);
        super.setName("hashFunctions");
        super.setFocusable(true);
        super.setVisible(true);
        super.setOpaque(true);
    }

    private static Collection<String> generateDefaultHashFunctions() {
        final Collection<String> defaultFunctions = new ArrayList<>(4);
        defaultFunctions.add(HashFunctionSelector.CHAINING_FUNCTION);
        defaultFunctions.add(HashFunctionSelector.DOUBLE_CHOICES);
        defaultFunctions.add(HashFunctionSelector.LINEAR_OPEN_ADDRESSING);
        defaultFunctions.add(HashFunctionSelector.QUADRATIC_OPEN_ADDRESSING);

        return defaultFunctions;
    }

    @Override
    public String getValue() {
        return (String) super.getSelectedItem();
    }
}
