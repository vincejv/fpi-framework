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

import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.util.DateUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Default data transfer object to use for responding over rest api.
 * @param <T> Type of the content of response
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RegisterForReflection
public class RespDto<T extends IDto> extends AbsDto {

  /**
   * API Response
   */
  private T resp;

  /**
   * Status message
   */
  private String status;

  /**
   * Error message
   */
  private String error;

  /**
   * Timestamp
   */
  private String timestamp;

  public static <T extends IDto> RespDto<T> wrap(T entity) {
    return wrap(entity, null);
  }

  public static <T extends IDto> RespDto<T> wrap(T entity, Object status) {
    RespDto<T> respDto = new RespDto<>();
    respDto.setStatus(String.valueOf(status));
    respDto.setResp(entity);
    respDto.setTimestamp(DateUtil.nowAsStr());
    return respDto;
  }

  public static <T extends IDto> RespDto<T> error(T entity, Object errorMsg) {
    RespDto<T> respDto = new RespDto<>();
    respDto.setError(String.valueOf(errorMsg));
    respDto.setResp(entity);
    respDto.setTimestamp(DateUtil.nowAsStr());
    return respDto;
  }

}
