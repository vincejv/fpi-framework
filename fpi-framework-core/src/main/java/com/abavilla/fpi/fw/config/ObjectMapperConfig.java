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

package com.abavilla.fpi.fw.config;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import io.quarkus.jackson.ObjectMapperCustomizer;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class ObjectMapperConfig implements ObjectMapperCustomizer {
  @Override
  public void customize(ObjectMapper mapper) {
    final var originalSerConfig = mapper.getSerializationConfig();
    final var newSerConfig = originalSerConfig
        .with(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
    final var ptv = BasicPolymorphicTypeValidator.builder().build();
    mapper.setConfig(newSerConfig);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
    customizeMapper(mapper);
  }

  public void customizeMapper(ObjectMapper mapper) {
    // implementing class will provide the functionality optionally
  }
}
