package com.springcourse.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestStageSavedto {

	@NotBlank
	private String description;
	
	@NotNull(message="Estado obrigatorio")
	private RequestState state;
	
	@NotNull(message = "Pedido obrigatorio")
	private Request request;
	
	@NotNull(message = "Owner obrigatorio")
	private User owner;
	
	public RequestStage transformToRequestStage() {
		RequestStage stage = new RequestStage(null, this.description,
				null,this.state,this.request,this.owner);
		return stage;
	}
	
	
}
