package com.bettingservice.exception;

/**
 * Created by manish.bisht on 3/8/2019.
 */
public class InvalidVoucherException extends Exception{
  public InvalidVoucherException() {
    super();
  }

  public InvalidVoucherException(String message) {
    super(message);
  }
}
