package dut.udn.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.AlbertType;

@Repository
public interface AlbertTypeRepository extends JpaRepository<AlbertType, Integer>{

}
