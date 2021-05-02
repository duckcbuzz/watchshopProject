/**
 * 
 */
package dut.udn.watchshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.AlbertType;
import dut.udn.watchshop.entity.Brand;
import dut.udn.watchshop.entity.ClockWork;
import dut.udn.watchshop.entity.Color;
import dut.udn.watchshop.entity.Watch;

/**
 * @author Admin
 *
 */
@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer>{
	List<Watch> findByAlbertType(AlbertType albertType);
	List<Watch> findByColor(Color color);
	List<Watch> findByBrand(Brand brand);
	 @Query("select w from Watch w where w.clockwork = ?1")
	List<Watch> findByClockWork(ClockWork clockWork);
}
