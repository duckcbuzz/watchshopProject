package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Color;

public interface ColorService {
	List<Color> findAll();
	Optional<Color> findById(int id);
	void save (Color color);
	void deleteById (int id);
}
