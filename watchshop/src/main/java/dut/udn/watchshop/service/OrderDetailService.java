package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	List<OrderDetail> findByOrder(Order order);
	Optional<OrderDetail> findById(int id);
	void save (OrderDetail orderDetail);
	void deleteById (int id);
	void deteleByOrderInDetail(int id);
}
