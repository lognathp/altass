package com.altass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.altass.entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
