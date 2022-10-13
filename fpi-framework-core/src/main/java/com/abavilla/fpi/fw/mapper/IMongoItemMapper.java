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
