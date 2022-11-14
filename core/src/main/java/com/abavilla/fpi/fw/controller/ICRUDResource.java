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
