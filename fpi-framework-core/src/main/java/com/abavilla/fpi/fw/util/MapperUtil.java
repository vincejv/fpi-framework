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
