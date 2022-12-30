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

package com.adavid.visualhasher.domain.exceptions;

import com.adavid.visualhasher.domain.HashFunctionResult;

/**
 * Error message constants used by exceptions.
 *
 * @author Axel DAVID
 * @version 1.0.0
 * @see HashFunctionResult
 * @see com.adavid.visualhasher.domain.exceptions
 * @since 1.0.0
 */
public interface ErrorMessage {
    // TODO use a configuration file
    String INVALID_INFORMATION = "Cannot create the HashFunctionResult the given information is invalid.";
    String INVALID_BOXES_LIST = "Cannot create the HashFunctionResult, the given list of boxes is invalid.";
}
