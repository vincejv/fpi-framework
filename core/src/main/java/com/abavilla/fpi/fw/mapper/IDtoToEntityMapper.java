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

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.IItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper used for conversion and mapping fields between {@link IDto} and {@link IItem} database item.
 * @param <D> DTO Type
 * @param <E> Entity Type
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IDtoToEntityMapper<D extends IDto, E extends IItem> extends IMapper {

  D mapToDto(E entity);

  E mapToEntity(D dto);

  /**
   * Partially patch entity, skip updating null values and only update the target entity with filled values
   * from source DTO.
   *
   * @param entity Target entity
   * @param dto Source DTO
   */
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void patchEntity(@MappingTarget E entity, D dto);
}
