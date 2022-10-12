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

import javax.ws.rs.PathParam;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import io.smallrye.mutiny.Uni;

/**
 * REST API resource capable of both reading and writing.
 *
 * @param <Dto> DTO Type
 * @param <Entity> Entity Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface ICRUDResource<Dto extends IDto, Entity extends AbsItem>
    extends IReadOnlyResource<Dto, Entity>, IResource<Dto, Entity> {

  /**
   * Update the item given by id.
   * It does not update the existing item's id.
   *
   * @param id Item id
   * @param body Updated item
   * @return {@link Dto} Object retrieved
   */
  Uni<Dto> updateItem(String id, Dto body);

  /**
   * Patches the item given by id.
   * It does not update the existing item's id.
   *
   * @param id Item id
   * @param body Updated item
   * @return {@link Dto} Object retrieved
   */
  Uni<Dto> patchItem(@PathParam("id") String id, Dto body);

  /**
   * Save a new item in database
   *
   * @param body Item to be saved in db
   * @return {@link Dto} Item after saved in db
   */
  Uni<Dto> saveItem(Dto body);

  /**
   * Deletes an item given an id.
   *
   * @param id Item id
   * @return {@link Dto} Deleted item
   */
  Uni<Dto> deleteItem(String id);
}
