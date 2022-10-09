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

package com.abavilla.fpi.fw.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
   * Object Id
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
  public AbsDto() {
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
    if (exceptions.size() == 0) {
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
