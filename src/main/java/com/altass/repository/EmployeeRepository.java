package com.altass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altass.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
