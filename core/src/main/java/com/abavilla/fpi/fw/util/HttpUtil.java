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

package com.abavilla.fpi.fw.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.vertx.core.http.HttpServerRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpUtil {

  public static final String USER_AGENT = "User-Agent";

  public static String getUserAgent(HttpServerRequest request) {
    return request.getHeader(USER_AGENT);
  }

  private static final List<String> IP_HEADERS = Arrays.asList("HTTP_CF_CONNECTING_IP", "REMOTE_ADDR",
      "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
      "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "True-Client-IP");

  public static String getClientIpAddr(HttpServerRequest request) {
    return IP_HEADERS.stream()
        .map(request::getHeader)
        .filter(Objects::nonNull)
        .filter(ip -> !ip.isEmpty() && !ip.equalsIgnoreCase("unknown"))
        .findFirst()
        .orElse(request.remoteAddress().hostAddress());
  }
}
