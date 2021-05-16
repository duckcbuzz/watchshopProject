package dut.udn.watchshop.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private String phone;
	private String email;
	private Date birthday;
	private String address;
	@JsonIgnore
	@OneToMany(mappedBy = "cartUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<CartDetail> listCart;
	@JsonIgnore
	@OneToMany(mappedBy = "orderUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Order> listOrder;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", 
	  joinColumns = @JoinColumn(name = "id_user",referencedColumnName="id"), 
	  inverseJoinColumns = @JoinColumn(name = "id_role",referencedColumnName="id"))
	private Set<Role> roles = new HashSet<>();
	public User(Integer id) {
		super();
		this.id = id;
	}
	
}
