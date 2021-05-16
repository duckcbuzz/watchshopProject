package dut.udn.watchshop.controller;

import java.net.URI;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import dut.udn.watchshop.bean.ResultBean;
import dut.udn.watchshop.config.PaypalPaymentIntent;
import dut.udn.watchshop.config.PaypalPaymentMethod;
import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.OrderDetail;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.entity.Watch;
import dut.udn.watchshop.service.OrderDetailService;
import dut.udn.watchshop.service.OrderService;
import dut.udn.watchshop.service.PaypalService;
import dut.udn.watchshop.service.UserService;
import dut.udn.watchshop.util.Constants;
import dut.udn.watchshop.util.Utils;

@RestController
@RequestMapping("api/order")
public class OrderController {
	@Autowired
	private OrderService OrderService;
	@Autowired
	private OrderDetailService OrderDetailService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaypalService paypalService;
	public static final String URL_PAYPAL_SUCCESS = "/success";
	public static final String URL_PAYPAL_CANCEL = "/cancel";

	@GetMapping(value = "/user/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> getOrderByUser(@PathVariable Integer id) {
		ResultBean resultBean = null;
		try {
			resultBean = new ResultBean(OrderService.findByUser(userService.findById(id).get()), Constants.STATUS_OK,
					Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	};

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> getOrderItemByUser(@PathVariable Integer id) {
		ResultBean resultBean = null;
		try {
			resultBean = new ResultBean(OrderDetailService.findByOrder(OrderService.findById(id).get()),
					Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	};

	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> addOrder(@RequestBody String json) {
		ResultBean resultBean = null;
		try {

			JSONObject object = new JSONObject(json);
			Order order = new Order(null, new User(object.getInt("id_user")), new Date(System.currentTimeMillis()),
					object.getString("address"), object.getString("phone"), object.getString("receiver"),
					object.getString("note"), null);
			OrderService.save(order);
			JSONArray listOrder = object.getJSONArray("detail");
			for (int i = 0; i < listOrder.length(); i++) {
				JSONObject objectDetail = listOrder.getJSONObject(i);
				OrderDetail orderdetail = new OrderDetail(null, order, new Watch(objectDetail.getInt("id_watch")),
						objectDetail.getInt("quantity"), null);
				OrderDetailService.save(orderdetail);
			}
			resultBean = new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	};

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> deleteOrder(@PathVariable Integer id) {
		ResultBean resultBean = null;
		try {
			OrderDetailService.deteleByOrderInDetail(id);
			OrderService.deleteById(id);
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	};

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> updateOrder(@PathVariable Integer id, @RequestBody String json) {
		ResultBean resultBean = null;
		try {
			JSONObject object = new JSONObject("json");
			OrderService.save(new Order(id, null, null, object.getString("address"), object.getString("phone"),
					object.getString("receiver"), object.getString("note"), null));
			resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	};

	@GetMapping(value = URL_PAYPAL_SUCCESS, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		ResultBean resultBean = null;
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				resultBean = new ResultBean(Constants.STATUS_OK, Constants.MSG_SUCCESS);
				return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
			}
		} catch (PayPalRESTException e) {
			resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://fullstackdeveloper.guru")).build();
	}
	@GetMapping(value = URL_PAYPAL_CANCEL, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> cancelPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//		return new ResponseEntity<ResultBean>(new ResultBean(Constants.STATUS_OK, Constants.MSG_CANCEL), HttpStatus.OK);
	    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://fullstackdeveloper.guru")).build();
	}

	@PostMapping(value = "/payment", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResultBean> pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/api/order" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/api/order" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return new ResponseEntity<ResultBean>(new ResultBean(links.getHref(), Constants.MSG_OK),
							HttpStatus.OK);
				}
			}
		} catch (PayPalRESTException e) {
			return new ResponseEntity<ResultBean>(new ResultBean(null, Constants.MSG_OK), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResultBean>(new ResultBean(null, Constants.MSG_OK), HttpStatus.BAD_GATEWAY);
	}
}
