package com.homework.workmotion.employee;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.homework.workmotion.generated.api.EmployeeRestApi;
import com.homework.workmotion.generated.model.EmployeeDetails;
import com.homework.workmotion.generated.model.EmployeeStateUpdate;
import com.homework.workmotion.generated.model.NewEmployee;

@RestController("/employee")
public class EmployeeController implements EmployeeRestApi {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Transactional
	@Override
	public ResponseEntity<EmployeeDetails> createEmployee(@Valid NewEmployee newEmployee) {

		LOG.info("New employee request {0}", newEmployee);

		EmployeeDetails result = employeeService.createEmployee(newEmployee);

		LOG.debug("Returning new employee {0}", result);
		return ResponseEntity.ok().body(result);
	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<EmployeeDetails> getEmployee(UUID employeeId) {

		LOG.debug("Read employee request {0}", employeeId);

		EmployeeDetails result = employeeService.readEmployee(employeeId);

		LOG.debug("Returning employee {0}", result);
		return ResponseEntity.ok().body(result);
	}

	@Transactional
	@Override
	public ResponseEntity<EmployeeDetails> updateEmployee(UUID employeeId, @Valid EmployeeStateUpdate employeeStateUpdate) {

		LOG.info("Update employee request {0}", employeeStateUpdate);

		EmployeeDetails result = employeeService.updateEmployeeState(employeeStateUpdate);

		LOG.debug("Returning employee {0}", result);
		return ResponseEntity.ok().body(result);
	}

}
