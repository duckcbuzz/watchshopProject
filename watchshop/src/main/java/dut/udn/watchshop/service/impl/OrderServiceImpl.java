package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.repository.OrderRepository;
import dut.udn.watchshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}

	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderRepository.save(order);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);;
	}

	@Override
	public List<Order> findByUser(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findByOrderUser(user);
	};
}
