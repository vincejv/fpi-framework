package com.abavilla.fpi.fw.exceptions;

import lombok.Getter;
import lombok.Setter;
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
  public static int DEFAULT_HTTP_STATUS_CODE = 500;

  /**
   * Http Status Code
   */
  private final int httpStatus;

  /**
   * Http response body
   */
  @Setter
  private Object entity;

  /**
   * Create a {@link FPISvcEx} given a message, this message will be wrapped in the http response. Default http status
   * code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   */
  public FPISvcEx(String message) {
    super(message);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
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
  }

  /**
   * Create a {@link FPISvcEx} given a message and exception cause, this message will be wrapped in the http response.
   * Default http status code will be used as defined by {@link #DEFAULT_HTTP_STATUS_CODE}
   *
   * @param message Exception message
   */
  public FPISvcEx(String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = DEFAULT_HTTP_STATUS_CODE;
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
  }

}
