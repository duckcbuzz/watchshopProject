package dut.udn.watchshop.controller;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dut.udn.watchshop.bean.Login;
import dut.udn.watchshop.bean.ResultBean;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.security.domain.JwtAuthenticationResponse;
import dut.udn.watchshop.security.service.JwtTokenUtil;
import dut.udn.watchshop.security.service.JwtUserDetailsService;
import dut.udn.watchshop.service.UserService;
import dut.udn.watchshop.util.Constants;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil tokenProvider;

	@Autowired
	private JwtUserDetailsService customUserDetailService;

	@Autowired
	private UserService userService;

//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> getAllUsers() {
		ResultBean resultBean = null;
		try {
			resultBean = new ResultBean(userService.findAll(), Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(value = "/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> getUserById(@RequestParam Integer id) {
		ResultBean resultBean = null;
		try {
			resultBean = new ResultBean(userService.findById(id), Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/username", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> getUserByUsername(@RequestParam String username) {
		ResultBean resultBean = null;
		try {
			resultBean = new ResultBean(userService.findByUsername(username), Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> deleteUserById(@PathVariable Integer id) {
		ResultBean resultBean = null;
		try {
			userService.deleteById(id);
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('USER')")
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> updateUser(@PathVariable Integer id, @RequestBody String json) {
		ResultBean resultBean = null;
		try {
			JSONObject object = new JSONObject("json");
			userService.save(new User(id, null, object.getString("password"), object.getString("fullname"),
					object.getString("phone"), object.getString("email"),
					new SimpleDateFormat("dd/MM/yyyy").parse(object.getString("birthday")), object.getString("address"),null,null,
					null, null, null));
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> addUser(@RequestBody String json) {
		ResultBean resultBean = null;
		try {
			JSONObject object = new JSONObject("json");
			userService.save(new User(null, object.getString("username"), object.getString("password"),
					object.getString("fullname"), object.getString("phone"), object.getString("email"),
					new SimpleDateFormat("dd/MM/yyyy").parse(object.getString("birthday")), object.getString("address"),null,null,
					null, null, null));
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}

	@PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> login(@RequestBody String json) throws Exception{
		String jwt = null;
		UserDetails user = null;
		
		System.out.println(json);
		JSONObject object = new JSONObject("json");
		Login login = new Login(object.getString("username"),object.getString("password"));
		if (userService.isExitsUserName(login.getUserName())) {
			try {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				user = customUserDetailService.loadUserByUsername(login.getUserName());
				jwt = tokenProvider.generateToken(user);

			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping(value = "/resetPass/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> resetPass(@PathVariable Integer id){
		ResultBean resultBean = null;
		String email = userService.findById(id).get().getEmail();
		try {
			userService.resetPass(email);
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	}
}
