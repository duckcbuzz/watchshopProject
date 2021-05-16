package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.User;

public interface UserService {
	List<User> findAll();
	Optional<User> findById(int id);
	void save (User user);
	void deleteById (int id);
	Optional<User> findByUsername(String username);
    public boolean isExitsUserName(String userName) throws Exception;
    void resetPass(String email) throws Exception;
}
