package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.AlbertType;
import dut.udn.watchshop.repository.AlbertTypeRepository;
import dut.udn.watchshop.service.AlbertTypeService;

@Service
public class AlbertTypeServiceImpl implements AlbertTypeService{
	@Autowired
	AlbertTypeRepository albertTypeRepository; 
	
	@Override
	public List<AlbertType> findAll() {
		// TODO Auto-generated method stub
		return albertTypeRepository.findAll();
	}

	@Override
	public Optional<AlbertType> findById(int id) {
		// TODO Auto-generated method stub
		return albertTypeRepository.findById(id);
	}

	@Override
	public void save(AlbertType albertType) {
		// TODO Auto-generated method stub
		albertTypeRepository.save(albertType);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		albertTypeRepository.deleteById(id);
	}

}
