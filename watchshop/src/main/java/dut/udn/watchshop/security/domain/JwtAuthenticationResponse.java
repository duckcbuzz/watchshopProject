package dut.udn.watchshop.security.domain;



import java.io.Serializable;
 
/**
   * Response token class
 */
 
public class JwtAuthenticationResponse implements Serializable {
  private static final long serialVersionUID = 4784951536404964122L;
     private final String token; //To send back the token of the client
 
  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }
 
  public String getToken() {
    return this.token;
  }
}