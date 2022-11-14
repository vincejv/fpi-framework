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

import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.entity.mongo.AbsMongoItem;

/**
 * Mapper used for conversion and mapping fields between {@link AbsDto} and {@link AbsMongoItem} database item.
 * @param <D> DTO Type
 * @param <E> Entity Type
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IMongoItemMapper<D extends AbsDto, E extends AbsMongoItem>
    extends IDtoToEntityMapper<D, E> {

}
