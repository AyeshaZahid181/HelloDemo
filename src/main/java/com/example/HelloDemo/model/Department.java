package com.example.HelloDemo.model;

import javax.validation.constraints.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor

@Table(name = "department")
public class Department{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dep_id")
	private long depId;
	
	// Department name should start with an uppercase letter and contain only letters and spaces
   // @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Department name must start with an uppercase letter and contain only letters")
    private String depName;

    // Department head should start with an uppercase letter and contain only letters and spaces
  //  @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Department head must start with an uppercase letter and contain only letters")
    private String depHead;
	
	public long getDepId() {
		return depId;
	}
	public void setDepId(long depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepHead() {
		return depHead;
	}
	public void setDepHead(String depHead) {
		this.depHead = depHead;
	}
	
}
