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

package com.abavilla.fpi.fw.exceptions.handler;

import java.util.Map;
import java.util.stream.Collectors;

import com.abavilla.fpi.fw.exceptions.ApiSvcEx;
import com.abavilla.fpi.fw.exceptions.AuthApiSvcEx;
import com.abavilla.fpi.fw.util.FWConst;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import org.jboss.resteasy.reactive.RestResponse;

/**
 * Default exception handler for REST Client exceptions, handles when REST Client gets an HTTP status greater than or
 * equal to 400.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public class ApiRepoExHandler
    implements ResponseExceptionMapper<ApiSvcEx> {

  @Override
  public ApiSvcEx toThrowable(Response response) {
    try {
      response.bufferEntity();
    } catch (Exception ignored) {
      // exception is ignored
    }

    if (response.getStatus() == RestResponse.StatusCode.UNAUTHORIZED ||
      response.getStatus() == RestResponse.StatusCode.FORBIDDEN) {
      return new AuthApiSvcEx("Rest Client was unable to access the resource!", response.getStatus(), getBody(response),
        String.valueOf(response.getLocation()),
        response.getStringHeaders().entrySet().stream().collect(Collectors.toUnmodifiableMap(
          Map.Entry::getKey, e -> StringUtils.join(e.getValue(), FWConst.COMMA_SEP)
        )));
    } else {
      return new ApiSvcEx("Rest Client encountered an exception!", response.getStatus(), getBody(response),
        String.valueOf(response.getLocation()),
        response.getStringHeaders().entrySet().stream().collect(Collectors.toUnmodifiableMap(
          Map.Entry::getKey, e -> StringUtils.join(e.getValue(), FWConst.COMMA_SEP)
        )));
    }
  }

  @Override
  public boolean handles(int status, MultivaluedMap<String, Object> headers) {
    return status >= 400;
  }

  @SneakyThrows
  private JsonNode getBody(Response response) {
    if (response.hasEntity()) {
      return response.readEntity(JsonNode.class);
    } else {
      return null;
    }
  }
}
