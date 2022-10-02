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

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.dto.impl.RespDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.exceptions.FPISvcEx;
import com.abavilla.fpi.fw.service.AbsSvc;
import com.abavilla.fpi.fw.util.DateUtil;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * REST API resource with no built-in CRUD operations.
 *
 * @param <E> DTO Type
 * @param <I> Entity Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbsBaseResource<E extends IDto, I extends AbsItem,
    S extends AbsSvc<E, I>> implements IResource<E, I> {

  /**
   * Service layer to operate on {@link I} item
   */
  @Inject
  protected S service;

  /**
   * Handles exceptions thrown by service layer.
   *
   * @param x Exception thrown
   * @return HTTP Response detailing the exception
   */
  @ServerExceptionMapper
  public RestResponse<RespDto<?>> mapException(FPISvcEx x) {
    RespDto<Object> resp = new RespDto<>();
    resp.setTimestamp(DateUtil.nowAsStr());
    resp.setResp(x.getEntity());
    resp.setError(x.getMessage());
    return RestResponse.status(Response.Status.fromStatusCode(x.getHttpStatus()), resp);
  }

}
