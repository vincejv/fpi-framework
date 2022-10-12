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

package com.abavilla.fpi.fw.dto;

import java.io.Serializable;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Data transfer object template for which all DTO objects inherit, contains the basic common functionality and fields
 * that a DTO may optionally use.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IDto extends Serializable {

  /**
   * Converts the DTO into a generic {@link JsonNode} object for displaying JSON and operations, converts the DTO using
   * {@link MapperUtil} utility methods.
   *
   * @return the JSON object
   */
  JsonNode toJson();

  String toJsonStr();
}
