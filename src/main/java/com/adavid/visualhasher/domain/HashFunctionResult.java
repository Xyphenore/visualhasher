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

package com.adavid.visualhasher.domain;

import com.adavid.visualhasher.domain.exceptions.BlankStringException;
import com.adavid.visualhasher.domain.exceptions.EmptyBoxesListException;
import com.adavid.visualhasher.domain.exceptions.EmptyStringException;
import com.adavid.visualhasher.domain.exceptions.IllegalBoxesListException;
import com.adavid.visualhasher.domain.exceptions.IllegalInformationException;
import com.adavid.visualhasher.presentation.views.SwingView;
import com.adavid.visualhasher.presentation.views.components.boxes.NumberBox;

import java.util.List;

/**
 * Result of a hash function worker, during it process and when it is done.
 *
 * @param information String. Information about boxes. The hash function can write anything in.
 * The string must not be empty and not filled with blank characters. The string must not be null.
 * @param boxes List{@literal <}NumberBox{@literal >}. Resulting boxes from the computing of the hash function.
 * The list must not be null.
 *
 * @author Axel DAVID
 * @version 1.0.0
 *
 * <br><br>
 * <u><b>Examples:</b></u><br>
 * <pre>
 * {@code
 * // Example: how to create a HashFunctionResult
 *
 * // Create a string test
 * String test = new String("TEST");
 *
 * // Create a basic list of boxes
 * List<NumberBox> boxes = new ArrayList<>();
 * // Create a box with 10 balls in it.
 * NumberBox box = new NumberBox(10);
 * // Add the box to the list
 * boxes.add(box);
 *
 * // Create a HashFunctionResult
 * HashFunctionResult result = new HashFunctionResult(test, boxes);
 *
 *
 * // Example: how to get information
 * // Information getter
 * resultInformation = result.information;
 * assert resultInformation.equals(test);
 *
 *
 * // Example: how to get boxes
 * // Boxes getter
 * resultBoxes = result.boxes;
 * assert resultBoxes.size() == boxes.size();
 * assert resultBoxes.equals(boxes);
 * }
 * </pre>
 * @see AbstractHashFunctionWorker
 * @see NumberBox
 * @see SwingView
 * @since 1.0.0
 */
public record HashFunctionResult(String information, List<ComputedBox> boxes) {
    // TODO use a configuration file
    public static final String INVALID_INFORMATION = "Cannot create the HashFunctionResult the given information is invalid.";
    public static final String INVALID_BOXES_LIST = "Cannot create the HashFunctionResult, the given list of boxes is invalid.";

    /**
     * Build a result of a hash function with information, and the list of result.
     *
     * @param information String. Information about boxes. The hash function can write anything in.
     * The string must not be empty and not filled with blank characters. The string must not be null.
     * @param boxes List{@literal <}NumberBox{@literal >}. Resulting boxes from the computing of the hash function.
     * The list must not be null.
     * <br><br>
     * <u><b>Developer's errors:</b></u>
     *
     * @throws IllegalInformationException Thrown if the information string is null, empty, or filled with blank
     * characters.
     * @throws IllegalBoxesListException Thrown if the list of boxes is null, empty or contains less than 2 boxes.
     * @since 1.0.0
     */
    public HashFunctionResult {
        if (null == information) {
            throw new IllegalInformationException(
                    INVALID_INFORMATION,
                    new NullPointerException("The string 'information' is null. The string must not be null.")
            );
        }

        if (information.isEmpty()) {
            throw new IllegalInformationException(
                    INVALID_INFORMATION,
                    new EmptyStringException("The string 'information' is empty. The string must not be empty.")
            );
        }

        if (information.isBlank()) {
            throw new IllegalInformationException(
                    INVALID_INFORMATION,
                    new BlankStringException(
                            "The string 'information' is filled with blanked characters. The string must be filled with not blank characters.")
            );
        }

        if (null == boxes) {
            throw new IllegalBoxesListException(
                    INVALID_BOXES_LIST,
                    new NullPointerException("The list of boxes is null. The list must not be null.")
            );
        }

        if (boxes.isEmpty()) {
            throw new IllegalBoxesListException(
                    INVALID_BOXES_LIST,
                    new EmptyBoxesListException("The list of boxes is empty. The list must not be empty.")
            );
        }

        if (2 > boxes.size()) {
            throw new IllegalBoxesListException(INVALID_BOXES_LIST + " The list of boxes has only " + boxes.size() + " boxes. The list of boxes must have 2 or more boxes.");
        }
    }
}
