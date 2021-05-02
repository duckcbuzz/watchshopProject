package dut.udn.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer>{

}
