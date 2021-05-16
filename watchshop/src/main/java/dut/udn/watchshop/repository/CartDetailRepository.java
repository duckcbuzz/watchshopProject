package dut.udn.watchshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.CartDetail;
import dut.udn.watchshop.entity.User;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer>{
	List<CartDetail> findByCartUser(User cartUser);
	
	@Query(value = "SELECT c.id FROM cart_detail AS c WHERE c.id_user = ?1 AND c.id_watch = ?2",nativeQuery = true)
	Integer isExist(int id_user, int id_watch);

}
