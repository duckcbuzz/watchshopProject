package dut.udn.watchshop.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dut.udn.watchshop.security.domain.JwtAuthenticationEntryPoint;
import dut.udn.watchshop.security.service.JwtAuthenticationTokenFilter;
import dut.udn.watchshop.security.service.JwtTokenUtil;
import dut.udn.watchshop.security.service.JwtUserDetailsService;

/**
 * Web security configuration class
 *
 */
@SuppressWarnings("SpringJavaAutowiringInspection") // Suppressed a warning
@Configuration // This is the configuration class
@EnableWebSecurity // Allow webSecurity to check
@EnableGlobalMethodSecurity(prePostEnabled = true) // Security check class for all methods in the global
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	// Unauthorized response processing class
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.route.authentication.path}")
	// Get the path of the authorization token (in the configuration file)
	private String authenticationPath;

	@Autowired
	// Configure a global authorization manager
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
	}

	@Autowired
	// Limited implementation class instance name
	@Qualifier("jwtUserDetailsService") // Limited interface UserDetailsService must be tied to jwtUserDetailsService
	private UserDetailsService UserDetailsService;

	// Global configuration
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.UserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	// Authorization Manager
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// configure is written in two parts
	// Safe access strategy
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable().cors().and() // supports cross-domain access

				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/product/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
				.permitAll().antMatchers("/api/user/**").permitAll() // paths that can be accessed without authorization
				.anyRequest().authenticated(); // Other requests need to be verified

		// disable page caching
		httpSecurity.headers().frameOptions().sameOrigin() // required to set for H2 else H2 Console will be blank.
				.cacheControl();

		// Custom JWT based security filter
		// Create a filter, filter jwt request
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter(userDetailsService(),
				jwtTokenUtil, tokenHeader);
		httpSecurity // Add the filter to the security policy
				.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().frameOptions().sameOrigin() // required to set for H2 else H2 Console will be blank.
				.cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
				// allow anonymous resource requests
				.and().ignoring()
				.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}
}