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

package com.abavilla.fpi.fw.exceptions;

import com.abavilla.fpi.fw.dto.IDto;
import lombok.Getter;
import lombok.ToString;

/**
 * FPI Exceptions thrown in service layer to generate HTTP errors with custom status code.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@ToString(callSuper = true)
public class FPISvcEx extends RuntimeException {

  /**
   * Default HTTP Status code for the exception when not defined
   */
  public static final int DEFAULT_HTTP_STATUS_CODE = 500;

  /**
   * Http Status Code
   */
  private final int httpStatus;

  /**
   * Http response body
   */
  private final IDto entity;

  /**
   * Create a {@link FPISvcEx} given a message, this message will be wrapped in the http response. Default http status
   * code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   */
  public FPISvcEx(String message) {
    super(message);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = null;
  }

  /**
   * Create a {@link FPISvcEx} given a message, this message will be wrapped in the http response. Default http status
   * code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   * @param entity the entity
   */
  public FPISvcEx(String message, IDto entity) {
    super(message);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = entity;
  }

  /**
   * Create a {@link FPISvcEx} given a message and http status code, this message will be wrapped in the http response.
   *
   * @param message Exception message
   * @param httpStatus HTTP Status code
   */
  public FPISvcEx(String message, int httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
    this.entity = null;
  }

  /**
   * Create a {@link FPISvcEx} given a message and http status code, this message will be wrapped in the http response.
   *
   * @param message Exception message
   * @param httpStatus HTTP Status code
   * @param entity the entity
   */
  public FPISvcEx(String message, int httpStatus, IDto entity) {
    super(message);
    this.httpStatus = httpStatus;
    this.entity = entity;
  }

  /**
   * Create a {@link FPISvcEx} given a message and exception cause, this message will be wrapped in the http response.
   * Default http status code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   * @param cause the cause
   */
  public FPISvcEx(String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = null;
  }

  /**
   * Create a {@link FPISvcEx} given a message and exception cause, this message will be wrapped in the http response.
   * Default http status code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   * @param cause the cause
   * @param entity the entity
   */
  public FPISvcEx(String message, Throwable cause, IDto entity) {
    super(message, cause);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = entity;
  }

  /**
   * Create an {@link FPISvcEx} given a message, a cause, and http status code, this message will be wrapped in the
   * http response
   *
   * @param message Exception message
   * @param cause Cause of exception
   * @param httpStatus HTTP Status code
   */
  public FPISvcEx(String message, Throwable cause, int httpStatus) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.entity = null;
  }

  /**
   * Create an {@link FPISvcEx} given a message, a cause, and http status code, this message will be wrapped in the
   * http response
   *
   * @param message Exception message
   * @param cause Cause of exception
   * @param httpStatus HTTP Status code
   * @param entity the entity
   */
  public FPISvcEx(String message, Throwable cause, int httpStatus, IDto entity) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.entity = entity;
  }

  /**
   * Create a {@link FPISvcEx} given the exception cause, message to be used is from {@code cause}, this message will
   * be wrapped in the http response. Default http status code will be used as defined by
   * {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param cause Cause of exception
   */
  public FPISvcEx(Throwable cause) {
    super(cause.getMessage(), cause);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = null;
  }

  /**
   * Create a {@link FPISvcEx} given the exception cause, message to be used is from {@code cause}, this message will
   * be wrapped in the http response. Default http status code will be used as defined by
   * {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param cause Cause of exception
   * @param entity the entity
   */
  public FPISvcEx(Throwable cause, IDto entity) {
    super(cause.getMessage(), cause);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
    this.entity = entity;
  }

  /**
   * Create a {@link FPISvcEx} given the exception cause, message to be used is from {@code cause}, this message will
   * be wrapped in the http response.
   *
   * @param cause Cause of exception
   * @param httpStatus HTTP Status code
   */
  public FPISvcEx(Throwable cause, int httpStatus) {
    super(cause);
    this.httpStatus = httpStatus;
    this.entity = null;
  }

  /**
   * Create a {@link FPISvcEx} given the exception cause, message to be used is from {@code cause}, this message will
   * be wrapped in the http response.
   *
   * @param cause Cause of exception
   * @param httpStatus HTTP Status code
   * @param entity the entity
   */
  public FPISvcEx(Throwable cause, int httpStatus, IDto entity) {
    super(cause);
    this.httpStatus = httpStatus;
    this.entity = entity;
  }

  /**
   * Gets the entity given the target class type, returns {@code null} if not castable
   * @param targetClass the target class type
   * @return the entity
   * @param <T> Type of entity
   */
  public <T extends IDto> T getEntity(Class<T> targetClass) {
    if (targetClass.isInstance(entity)) {
      return targetClass.cast(entity);
    } else {
      return null;
    }
  }

}
