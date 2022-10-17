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

package com.abavilla.fpi.fw.mapper;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

/**
 * Common mapper contains the commonly used data types across all mapstruct mappers.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IMapper {

  /**
   * Converts hex encoded string to {@link ObjectId} for dto.
   *
   * @param id Hex encoded {@link ObjectId}
   * @return {@link ObjectId} object
   */
  default ObjectId strToMongoId(String id) {
    if (StringUtils.isNotBlank(id) &&
        ObjectId.isValid(id)) {
      return new ObjectId(id);
    } else {
      return null;
    }
  }

  /**
   * Converts mongo db {@link ObjectId} to hex encoded string for entity.
   *
   * @param id {@link ObjectId} object
   * @return Hex encoded id
   */
  default String mongoIdToStr(ObjectId id) {
    if (id != null) {
      return id.toHexString();
    } else {
      return null;
    }
  }
}
