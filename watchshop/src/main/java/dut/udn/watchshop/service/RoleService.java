package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Role;

public interface RoleService {
	List<Role> findAll();
	Optional<Role> findById(int id);
	void save (Role orderDetail);
	void deleteById (int id);
}
