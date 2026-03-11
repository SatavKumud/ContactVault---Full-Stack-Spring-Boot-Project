package com.scm.Secure.Multi_User.Contact.Management.System;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.Secure.Multi_User.Contact.Management.System.services.EmailService;

@SpringBootTest
class SecureMultiUserContactManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest() {
		service.sendEmail("kumud.satav@gmail.com", "Testing email service",
				"This is to check if email can be send  or not in project.");

	}

}
