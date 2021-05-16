package dut.udn.watchshop.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import dut.udn.watchshop.bean.ResultBean;
import dut.udn.watchshop.entity.CartDetail;
import dut.udn.watchshop.service.CartDetailService;
import dut.udn.watchshop.service.UserService;
import dut.udn.watchshop.service.WatchService;
import dut.udn.watchshop.util.Constants;

@RestController
@RequestMapping("api/cart")
public class CartDetailController {
	@Autowired
	private CartDetailService CartDetailService;
	@Autowired
	private UserService userService;
	@Autowired
	private WatchService watchService;
	@GetMapping(value ="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public  ResponseEntity<ResultBean> getCartByUser(@PathVariable Integer id ) {
		  ResultBean resultBean = null;
	      try {
	          resultBean = new ResultBean(CartDetailService.findByUser(userService.findById(id).get()),Constants.STATUS_OK, Constants.MSG_OK);
	      } catch (Exception e) {
	          resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	          return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  
	  @PostMapping(value = "/",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> addCartDetail(@RequestBody String json){
		  ResultBean resultBean = null;
		  
	        try {
	        	JSONObject object = new JSONObject(json);
	        	CartDetail cart = null;

	        	if (CartDetailService.isExist(object.getInt("id_user"),object.getInt("id_watch")) != null) {
	        	cart = CartDetailService.findById(CartDetailService.isExist(object.getInt("id_user"),object.getInt("id_watch")) ).get();
	        	cart.setQuantity(cart.getQuantity() +  object.getInt("quantity"));
	        	} else {
	        	cart = new CartDetail(null, userService.findById(object.getInt("id_user")).get(),watchService.findById(object.getInt("id_watch")).get(), object.getInt("quantity"));
	        	}
	        	CartDetailService.save(cart);
	            resultBean = new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @DeleteMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> deleteCartDetail(@PathVariable Integer id){
		  ResultBean resultBean = null;
	        try {
	            CartDetailService.deleteById(id);
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @PutMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> updateCartDetail(@PathVariable Integer id,@RequestBody String json){
		  ResultBean resultBean = null;
	        try {
	            CartDetail cart = CartDetailService.findById(id).get();
	            CartDetail cartDetail = new ObjectMapper().readValue(json, CartDetail.class);
	            cart.setQuantity(cartDetail.getQuantity());
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
}
