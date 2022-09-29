package com.abavilla.fpi.fw.dto.impl;

import com.abavilla.fpi.fw.dto.AbsDto;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Default data transfer object to use for responding over rest api.
 * @param <T> Type of the content of response
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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

}
