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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public final class MapperUtil {

  private static ObjectMapper mapper;

  @Inject
  ObjectMapper configuredMapper;

  public synchronized void init(@Observes StartupEvent evt) {
    mapper = configuredMapper;
  }

  public static ObjectMapper mapper() {
    return mapper;
  }

  public static <T> T convert(Object obj, Class<T> clazz) {
    return mapper.convertValue(obj, clazz);
  }

  /**
   * Deserializes json string into a java pojo object
   *
   * @param jsonString the json string
   * @param clazz Target java object
   * @return the deserialized object
   * @param <T> Type of the target pojo
   */
  public static <T> T readJson(String jsonString, Class<T> clazz) {
    try {
      return mapper.readerFor(clazz).readValue(jsonString);
    } catch (JsonProcessingException e) {
      Log.warn("Cannot serialize from JSON string: " + jsonString);
      return null;
    }
  }
}
