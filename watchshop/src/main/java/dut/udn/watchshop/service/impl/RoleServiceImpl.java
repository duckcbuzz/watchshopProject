package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Role;
import dut.udn.watchshop.repository.RoleRepository;
import dut.udn.watchshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}
}
