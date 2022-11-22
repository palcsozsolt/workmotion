package com.homework.workmotion.employee;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.homework.workmotion.exception.RestExceptionHandler;
import com.homework.workmotion.generated.api.EmployeeRestApi;
import com.homework.workmotion.generated.dto.EmployeeDetails;

@RestController("/employee")
public class EmployeeController implements EmployeeRestApi {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Transactional
	@Override
	public ResponseEntity<EmployeeDetails> createEmployee(@Valid EmployeeDetails employee) {

		LOG.info("New employee request {0}", employee.toString());

		EmployeeDetails result = employeeService.createEmployee(employee);

		LOG.debug("Returning new employee {0}", result.toString());
		return ResponseEntity.ok().body(result);
	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<EmployeeDetails> getEmployee(UUID employeeId) {

		LOG.debug("Read employee request {0}", employeeId);

		EmployeeDetails result = employeeService.readEmployee(employeeId);

		LOG.debug("Returning employee {0}", result.toString());
		return ResponseEntity.ok().body(result);
	}

	@Transactional
	@Override
	public ResponseEntity<EmployeeDetails> updateEmployee(UUID employeeId, @Valid EmployeeDetails employee) {

		LOG.info("Update employee request {0}", employee.toString());

		EmployeeDetails result = employeeService.updateEmployee(employee);

		LOG.debug("Returning employee {0}", result.toString());
		return ResponseEntity.ok().body(result);
	}

}
