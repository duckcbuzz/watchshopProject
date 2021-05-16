package dut.udn.watchshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dut.udn.watchshop.entity.Feedback;
import dut.udn.watchshop.repository.FeedbackRepository;
import dut.udn.watchshop.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Override
	public List<Feedback> findAll() {
		// TODO Auto-generated method stub
		return feedbackRepository.findAll();
	}

	@Override
	public Optional<Feedback> findById(int id) {
		// TODO Auto-generated method stub
		return feedbackRepository.findById(id);
	}

	@Override
	public void save(Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackRepository.save(feedback);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		feedbackRepository.deleteById(id);
	}
	@Autowired
	private FeedbackRepository feedbackRepository;
}
