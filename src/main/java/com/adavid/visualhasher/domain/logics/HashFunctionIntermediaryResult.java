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

package com.adavid.visualhasher.domain.logics;

import com.adavid.visualhasher.domain.exceptions.BlankStringException;
import com.adavid.visualhasher.domain.exceptions.EmptyStringException;
import com.adavid.visualhasher.domain.exceptions.IllegalBoxesListException;
import com.adavid.visualhasher.domain.exceptions.IllegalInformationException;
import com.adavid.visualhasher.domain.objects.Box;

public record HashFunctionIntermediaryResult(String information, Box box) {
    // TODO use a configuration file
    public static final String INVALID_INFORMATION = "Cannot create the HashFunctionIntermediaryResult the given information is invalid.";

    /**
     * Build an intermediary result of a hash function with information, and the computed box.
     *
     * @param information String. Information about box. The hash function can write anything in.
     * The string must not be empty and not filled with blank characters. The string must not be null.
     * @param box ComputationBox. Resulting box from the computing of the hash function.
     * The box must not be null.
     * <br><br>
     * <u><b>Developer's errors:</b></u>
     *
     * @throws IllegalInformationException Thrown if the information string is null, empty, or filled with blank
     * characters.
     * @throws IllegalBoxesListException Thrown if the list of box is null, empty or contains less than 2 box.
     * @since 1.0.0
     */
    public HashFunctionIntermediaryResult {
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

        if (null == box) {
            throw new IllegalBoxException(
                    "Cannot create the HashFunctionIntermediaryResult, the given box is invalid.",
                    new NullPointerException("The box is null. The box must not be null.")
            );
        }
    }
}
