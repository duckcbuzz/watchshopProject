package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.AlbertType;

public interface AlbertTypeService {
	List<AlbertType> findAll();
	Optional<AlbertType> findById(int id);
	void save (AlbertType albertType);
	void deleteById (int id);
}
