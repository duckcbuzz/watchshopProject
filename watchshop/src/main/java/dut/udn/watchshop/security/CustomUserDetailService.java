package dut.udn.watchshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.CustomUserDetails;
import dut.udn.watchshop.entity.User;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
        	User accountEntity = user.get();
        	return new CustomUserDetails(accountEntity);
            }
        throw new UsernameNotFoundException("Not found account with username: " + username);
    }

}