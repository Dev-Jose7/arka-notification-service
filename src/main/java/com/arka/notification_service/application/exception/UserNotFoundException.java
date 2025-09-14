package com.arka.notification_service.application.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
      super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
      super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
      super("User not found", cause);
    }

    public UserNotFoundException() {
      super("User not found");
    }
}
