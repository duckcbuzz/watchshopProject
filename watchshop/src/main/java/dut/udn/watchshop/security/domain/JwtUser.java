package dut.udn.watchshop.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dut.udn.watchshop.entity.Role;
import dut.udn.watchshop.entity.User;

public class JwtUser implements UserDetails{

        private final Integer id; // required
        private final String username; // required
        private final String password; // required
        private final String email;
        private final Date lastPasswordResetDate;
        private final Date loginTime;

        //Authorized role set---not the user's role set
        //The type of permission should inherit GrantedAuthority
        private final Collection<? extends GrantedAuthority> authorities; // required

   public JwtUser(Integer id, String username, String password,String email, Date lastPasswordResetDate,Date loginTime, Collection<? extends GrantedAuthority> authorities) {
       this.id = id;
       this.username = username;
       this.password = password;
       this.email = email;
       this.lastPasswordResetDate = lastPasswordResetDate;
       this.loginTime = loginTime;
       this.authorities = authorities;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.authorities;
   }

        @JsonIgnore //When serializing JwtUser, some attribute values ​​are not serialized, so you can add this annotation
   @Override
   public String getPassword() {
       return this.password;
   }

   @Override
   public String getUsername() {
       return this.username;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonLocked() {
       return true;
   }

   @JsonIgnore
   @Override
   public boolean isCredentialsNonExpired() {
       return true;
   }

   @JsonIgnore
   public Integer getId() {
       return id;
   }


   public String getEmail() {
       return email;
   }

   @JsonIgnore
   public Date getLastPasswordResetDate() {
       return lastPasswordResetDate;
   }

   public Date getLoginTime() {
       return loginTime;
   }

   @Override
	public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
   
}