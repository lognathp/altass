package com.altass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altass.entity.Project;
import com.altass.exception.ResourceNotFoundException;
import com.altass.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project getProjectById(String projectId) {
		return projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
	}

	public Project updateProject(String projectId, Project updatedProject) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		project.setAccount(updatedProject.getAccount());
		project.setProjectName(updatedProject.getProjectName());
		project.setAllocation(updatedProject.getAllocation());
		project.setProjectStartDate(updatedProject.getProjectStartDate());
		project.setProjectEndDate(updatedProject.getProjectEndDate());
		project.setRemarks(updatedProject.getRemarks());

		return projectRepository.save(project);
	}

	public void deleteProject(String projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		projectRepository.delete(project);
	}
}
