package com.homework.workmotion.employee;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
