package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Order;

public interface OrderService {
	List<Order> findAll();
	Optional<Order> findById(int id);
	void save (Order order);
	void deleteById (int id);
}
