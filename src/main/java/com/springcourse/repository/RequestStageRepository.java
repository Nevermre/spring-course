package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

	
	public List<RequestStage> findAllByRequestId(Long Id);
	public Page<RequestStage> findAllByRequestId(Long Id,Pageable pageable);

	
}
