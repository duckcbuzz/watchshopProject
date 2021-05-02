package dut.udn.watchshop.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User orderUser;
	private Date dateOrder;
	private String address;
	private String phone;
	private String receiver;
	private String note;
	@JsonIgnore
	@OneToMany(mappedBy = "orderInDetail",fetch = FetchType.EAGER)
	private Set<OrderDetail> listItem;
	
}
