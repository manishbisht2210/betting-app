package com.bettingservice.exception;

/**
 * Created by manish.bisht on 3/8/2019.
 */
public class InvalidBettingException extends Exception{
  public InvalidBettingException() {
    super();
  }

  public InvalidBettingException(String message) {
    super(message);
  }
}
