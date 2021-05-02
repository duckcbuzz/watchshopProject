package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.CartDetail;

public interface CartDetailService {
	List<CartDetail> findAll();
	Optional<CartDetail> findById(int id);
	void save (CartDetail cartDetail);
	void deleteById (int id);
}
