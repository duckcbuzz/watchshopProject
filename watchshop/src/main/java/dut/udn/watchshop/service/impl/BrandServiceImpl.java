package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Brand;
import dut.udn.watchshop.repository.BrandRepository;
import dut.udn.watchshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> findAll() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}

	@Override
	public Optional<Brand> findById(int id) {
		// TODO Auto-generated method stub
		return brandRepository.findById(id);
	}

	@Override
	public void save(Brand brand) {
		// TODO Auto-generated method stub
		brandRepository.save(brand);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		brandRepository.deleteById(id);
	}
}
