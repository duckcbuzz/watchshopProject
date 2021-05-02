package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.ClockWork;

public interface ClockWorkService {
	List<ClockWork> findAll();
	Optional<ClockWork> findById(int id);
	void save (ClockWork clockWork);
	void deleteById (int id);
}
