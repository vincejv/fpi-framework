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

package com.abavilla.fpi.fw.mapper;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.IItem;
import org.bson.types.ObjectId;

public interface IMapper<DTO extends IDto, ENTITY extends IItem> {
  DTO mapToDto(ENTITY entity);
  ENTITY mapToEntity(DTO dto);

  /**
   * Parses the hex formatted object id string to {@link ObjectId}
   *
   * @param id Hex Formatted String
   * @return {@link ObjectId} id
   */
  default ObjectId strToObjId(String id) {
    return new ObjectId(id);
  }

  /**
   * Gets the hex formatted string from {@link ObjectId}
   *
   * @param id {@link ObjectId} id
   * @return Hex formatted id
   */
  default String objIdToStr(ObjectId id) {
    return id.toHexString();
  }
}
