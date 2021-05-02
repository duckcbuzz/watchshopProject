package dut.udn.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
