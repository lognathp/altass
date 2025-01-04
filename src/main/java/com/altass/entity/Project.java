package com.altass.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.altass.enums.Account;

import lombok.Data;

@Data
@Document(collection = "project")
public class Project {

	@Id
    private String id; 
	
    private String projectName; 

	private Account account; 

    private Float allocation; 

    private LocalDate projectStartDate; 

    private LocalDate projectEndDate; 

    private String remarks; 
}
