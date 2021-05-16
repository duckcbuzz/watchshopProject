package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.CartDetail;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.repository.CartDetailRepository;
import dut.udn.watchshop.service.CartDetailService;

@Service
public class CartDetailServiceImpl implements CartDetailService{
	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Override
	public List<CartDetail> findAll() {
		// TODO Auto-generated method stub
		return cartDetailRepository.findAll();
	}

	@Override
	public Optional<CartDetail> findById(int id) {
		// TODO Auto-generated method stub
		return cartDetailRepository.findById(id);
	}

	@Override
	public void save(CartDetail cartDetail) {
		// TODO Auto-generated method stub
		cartDetailRepository.save(cartDetail);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		cartDetailRepository.deleteById(id);
	}

	@Override
	public List<CartDetail> findByUser(User user) {
		// TODO Auto-generated method stub
		return cartDetailRepository.findByCartUser(user);
	}

	@Override
	public Integer isExist(int id_user, int id_watch) {
		// TODO Auto-generated method stub
		return cartDetailRepository.isExist(id_user,id_watch);
	}
}
