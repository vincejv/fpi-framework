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

package com.abavilla.fpi.fw.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public abstract class AbsItem implements IItem {

  private Serializable id;

  protected Boolean isArchived;

  protected LocalDateTime dateCreated;

  protected LocalDateTime dateUpdated;

  private Long version;

  private transient List<Exception> exceptions;

  protected AbsItem() {
    exceptions = new ArrayList<>();
  }

  public void chainEx(Exception ex) {
    exceptions.add(ex);
  }

  public Exception lastEx() {
    if (exceptions.isEmpty()) {
      return null;
    }
    return exceptions.get(exceptions.size()-1);
  }

  @Override
  public JsonNode toJson() {
    return MapperUtil.mapper().convertValue(this, JsonNode.class);
  }

  @Override
  public String toJsonStr() {
    return toJson().toString();
  }
}
