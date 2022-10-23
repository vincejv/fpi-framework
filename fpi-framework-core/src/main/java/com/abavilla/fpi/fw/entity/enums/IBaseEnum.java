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

package com.abavilla.fpi.fw.entity.enums;

import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

/**
 * Base implementation of {@link Enum} types
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@RegisterForReflection
public interface IBaseEnum {

  /**
   * Creates an enum based from given string value, returns the default enum value if value does not map
   *
   * @param value the string value
   * @param enumMap id to enum mapping
   * @param unknownVal default enum value
   * @return the created enum
   */
  static IBaseEnum fromValue(String value, Map<Integer, IBaseEnum> enumMap, IBaseEnum unknownVal) {
    if (StringUtils.isBlank(value)) {
      return null;
    } else {
      return enumMap.values().stream().filter(enumItem ->
          StringUtils.equalsIgnoreCase(value, enumItem.getValue())).findAny()
        .orElse(unknownVal);
    }
  }

  /**
   * Creates an enum based from given an ordinal id, returns the default enum value if id does not map
   *
   * @param id the ordinal id
   * @param enumMap id to enum mapping
   * @param unknownVal default enum value
   * @return the created enum
   */
  static IBaseEnum fromId(int id, Map<Integer, IBaseEnum> enumMap, IBaseEnum unknownVal) {
    return enumMap.values().stream().filter(enumItem -> id == enumItem.getId()).findAny()
      .orElse(unknownVal);
  }

  /**
   * Gets the value
   *
   * @return the value
   */
  default String getValue() {
    throw new NotImplementedException();
  }

  /**
   * Gets the id
   *
   * @return the id
   */
  default int getId() {
    throw new NotImplementedException();
  }

}
