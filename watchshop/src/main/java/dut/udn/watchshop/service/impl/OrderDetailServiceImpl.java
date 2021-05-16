package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.OrderDetail;
import dut.udn.watchshop.repository.OrderDetailRepository;
import dut.udn.watchshop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired 
	private OrderDetailRepository orderDetailService;

	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return orderDetailService.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(int id) {
		// TODO Auto-generated method stub
		return orderDetailService.findById(id);
	}

	@Override
	public void save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDetailService.save(orderDetail);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		orderDetailService.deleteById(id);
	}

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDetailService.findByOrderInDetail(order);
	}

	@Override
	public void deteleByOrderInDetail(int id) {
		// TODO Auto-generated method stub
		orderDetailService.deteleByOrderInDetail(id);
	}
}
