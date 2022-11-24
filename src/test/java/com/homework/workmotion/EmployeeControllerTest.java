package com.homework.workmotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.homework.workmotion.employee.Employee;
import com.homework.workmotion.employee.EmployeeRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
public class EmployeeControllerTest {

	private TestRestTemplate restTemplate;

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeControllerTest(TestRestTemplate restTemplate, EmployeeRepository employeeRepository) {
		this.restTemplate = restTemplate;
		this.employeeRepository = employeeRepository;
	}

	@Given("there is no employee in the database")
	public void clearEmployeeDatabase() {
		employeeRepository.deleteAll();
	}

	@When("create an employee")
	public void createEmployee() {
		String requestPath = "/employee";
	}

	@When("update state of the employee to {string}")
	public void updateEmployeeState(String newState) {
		String requestPath = "/employee/" + getEmployeeId();
	}

	@When("update substate of {string} state the employee to {string}")
	public void updateEmployeeState(String substate, String newState) {
		String requestPath = "/employee/" + getEmployeeId();
	}

	@When("update state of the employee to {string} from {string} is not allowed")
	public void updateEmployeeStateFail(String wrongState, String currentState) {
		String requestPath = "/employee/" + getEmployeeId();
	}

	@Then("exactly one employee should exist")
	public void oneEmployeeExists() {
		String requestPath = "/employee/" + getEmployeeId();
		ResponseEntity<Employee> response = restTemplate.getForEntity(requestPath, Employee.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Then("employee is auto-transitioned to {string} state")
	public void autoTransitionedEmployeeState(String newState) {
		String requestPath = "/employee/" + getEmployeeId();
	}

	/**
	 * Reading the employee id from the database. The assumption is to have only one employee in the database.
	 * 
	 * The purpose is to eliminate employee id (state) shared between the test methods.
	 * 
	 * @return
	 */
	private UUID getEmployeeId() {
		return employeeRepository.findAll().get(0).getId();
	}
}
