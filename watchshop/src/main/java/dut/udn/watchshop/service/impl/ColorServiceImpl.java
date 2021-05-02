package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Color;
import dut.udn.watchshop.repository.ColorRepository;
import dut.udn.watchshop.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{
	@Autowired
	private ColorRepository colorRepository;
	@Override
	public List<Color> findAll() {
		// TODO Auto-generated method stub
		return colorRepository.findAll();
	}

	@Override
	public Optional<Color> findById(int id) {
		// TODO Auto-generated method stub
		return colorRepository.findById(id);
	}

	@Override
	public void save(Color color) {
		// TODO Auto-generated method stub
		colorRepository.save(color);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		colorRepository.deleteById(id);
	}

}
