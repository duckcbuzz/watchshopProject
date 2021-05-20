package dut.udn.watchshop.security.domain;

/**
  * Authorization exception class
*/
public class AuthenticationException extends RuntimeException {
 public AuthenticationException(String message, Throwable cause) {
   super(message, cause);
 }
}