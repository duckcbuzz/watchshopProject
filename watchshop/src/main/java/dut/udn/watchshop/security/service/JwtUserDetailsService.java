package dut.udn.watchshop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.service.UserService;
 
@Service
public class JwtUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserService userService;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).get();
        if(user==null){
                         throw new UsernameNotFoundException("Username does not exist!");
        }else{
            return JwtUserFactory.create(user);
        }
    }
}