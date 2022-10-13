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
import io.smallrye.mutiny.Uni;

/**
 * REST API resource capable of both reading and writing.
 *
 * @param <D> DTO Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface ICRUDResource<D extends IDto>
    extends IReadOnlyResource<D>, IResource {

  /**
   * Update the item given by id.
   * It does not update the existing item's id.
   *
   * @param id Item id
   * @param body Updated item
   * @return {@link D} Object retrieved
   */
  Uni<D> updateItem(String id, D body);

  /**
   * Patches the item given by id.
   * It does not update the existing item's id.
   *
   * @param id Item id
   * @param body Updated item
   * @return {@link D} Object retrieved
   */
  Uni<D> patchItem(@PathParam("id") String id, D body);

  /**
   * Save a new item in database
   *
   * @param body Item to be saved in db
   * @return {@link D} Item after saved in db
   */
  Uni<D> saveItem(D body);

  /**
   * Deletes an item given an id.
   *
   * @param id Item id
   * @return {@link D} Deleted item
   */
  Uni<D> deleteItem(String id);
}
