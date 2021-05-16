package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.User;

public interface OrderService {
	List<Order> findAll();
	Optional<Order> findById(int id);
	void save (Order order);
	void deleteById (int id);
	List<Order> findByUser(User user);
}
