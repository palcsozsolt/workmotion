package com.homework.workmotion.employee;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private UUID id;

	private String name;

	private int age;

	private String phone;

	private String email;

	private String address;

	// TODO: change it to enum
	private String state;

	private Employee(UUID id, String name, int age, String phone, String email, String address, String state) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.state = state;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

}
