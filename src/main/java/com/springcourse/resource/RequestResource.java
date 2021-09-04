package com.springcourse.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.dto.RequestSavedto;
import com.springcourse.dto.RequestUpdateDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.AccessManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService requestStageService;
	@Autowired private AccessManager accessManager;
	@PostMapping 
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSavedto requestdto){
//		Request createdRequest = requestService.save(request);
		Request request = requestdto.transformToRequest();
		return ResponseEntity.status(HttpStatus.CREATED).body(request);
		
	}
	
	
	@PreAuthorize("@accessManager.isRequestOwner(#id)")
	@PutMapping("/{id}")
	
	public ResponseEntity<Request> update(@PathVariable(name="id") Long id,
			@RequestBody @Valid RequestUpdateDto requestdto){
		Request request = requestdto.transformToRequest();
		request.setId(id);
		Request updatedRequest = requestService.update(request);
		return ResponseEntity.ok(updatedRequest);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name="id") Long id){
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		//List<User> users = userService.listAll();
		
		PageRequestModel pr = new PageRequestModel(page,size);
		PageModel<Request> pm = requestService.listAllOnLazyMode( pr);
		return ResponseEntity.ok(pm);
	}
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById
	(@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
//		List<RequestStage> stage = requestStageService.listAllByRequestId(id);
		PageRequestModel pr = new PageRequestModel(page,size);

		PageModel<RequestStage> pm = requestStageService.listAllByRequestIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}

}
