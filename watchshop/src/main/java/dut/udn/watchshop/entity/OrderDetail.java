package dut.udn.watchshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_order")
	private Order orderInDetail;
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Watch itemOrder;
	private Integer quantity;
	@OneToOne(mappedBy = "orderDetail")
	private Feedback feedback;
}
