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
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.service.AbsSvc;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * REST API resource with built-in CRUD operations.
 *
 * @param <D> DTO Type
 * @param <E> Entity Type
 * @param <S> Service layer Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbsResource<D extends IDto, E extends AbsItem,
    S extends AbsSvc<D, E>> extends AbsReadOnlyResource<D, E, S>
    implements ICRUDResource<D>, IReadOnlyResource<D> {

  /**
   * {@inheritDoc}
   */
  @Override
  @Path("{id}")
  @PUT
  public Uni<D> updateItem(@PathParam("id") String id, D body) {
    return service.update(id, body);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Path("{id}")
  @PATCH
  public Uni<D> patchItem(@PathParam("id") String id, D body) {
    return service.patch(id, body);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @POST
  public Uni<D> saveItem(D body) {
    return service.save(body);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Path("{id}")
  @DELETE
  public Uni<D> deleteItem(@PathParam("id") String id) {
    return service.delete(id);
  }
}
