package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Brand;

public interface BrandService {
	List<Brand> findAll();
	Optional<Brand> findById(int id);
	void save (Brand brand);
	void deleteById (int id);
}
