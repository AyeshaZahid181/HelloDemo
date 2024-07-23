package com.example.HelloDemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Transactional
@Data
@NoArgsConstructor

@Table(name = "employee")
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private long empId;
	
	  @NotBlank(message = "Name is required")
	    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Employee name must start with an uppercase letter and contain only letters")
	    private String empName;

	    @NotBlank(message = "Email is required")
	    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
	    private String empEmail;

	    @NotBlank(message = "Contact number is required")
	    @Pattern(regexp = "^\\d{11}$", message = "Contact number must be 11 digits")
	    private String empContact;


	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "fk_dep_id")
	private Department dep;
	
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpContact() {
		return empContact;
	}

	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}
}