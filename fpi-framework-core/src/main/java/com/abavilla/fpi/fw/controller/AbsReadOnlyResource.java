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
