package dut.udn.watchshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_detail")
public class CartDetail {
	@Id
	@GeneratedValue
	private Integer id;
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User cartUser;
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name = "id_watch")
	private Watch itemCart;
	private Integer quantity;

}
