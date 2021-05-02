package dut.udn.watchshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_order")
	private Order orderInDetail;
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Watch itemOrder;
	private Integer quantity;
	@JsonIgnore
	@OneToOne(mappedBy = "orderDetail")
	private Feedback feedback;
}
