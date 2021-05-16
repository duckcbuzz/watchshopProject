package dut.udn.watchshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findByOrderUser(User orderUser);
}
