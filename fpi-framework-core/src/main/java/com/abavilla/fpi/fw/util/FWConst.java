/******************************************************************************
 * FPI Application - Abavilla                                                 *
 * Copyright (C) 2022  Vince Jerald Villamora                                 *
 *                                                                            *
 * This program is free software: you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, either version 3 of the License, or          *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.     *
 ******************************************************************************/

package com.abavilla.fpi.fw.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * FPI Framework constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FWConst {

  /**
   * Comma separator
   */
  public static final String COMMA_SEP = ", ";

  /**
   * Success message used for API response
   */
  public static final String SUCCESS = "Success";

  /**
   * Failed message used for API response
   */
  public static final String FAILED = "Failed";
  public static final String CANNOT_FIND_ERR_MSG = "Cannot find ";

  /**
   * Prefix for Unknown enum values
   */
  public static final String UNKNOWN_PREFIX = "UNK >> ";

}
