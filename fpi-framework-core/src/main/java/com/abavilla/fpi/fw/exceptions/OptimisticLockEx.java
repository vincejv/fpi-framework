package com.abavilla.fpi.fw.exceptions;

public class OptimisticLockEx extends RuntimeException {
  public OptimisticLockEx() {
  }

  public OptimisticLockEx(String message) {
    super(message);
  }

  public OptimisticLockEx(String message, Throwable cause) {
    super(message, cause);
  }

  public OptimisticLockEx(Throwable cause) {
    super(cause);
  }

  public OptimisticLockEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
