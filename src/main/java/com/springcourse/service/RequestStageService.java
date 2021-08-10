package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired private RequestStageRepository requestStageRepository;
	@Autowired private RequestRepository repository;
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createdStage = requestStageRepository.save(stage);
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		repository.updateStatus(requestId, state);
		return createdStage;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.get();
	}
	
	public List<RequestStage> listAllByRequestId(Long id){
		List<RequestStage> requestStages = requestStageRepository
				.findAllByRequestId(id);
				
		return requestStages;
	}
	
	
}
