package dut.udn.watchshop.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "color")
public class Color {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String color;
	@JsonIgnore
	@OneToMany(mappedBy = "color",fetch = FetchType.LAZY)
	private Set<Watch> productColor;
	@JsonIgnore
	@OneToMany(mappedBy = "colorAlbert",fetch = FetchType.LAZY)
	private Set<Watch> productColorAlbert;
	public Color(Integer id) {
		super();
		this.id = id;
	}
	
}
