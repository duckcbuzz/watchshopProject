package dut.udn.watchshop.security.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import dut.udn.watchshop.entity.Role;
import dut.udn.watchshop.entity.User;
import dut.udn.watchshop.security.domain.JwtUser;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
public final class JwtUserFactory {
 
    private JwtUserFactory(){
    }
 
    //Method to create JwtUser
    public static JwtUser create(User user){
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordResetDate(),
                user.getLoginTime(),
                                 mapToGrantedAuthorities(user.getRoles())); //Call the following static method
    }
 
    /*
                 Convert the queried user role set into a role set authorized by the security framework
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> set){
        return set.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());
                 //Turn the collection into a stream, then process it with functional programming, and finally turn the stream into a collection
 
    }
}