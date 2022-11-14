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

package com.abavilla.fpi.fw.exceptions;

import java.util.Map;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import lombok.ToString;

/**
 * Exception thrown when an error is occured when an FPI Service is invoking an external rest call.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@ToString(callSuper = true)
public class ApiSvcEx extends RuntimeException {

  private final transient JsonNode jsonResponse;

  private final transient HttpResponseStatus httpResponseStatus;

  private final transient String uriPath;

  private final transient Map<String, String> headers;

  public ApiSvcEx(String message, int httpStatus, JsonNode jsonNode, String uriPath, Map<String, String> headers) {
    this(message, HttpResponseStatus.valueOf(httpStatus), jsonNode, uriPath, headers);
  }

  public ApiSvcEx(String message, HttpResponseStatus httpStatus, JsonNode jsonNode, String uriPath,
                  Map<String, String> headers) {
    super(message);
    this.httpResponseStatus = httpStatus;
    this.jsonResponse = jsonNode;
    this.uriPath = uriPath;
    this.headers = headers;
  }

  public ApiSvcEx(String message) {
    this(message, null, null, null, null);
  }

  public ApiSvcEx(String message, int httpStatus) {
    this(message, httpStatus, null, null, null);
  }

  public <T> T getJsonResponse(Class<T> clazz) {
    return MapperUtil.convert(jsonResponse, clazz);
  }
}
