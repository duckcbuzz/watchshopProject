package dut.udn.watchshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.service.OrderDetailService;
import dut.udn.watchshop.service.OrderService;
import dut.udn.watchshop.service.UserService;

@RestController
@RequestMapping("api")
public class TestController {
	@Autowired
	private OrderService OrderService;
	@Autowired
	private OrderDetailService detailService;
	@Autowired
	private UserService userService;
	@GetMapping(value ="/",produces = { MediaType.APPLICATION_JSON_VALUE })
	public  Optional<Order> getCartByUser( ) {
		  return OrderService.findById(1);
	};
	@GetMapping(value ="/detail/",produces = { MediaType.APPLICATION_JSON_VALUE })
	public  Object getOrder() {
		  return OrderService.findById(1).get().getListItem();
	};
	@GetMapping(value ="/user/2",produces = { MediaType.APPLICATION_JSON_VALUE })
	public  List<User> getUser() {
		detailService.deleteById(1);
			  return userService.findAll();
	};
}
