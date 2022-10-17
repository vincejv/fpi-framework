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

import javax.ws.rs.Priorities;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.abavilla.fpi.fw.exceptions.ApiSvcEx;
import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.arc.Priority;
import lombok.SneakyThrows;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 * Default exception handler for REST Client exceptions, handles when REST Client gets an HTTP status greater than or
 * equal to 400.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Priority(Priorities.USER)
public class ApiRepoExHandler
    implements ResponseExceptionMapper<ApiSvcEx> {

  @Override
  public ApiSvcEx toThrowable(Response response) {
    try {
      response.bufferEntity();
    } catch (Exception ignored) {
      // exception is ignored
    }
    return new ApiSvcEx("Rest Client encountered an exception!", response.getStatus(), getBody(response));
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
