package com.springcourse.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired private UserRepository userRepository;
	
	@Disabled
	@Order(1)
	public void saveTest() {
		User user = new User(null,"lucas","lucas@gmail.com","123",
				null,null,Role.ADMINISTRATOR);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1);
		
	}
	@Disabled
	@Order(2)
	public void updateTest() {
		User user = new User(1L,"lucas2","lucas@gmail.com","123",
				null,null,Role.ADMINISTRATOR);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("lucas2");

	}
	@Disabled
	@Order(3)
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		assertThat(user.getPassword()).isEqualTo("123");
	}
	@Disabled
	@Order(4)
	public void listTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1);
	}
	@Disabled
	@Order(5)
	public void loginTest() {
		Optional<User> result =	userRepository.login("lucas@gmail.com", "123");
		User loggedUser = result.get();
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(6)
	public void updateRoleTest() {
		int affectedRows = userRepository.updateRole(2L, Role.ADMINISTRATOR);
		assertThat(affectedRows).isEqualTo(1);
	}
}
