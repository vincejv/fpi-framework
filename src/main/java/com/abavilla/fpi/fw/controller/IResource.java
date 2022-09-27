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
import com.abavilla.fpi.fw.entity.AbsItem;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface IResource<E extends IDto, I extends AbsItem> {

   /**
   * Retrieves by page, if page number and size are not given, returns the entire list.
   *
   * @param pageNo Page number
   * @param size Items per page
   * @return List of {@link I} items
   */
  Multi<E> getByPage(Integer pageNo, Integer size);

  /**
   * Retrieve item given by id
   *
   * @param id Item id
   * @return {@link E} Object retrieved
   */
  Uni<E> getById(String id);

  /**
   * Update the item given by id
   *
   * @param id Item id
   * @param body Updated item
   * @return {@link E} Object retrieved
   */
  Uni<E> updateItem(String id, E body);

  /**
   * Save a new item in database
   *
   * @param body Item to be saved in db
   * @return {@link E} Item after saved in db
   */
  Uni<E> saveItem(E body);

  /**
   * Deletes an item given an id.
   *
   * @param id Item id
   * @return {@link E} Deleted item
   */
  Uni<E> deleteItem(String id);
}
