package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.AlbertType;
import dut.udn.watchshop.entity.Brand;
import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.entity.Color;
import dut.udn.watchshop.entity.Watch;

public interface WatchService {
	List<Watch> findAll();
	List<Watch> findByColor(Color color);
	List<Watch> findByBrand(Brand brand);
	List<Watch> findByAlbertType(AlbertType albertType);
	List<Watch> findByClockWork(ClockWork clockWork);
	Optional<Watch> findById(int id);
	void save (Watch watch);
	void deleteById (int id);
	
}
