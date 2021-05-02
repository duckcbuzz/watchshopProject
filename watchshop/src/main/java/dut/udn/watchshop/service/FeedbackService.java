package dut.udn.watchshop.service;

import java.util.List;
import java.util.Optional;

import dut.udn.watchshop.entity.Feedback;

public interface FeedbackService {
	List<Feedback> findAll();
	Optional<Feedback> findById(int id);
	void save (Feedback feedback);
	void deleteById (int id);
}
