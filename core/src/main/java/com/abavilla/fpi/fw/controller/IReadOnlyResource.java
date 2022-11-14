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
