package dut.udn.watchshop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.Order;
import dut.udn.watchshop.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrderInDetail(Order orderInDetail);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM order_detail WHERE id_order =:id_order",nativeQuery = true)
	void deteleByOrderInDetail(@Param("id_order") int id);
}
