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

import com.altass.entity.Project;
import com.altass.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project savedProject = projectService.addProject(project);
        log.info("Project added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project updatedProject) {
        Project project = projectService.updateProject(projectId, updatedProject);
        log.info("Project updated successfully");
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
        log.info("Project deleted successfully");
        return ResponseEntity.ok("Project deleted successfully");
    }
}
