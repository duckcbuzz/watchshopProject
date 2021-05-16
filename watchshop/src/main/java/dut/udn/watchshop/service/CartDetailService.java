package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.CartDetail;
import dut.udn.watchshop.entity.User;

public interface CartDetailService {
	List<CartDetail> findAll();
	List<CartDetail> findByUser(User user);
	Optional<CartDetail> findById(int id);
	void save (CartDetail cartDetail);
	void deleteById (int id);
	Integer isExist(int id_user, int id_watch);
}
