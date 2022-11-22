package com.homework.workmotion.employee;

import java.util.UUID;

import com.homework.workmotion.generated.dto.EmployeeDetails;

public interface EmployeeService {

	/**
	 * Create new employee
	 * 
	 * @param employee
	 * @return
	 */
	public EmployeeDetails createEmployee(EmployeeDetails employee);

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
	public EmployeeDetails updateEmployee(EmployeeDetails employee);
}
