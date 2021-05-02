package dut.udn.watchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dut.udn.watchshop.entity.Feedback;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
