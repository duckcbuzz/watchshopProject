package dut.udn.watchshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import dut.udn.watchshop.bean.ResultBean;
import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.service.ClockWorkService;
import dut.udn.watchshop.service.WatchService;
import dut.udn.watchshop.util.Constants;

@RestController
@RequestMapping("api/clockwork")
public class ClockWorkController {
	@Autowired
	private ClockWorkService clockworkService;
	@Autowired
	  private WatchService watchService;
	@GetMapping(value ="/",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public  ResponseEntity<ResultBean> getAllclockwork( ) {
		  ResultBean resultBean = null;
	      try {
	          resultBean = new ResultBean(clockworkService.findAll(),Constants.STATUS_OK, Constants.MSG_OK);
	      } catch (Exception e) {
	          resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	          return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @GetMapping(value ="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public  ResponseEntity<ResultBean> getWatchByclockwork(@PathVariable Integer id ) {
		  ResultBean resultBean = null;
	      try {
	          resultBean = new ResultBean(watchService.findByClockWork(clockworkService.findById(id).get()),Constants.STATUS_OK, Constants.MSG_OK);
	      } catch (Exception e) {
	          resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	          return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @PostMapping(value = "/add",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> addclockwork(@RequestParam("json") String json){
		  ResultBean resultBean = null;
	        try {
	            clockworkService.save(new ObjectMapper().readValue(json, ClockWork.class));
	            resultBean = new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @DeleteMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> deleteclockwork(@PathVariable Integer id){
		  ResultBean resultBean = null;
	        try {
	            clockworkService.deleteById(id);
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
	  @PutMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<ResultBean> updateclockwork(@PathVariable Integer id,@RequestParam("json") String json){
		  ResultBean resultBean = null;
	        try {
	            ClockWork clockwork = clockworkService.findById(id).get();
	            ClockWork clockworkDetail = new ObjectMapper().readValue(json, ClockWork.class);
	            clockwork.setName(clockworkDetail.getName());
	            resultBean =  new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
	            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
	  };
}
