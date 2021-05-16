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

import dut.udn.watchshop.bean.ResultBean;
import dut.udn.watchshop.entity.AlbertType;
import dut.udn.watchshop.entity.Brand;
import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.entity.Color;
import dut.udn.watchshop.entity.Watch;
import dut.udn.watchshop.service.WatchService;
import dut.udn.watchshop.util.Constants;

@RestController
@RequestMapping("api/product")
public class WatchController {
	@Autowired
	  private WatchService watchService;
	@GetMapping(value ="/",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public  ResponseEntity<ResultBean> getAllProduct( ) {
		  ResultBean resultBean = null;
	      try {
	          resultBean = new ResultBean(watchService.findAll(),Constants.STATUS_OK, Constants.MSG_OK);
	      } catch (Exception e) {
	          resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	          return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @GetMapping(value ="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public  ResponseEntity<ResultBean> getWatchById(@PathVariable Integer id ) {
		  ResultBean resultBean = null;
	      try {
	          resultBean = new ResultBean(watchService.findById(id),Constants.STATUS_OK, Constants.MSG_OK);
	      } catch (Exception e) {
	          resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	          return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @PostMapping(value = "/",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> addWatch(@RequestBody String json){
		  ResultBean resultBean = null;
	        try {
	        	JSONObject object = new JSONObject(json);
	        	watchService.save(new Watch(null, object.getString("name"), object.getInt("price"), object.getString("image"),
	        			object.getInt("amount"), object.getBoolean("sex"), object.getString("size"), 
	        			new Brand(object.getInt("id_brand")), new Color(object.getInt("id_color")), 
	        			new AlbertType(object.getInt("id_albert")), new ClockWork(object.getInt("id_clockwork")),
	        			new Color(object.getInt("id_color_albert"))));
	            resultBean = new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @DeleteMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> deleteWatch(@PathVariable Integer id){
		  ResultBean resultBean = null;
	        try {
	        	watchService.deleteById(id);
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @PutMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> updateWatch(@PathVariable Integer id,@RequestBody String json){
		  ResultBean resultBean = null;
	        try {
	        	JSONObject object = new JSONObject(json);
	        	watchService.save(new Watch(id, object.getString("name"), object.getInt("price"), object.getString("image"),
	        			object.getInt("amount"), object.getBoolean("sex"), object.getString("size"), 
	        			new Brand(object.getInt("id_brand")), new Color(object.getInt("id_color")), 
	        			new AlbertType(object.getInt("id_albert")), new ClockWork(object.getInt("id_clockwork")),
	        			new Color(object.getInt("id_color_albert"))));
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
}
