/*************************************************************************
 * Copyright (C) 2022 Vince Jerald Villamora @ https://vincejv.com       *
 *                                                                       *
 * Licensed under the Apache License, Version 2.0 (the "License");       *
 * you may not use this file except in compliance with the License.      *
 * You may obtain a copy of the License at                               *
 *                                                                       *
 *   http://www.apache.org/licenses/LICENSE-2.0                          *
 *                                                                       *
 * Unless required by applicable law or agreed to in writing, software   *
 * distributed under the License is distributed on an "AS IS" BASIS,     *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or       *
 * implied. See the License for the specific language governing          *
 * permissions and limitations under the License.                        *
 *************************************************************************/

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
