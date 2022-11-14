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

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.dto.impl.PageDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.service.AbsSvc;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * REST API resource that's only capable or READ operations.
 *
 * @param <D> DTO Type
 * @param <E> Entity Type
 * @param <S> Service layer Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbsReadOnlyResource<D extends IDto, E extends AbsItem,
    S extends AbsSvc<D, E>> extends AbsBaseResource<D, E, S>
    implements IReadOnlyResource<D>, IResource {

  /**
   * {@inheritDoc}
   */
  @Override
  @GET
  public Multi<D> getAll() {
    return service.list();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Path("page")
  @GET
  public Uni<PageDto<D>> getByPage(@QueryParam("no") Integer pageNo,
                                   @QueryParam("size") Integer size) {
    return service.getByPage(pageNo,
        Objects.requireNonNullElse(size, 50));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Path("{id}")
  @GET
  public Uni<D> getById(@PathParam("id") String id) {
    return service.get(id);
  }

}
