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

@Data
@RegisterForReflection
public abstract class AbsDto implements IDto {

  protected String id;

  protected LocalDateTime dateCreated;

  protected LocalDateTime dateUpdated;

  @JsonIgnore
  private transient List<Exception> exceptions;

  public AbsDto() {
    exceptions = new ArrayList<>();
  }


  public void chainEx(Exception ex) {
    exceptions.add(ex);
  }

  @JsonIgnore // used only internally, not to be serialized
  public Exception getLastEx() {
    if (exceptions.size() == 0) {
      return null;
    }
    return exceptions.get(exceptions.size()-1);
  }

  @Override
  public JsonNode toJson() {
    return MapperUtil.mapper().convertValue(this, JsonNode.class);
  }
}
