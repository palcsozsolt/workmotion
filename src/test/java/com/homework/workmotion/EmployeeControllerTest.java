package com.homework.workmotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.homework.workmotion.employee.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.profiles.active=test" })
class EmployeeControllerTest {

	private TestRestTemplate restTemplate;

	@Autowired
	public EmployeeControllerTest(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Test
	void getEmployee() {
		UUID employeeId = UUID.randomUUID();
		String requestPath = "/employee/" + employeeId;
		ResponseEntity<Employee> response = restTemplate.getForEntity(requestPath, Employee.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void createEmployee() {

	}

	@Test
	void updateEmployee() {

	}
}
