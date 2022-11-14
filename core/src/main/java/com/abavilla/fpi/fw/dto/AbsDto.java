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

package com.abavilla.fpi.fw.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

/**
 * Data transfer object template for which all DTO objects inherit, contains the basic common functionality and fields
 * that a DTO may optionally use.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Data
@RegisterForReflection
public abstract class AbsDto implements IDto {

  /**
   * Object ID
   */
  protected String id;

  /**
   * Date and time the DTO is created
   */
  protected LocalDateTime dateCreated;

  /**
   * Date and time the DTO is last updated
   */
  protected LocalDateTime dateUpdated;

  /**
   * {@link List} of {@link Exception} that were encountered while generating the DTO output.
   * Marked with {@code transient} and {@link JsonIgnore} so it won't be part of serializations.
   */
  @JsonIgnore
  private transient List<Exception> exceptions;

  /**
   * Creates an instance of an empty DTO and initializes {@link #exceptions} list.
   */
  protected AbsDto() {
    exceptions = new ArrayList<>();
  }

  /**
   * Inserts a new {@link Exception} that was encountered while generating the DTO Output.
   * Initializes {@link #exceptions} list if not yet initialized.
   *
   * @param ex Exception encountered
   */
  public void chainEx(Exception ex) {
    if (exceptions == null) {
      exceptions = new ArrayList<>();
    }
    exceptions.add(ex);
  }

  /**
   * Gets the last {@link Exception} encountered while generating the DTO Output.
   * Should not be included during serialization as it is marked with {@link JsonIgnore} annotation.
   *
   * @return the exception
   */
  @JsonIgnore // used only internally, not to be serialized
  public Exception getLastEx() {
    if (exceptions.isEmpty()) {
      return null;
    }
    return exceptions.get(exceptions.size()-1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonNode toJson() {
    return MapperUtil.mapper().convertValue(this, JsonNode.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toJsonStr() {
    return toJson().toString();
  }
}
