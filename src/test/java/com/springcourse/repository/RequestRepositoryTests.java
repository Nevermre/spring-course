package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired private RequestRepository requestRepository;
	@Test
	@Order(1)
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null,"Novo Notebook","manda um novo note",
				new Date(),RequestState.OPEN,owner,null,null);
		Request createdRequest = requestRepository.save(request);
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	@Test
	@Order(2)
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(1L,"Novo Notebook2","manda um novo note2",
				null,RequestState.OPEN,owner,null,null);
		Request updatedRequest = requestRepository.save(request);
		assertThat(updatedRequest.getDescription()).isEqualTo("manda um novo note2");
		
	}
	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<Request> result =requestRepository.findById(1L);
		Request request = result.get();
		assertThat(request.getSubject()).isEqualTo("Novo Notebook");

	}
	@Test
	@Order(4)
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);

	}
	@Test
	@Order(5)
	public void listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(1);

	}
	@Test
	@Order(6)
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(3L, RequestState.IN_PGROGRESS);
		assertThat(affectedRows).isEqualTo(1);
	}
}
