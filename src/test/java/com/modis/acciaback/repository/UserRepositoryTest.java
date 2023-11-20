package com.modis.acciaback.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.modis.acciaback.model.User;
import com.modis.acciaback.model.UserRole;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void testExistsByUsername() {
		boolean isExist = userRepository.existsByUsername("alex");
		assertTrue(isExist);
	}
	
	@Test
	void testNotExistsByUsername() {
		boolean isExist = userRepository.existsByUsername("jojo");
		assertTrue(!isExist);
	}

	@Test
	void testExistsByEmail() {
		boolean isExist = userRepository.existsByEmail("test@gmail.com");
		assertTrue(isExist);
	}

	@Test
	void testNotExistsByEmail() {
		boolean isExist = userRepository.existsByEmail("jojo@test.com");
		assertTrue(!isExist);
	}

	@Test
	void testFindByEmail() {
		User rs = userRepository.findByEmail("test@gmail.com");
		assertEquals(rs.getEmail(), "test@gmail.com");
		//assertEquals(rs.getNom(), "user03");
		//assertEquals(rs.getPrenom(), "user03");
		assertEquals(rs.getUsername(), "alex");
		//assertEquals(rs.getRole(), UserRole.USER);
	
	}

	@Test
	void testFindAvailableAdmins() {
		List<User> rs = userRepository.findAvailableAdmins();
		assertEquals(rs, new ArrayList<User>());
	}

}
