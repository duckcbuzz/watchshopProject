package dut.udn.watchshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "watch")
public class Watch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer price;
	private String image;
	private Integer amount;
	private boolean sex;
	private String size;
	@ManyToOne
	@JoinColumn(name = "id_brand")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name = "id_color")
	private Color color;
	@ManyToOne
	@JoinColumn(name = "id_albert")
	private AlbertType albertType;
	@ManyToOne
	@JoinColumn(name = "id_clockwork")
	private ClockWork clockwork;
	@ManyToOne
	@JoinColumn(name = "id_color_albert")
	private Color colorAlbert;
	public Watch(Integer id) {
		super();
		this.id = id;
	}
}
