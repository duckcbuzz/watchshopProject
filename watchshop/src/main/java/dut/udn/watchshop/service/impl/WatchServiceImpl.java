package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.AlbertType;
import dut.udn.watchshop.entity.Brand;
import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.entity.Color;
import dut.udn.watchshop.entity.Watch;
import dut.udn.watchshop.repository.WatchRepository;
import dut.udn.watchshop.service.WatchService;

@Service
public class WatchServiceImpl implements WatchService{
	@Autowired
	private WatchRepository watchRepository;
	@Override
	public List<Watch> findAll() {
		// TODO Auto-generated method stub
		return watchRepository.findAll();
	}

	@Override
	public Optional<Watch> findById(int id) {
		// TODO Auto-generated method stub
		return watchRepository.findById(id);
	}

	@Override
	public void save(Watch watch) {
		// TODO Auto-generated method stub
		watchRepository.save(watch);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		watchRepository.deleteById(id);
	}

	@Override
	public List<Watch> findByAlbertType(AlbertType albertType) {
		// TODO Auto-generated method stub
		return watchRepository.findByAlbertType(albertType);
	}

	@Override
	public List<Watch> findByColor(Color color) {
		// TODO Auto-generated method stub
		return watchRepository.findByColor(color);
	}

	@Override
	public List<Watch> findByBrand(Brand brand) {
		// TODO Auto-generated method stub
		return watchRepository.findByBrand(brand);
	}

	@Override
	public List<Watch> findByClockWork(ClockWork clockWork) {
		// TODO Auto-generated method stub
		return watchRepository.findByClockWork(clockWork);
	}


}
