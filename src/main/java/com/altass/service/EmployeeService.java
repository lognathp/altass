package com.altass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.altass.entity.Employee;
import com.altass.entity.Project;
import com.altass.exception.BadRequestException;
import com.altass.exception.ResourceNotFoundException;
import com.altass.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void allocateProject(String employeeId, Project project) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

		List<Project> projects = Optional.ofNullable(employee.getProjects())
                .orElse(new ArrayList<>());
		if (projects.size() >= 3) {
			log.error("Employee already allocated to 3 projects");
			throw new BadRequestException("Employee already allocated to 3 projects");
		}

		projects.add(project);
		employee.setProjects(projects);
		employeeRepository.save(employee);
	}

	public void unallocateProject(String employeeId, Project project) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

		List<Project> projects = employee.getProjects();
		boolean projectRemoved = projects.removeIf(p -> p.getId().equals(project.getId()));

		if (!projectRemoved) {
			log.error("Project not found in employee's allocations");
			throw new ResourceNotFoundException("Project not found in employee's allocations");
		}
		employeeRepository.save(employee);
	}

	public List<Employee> getEmployees(QueryFilter filter) {
		Query query = filter.buildQuery();
		return mongoTemplate.find(query, Employee.class);
	}
	
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String employeeId, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(updatedEmployee.getName());
        employee.setCapabilityCentre(updatedEmployee.getCapabilityCentre());
        employee.setDateOfJoining(updatedEmployee.getDateOfJoining());
        employee.setDesignation(updatedEmployee.getDesignation());
        employee.setPrimarySkill(updatedEmployee.getPrimarySkill());
        employee.setSecondarySkill(updatedEmployee.getSecondarySkill());
        employee.setOverallExperience(updatedEmployee.getOverallExperience());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
	
	
}
