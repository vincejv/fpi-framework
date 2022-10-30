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

package com.abavilla.fpi.fw.dto.impl;

import java.util.List;

import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.dto.IDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Data transfer object containing a page of {@link AbsDto}.
 *
 * @param <D> DTO Type
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RegisterForReflection
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, defaultImpl = PageDto.class)
public class PageDto<D extends IDto> extends AbsDto {

  /**
   * Content of the page containing list of {@link D}
   */
  private final List<D> content;

  /**
   * Flag to indicate if page has a next page, {@code true} if has a next page,
   * otherwise {@code false}
   */
  private final Boolean hasNextPage;

  /**
   * Current page number
   */
  private final Integer pageNumber;

  /**
   * Number of items in the page
   */
  private final Integer count;

  /**
   * Maximum number of items in a page
   */
  private final Integer requestedCount;

  /**
   * Creates a new page containing list of {@link D}
   *
   * @param content Page content
   * @param hasNextPage Flag to indicate if it has a next page
   * @param pageNumber Current page number
   * @param count Number of items in page
   * @param <D> DTO Type
   * @return {@link PageDto}
   */
  public static <D extends IDto> PageDto<D> of(List<D> content, Boolean hasNextPage, Integer pageNumber, Integer count, Integer maxCount) {
    return new PageDto<>(content, hasNextPage, pageNumber, count, maxCount);
  }

}
