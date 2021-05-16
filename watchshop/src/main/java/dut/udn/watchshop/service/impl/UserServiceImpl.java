package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.repository.UserRepository;
import dut.udn.watchshop.service.UserService;
import dut.udn.watchshop.util.Utils;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean isExitsUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(userName).isPresent();
	}

	@Override
	public void resetPass(String email) throws Exception {
		String subject = "Mật khẩu mới";
        String msg = "Mật khẩu mới của bạn đã được tạo tự động: " + Utils.randomPassword() +"<br>";
        Utils.sendmail(email, subject, msg);
	}

}
