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

package com.adavid.visualhasher.view;

public interface Constants {
    int MIN_BOXES = 2;
    int MAX_BOXES = 10000;
    int STEP_BOXES = 1;

    int MIN_DRAWS = Constants.MAX_BOXES / 2;
    int MAX_DRAWS = Constants.MAX_BOXES;
    int STEP_DRAWS = 1;

    String CHAINING_FUNCTION = "Chaining Hash Function";
    String DOUBLE_CHOICES = "Double Choices Hash Function";
    String LINEAR_OPEN_ADDRESSING = "Linear Open Addressing Hash Function";
    String QUADRATIC_OPEN_ADDRESSING = "Quadratic Open Addressing Hash Function";
}
