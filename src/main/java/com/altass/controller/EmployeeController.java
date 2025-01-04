package com.altass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altass.entity.Employee;
import com.altass.entity.Project;
import com.altass.service.EmployeeService;
import com.altass.service.QueryFilter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.addEmployee(employee);
		log.info("Employee added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@PostMapping("/filter")
	public ResponseEntity<List<Employee>> getEmployees(@RequestBody QueryFilter filter) {
		List<Employee> employees = employeeService.getEmployees(filter);
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId,
			@RequestBody Employee updatedEmployee) {
		Employee employee = employeeService.updateEmployee(employeeId, updatedEmployee);
		log.info("Employee updated successfully");
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
		employeeService.deleteEmployee(employeeId);
		log.info("Employee deleted successfully");
		return ResponseEntity.ok("Employee deleted successfully");
	}

	@PostMapping("/{employeeId}/projects")
	public ResponseEntity<String> allocateProject(@PathVariable String employeeId, @RequestBody Project project) {
		employeeService.allocateProject(employeeId, project);
		log.info("Project allocated successfully");
		return ResponseEntity.ok("Project allocated successfully");
	}

	@PutMapping("/{employeeId}/projects")
	public ResponseEntity<String> unallocateProject(@PathVariable String employeeId, @RequestBody Project project) {
		employeeService.unallocateProject(employeeId, project);
		log.info("Project unallocated successfully");
		return ResponseEntity.ok("Project unallocated successfully");
	}
}
