package com.abavilla.fpi.fw.dto.impl;

import com.abavilla.fpi.fw.dto.AbsDto;
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
public class RespDto<T> extends AbsDto {

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

  public static <T> RespDto<T> wrap(T entity) {
    return wrap(entity, null);
  }

  public static <T> RespDto<T> wrap(T entity, Object status) {
    RespDto<T> respDto = new RespDto<>();
    respDto.setStatus(String.valueOf(status));
    respDto.setResp(entity);
    respDto.setTimestamp(DateUtil.nowAsStr());
    return respDto;
  }

  public static <T> RespDto<T> error(T entity, Object errorMsg) {
    RespDto<T> respDto = new RespDto<>();
    respDto.setError(String.valueOf(errorMsg));
    respDto.setResp(entity);
    respDto.setTimestamp(DateUtil.nowAsStr());
    return respDto;
  }

}
