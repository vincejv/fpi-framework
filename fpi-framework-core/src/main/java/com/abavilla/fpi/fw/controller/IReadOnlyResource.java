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

package com.abavilla.fpi.fw.controller;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.dto.impl.PageDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * REST API resource capable of reading only
 *
 * @param <D> DTO Type
*  @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IReadOnlyResource<D extends IDto> extends IResource {

   /**
   * Retrieves by page, if page number and size are not given, returns the entire list.
   *
   * @param pageNo Page number
   * @param size Items per page
   * @return List of {@link D} items
   */
  Uni<PageDto<D>> getByPage(Integer pageNo, Integer size);

  /**
   * Retrieves all items from the database
   *
   * @return List of {@link D} items
   */
  Multi<D> getAll();

  /**
   * Retrieve item given by id
   *
   * @param id Item id
   * @return {@link D} Object retrieved
   */
  Uni<D> getById(String id);
}
