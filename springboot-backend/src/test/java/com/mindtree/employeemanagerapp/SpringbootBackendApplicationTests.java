package com.mindtree.employeemanagerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;
import com.mindtree.employeemanagerapp.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootBackendApplicationTests {

	@Autowired
	EmployeeService employeeService;

	@MockBean
	EmployeeRepository employeeRepository;

	@Test
	public void getEmployeesTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee("smit", "mehta", "test@gmail.com"), new Employee("ram", "patel", "demo@gmail.com"))
				.collect(Collectors.toList()));
		assertEquals(2,employeeService.getAllEmployees().size());
	}

	@Test
	public void addEmployeeTest() {
		Employee employee = new Employee("Smit", "Mehta", "test@gmail.com");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.createEmployee(employee));
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee employee = new Employee("Smit", "Mehta", "test@gmail.com");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee.getFirstName(),employeeRepository.save(employee).getFirstName());
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee("Smit", "Mehta", "test@gmail.com");
		employeeRepository.delete(employee);
		verify(employeeRepository, times(1)).delete(employee);
	}
}
