package com.altass.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.altass.enums.CapabilityCentre;
import com.altass.enums.Designation;

import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee {

	@Id
    private String id;

    private String name; 

    private CapabilityCentre capabilityCentre; 

    private LocalDate dateOfJoining;

    private Designation designation;

    private String primarySkill; 

    private String secondarySkill;

    private Double overallExperience;
    
    @DBRef
    private List<Project> projects;
}
