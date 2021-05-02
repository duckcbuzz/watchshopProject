package dut.udn.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.ClockWork;

@Repository
public interface ClockWorkRepository extends JpaRepository<ClockWork, Integer>{

}
