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
public class AuthApiSvcEx extends ApiSvcEx {

  public AuthApiSvcEx(String message, int httpStatus, JsonNode jsonNode, String uriPath, Map<String, String> headers) {
    super(message, httpStatus, jsonNode, uriPath, headers);
  }

  public AuthApiSvcEx(String message, HttpResponseStatus httpStatus, JsonNode jsonNode, String uriPath, Map<String, String> headers) {
    super(message, httpStatus, jsonNode, uriPath, headers);
  }

  public AuthApiSvcEx(String message) {
    super(message);
  }

  public AuthApiSvcEx(String message, int httpStatus) {
    super(message, httpStatus);
  }
}
