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

import javax.enterprise.context.ApplicationScoped;
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

/**
 * REST API resource with no built-in CRUD operations.
 *
 * @param <D> DTO Type
 * @param <E> Entity Type
 * @param <S> Service layer Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbsBaseResource<D extends IDto, E extends AbsItem,
    S extends AbsSvc<D, E>> implements IResource {

  /**
   * Service layer to operate on {@link E} item
   */
  @Inject
  protected S service;

  /**
   * Handles exceptions thrown by service layer.
   *
   * @param x Exception thrown
   * @return HTTP Response detailing the exception
   */
  protected RestResponse<RespDto<IDto>> mapException(FPISvcEx x) {
    RespDto<IDto> resp = new RespDto<>();
    resp.setTimestamp(DateUtil.nowAsStr());
    resp.setResp(x.getEntity());
    resp.setError(x.getMessage());
    return RestResponse.status(Response.Status.fromStatusCode(x.getHttpStatus()), resp);
  }

}
