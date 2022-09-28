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

import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.entity.IItem;
import com.abavilla.fpi.fw.entity.mongo.AbsMongoItem;
import org.bson.types.ObjectId;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface IMongoItemMapper<DTO extends AbsDto, ENTITY extends AbsMongoItem> extends IMapper<DTO, ENTITY> {

  @Override
  @Mappings(value = {
      @Mapping(target = "id", ignore = true)
  })
  DTO mapToDto(ENTITY entity);

  @Override
  @Mappings(value = {
      @Mapping(target = "id", ignore = true)
  })
  ENTITY mapToEntity(DTO dto);

  /**
   * {@inheritDoc}
   */
  @Override
  @Mappings(value = {
      @Mapping(target = "id", ignore = true)
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void patchEntity(@MappingTarget ENTITY entity, DTO dto);

  /**
   * Converts mongo db {@link ObjectId} to hex string for {@link DTO}.
   *
   * @param entity Source entity
   * @param dto Target dto
   */
  @BeforeMapping
  default void mapMongoIdToDto(@MappingTarget DTO dto, ENTITY entity) {
    dto.setId(entity.getId().toHexString());
  }

}
