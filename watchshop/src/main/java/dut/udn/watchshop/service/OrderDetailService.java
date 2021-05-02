package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	Optional<OrderDetail> findById(int id);
	void save (OrderDetail orderDetail);
	void deleteById (int id);
}
