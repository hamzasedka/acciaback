package com.modis.acciaback.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.modis.acciaback.model.ResetPasswordToken;
import com.modis.acciaback.model.User;

@SpringBootTest
class ResetPasswordTokenRepositoryTest {

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;
	
	@Autowired
	private UserRepository userRepository;

	/*@Test
	void testFindByToken() {
		ResetPasswordToken rs = resetPasswordTokenRepository.findByToken("1d4e4c4c-dd2d-4ef6-9921-2f2a1221784d");
		assertEquals(rs.getToken(), "1d4e4c4c-dd2d-4ef6-9921-2f2a1221784d");
	}

	@Test
	void testNotFindByToken() {
		ResetPasswordToken rs = resetPasswordTokenRepository.findByToken("144111");
		assertNull(rs);
	}
	
	/*@Test
	void testFindByUser() {
		User user = userRepository.findByEmail("reset@gmail.com");
		
		ResetPasswordToken rs = resetPasswordTokenRepository.findByUser(user);
		assertEquals(rs.getToken(), "16213761-ee48-4a92-919f-8e1558cd78ac");
	}
*/
}
