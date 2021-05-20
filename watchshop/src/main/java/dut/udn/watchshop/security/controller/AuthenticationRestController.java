package dut.udn.watchshop.security.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dut.udn.watchshop.security.domain.AuthenticationException;
import dut.udn.watchshop.security.domain.JwtAuthenticationRequest;
import dut.udn.watchshop.security.domain.JwtAuthenticationResponse;
import dut.udn.watchshop.security.domain.JwtUser;
import dut.udn.watchshop.security.service.JwtTokenUtil;

/**
 * Authorized controller User login controller
 *
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	
	@Autowired
  private AuthenticationManager authenticationManager;

	@Autowired
  private JwtTokenUtil jwtTokenUtil;

	@Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

	/**
       * Create an authorization token (login)
   *
   * @param authenticationRequest
   * @return
   * @throws AuthenticationException
   */
     @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST) //User login and password have been encapsulated in JwtAuthenticationRequest
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
 
         //authenticate verifies the username and password (below this class)
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
 
         //Check after passing
    // Reload password post-security so we can generate the token
         // Check the user by user name
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
         // Then pass the user generated token token
    final String token = jwtTokenUtil.generateToken(userDetails);
         // Encapsulate the token into the JwtAuthenticationResponse and return
    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

	/**
       * Refresh
   *
   * @param request
   * @return
   */
  @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
 
    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

	@ExceptionHandler({AuthenticationException.class})
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

	/**
   * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
   */
  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);
 
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("User is disabled!", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Bad credentials!", e);
    }
  }

}