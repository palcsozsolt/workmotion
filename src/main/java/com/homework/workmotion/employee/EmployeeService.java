package com.homework.workmotion.employee;

import java.util.UUID;

import com.homework.workmotion.generated.model.EmployeeDetails;
import com.homework.workmotion.generated.model.EmployeeStateUpdate;
import com.homework.workmotion.generated.model.NewEmployee;

public interface EmployeeService {

	/**
	 * Create new employee
	 * 
	 * @param newEmployee
	 * @return
	 */
	public EmployeeDetails createEmployee(NewEmployee newEmployee);

	/**
	 * Find employee by id
	 * 
	 * @param employeeId
	 * @return
	 */
	public EmployeeDetails readEmployee(UUID employeeId);

	/**
	 * Update employee
	 * 
	 * @param employee
	 * @return
	 */
	public EmployeeDetails updateEmployeeState(EmployeeStateUpdate employee);
}
