package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.repository.ClockWorkRepository;
import dut.udn.watchshop.service.ClockWorkService;

@Service
public class ClockWorkServiceImpl implements ClockWorkService{
	@Autowired
	private ClockWorkRepository clockWorkRepository;

	@Override
	public List<ClockWork> findAll() {
		// TODO Auto-generated method stub
		return clockWorkRepository.findAll();
	}

	@Override
	public Optional<ClockWork> findById(int id) {
		// TODO Auto-generated method stub
		return clockWorkRepository.findById(id);
	}

	@Override
	public void save(ClockWork clockWork) {
		// TODO Auto-generated method stub
		clockWorkRepository.save(clockWork);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		clockWorkRepository.deleteById(id);
	}
	
}
