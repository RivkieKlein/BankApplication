package staff;

import java.time.LocalDate;

public class Employee {
	
	private String employeeID;
	private String firstName ;
	private String lastName;
	private LocalDate hireDate;
	
	public Employee(String employeeID, String firstName, String lastName) {
		this.employeeID=employeeID;
		this.firstName=firstName;
		this.lastName=lastName;
		
		hireDate=LocalDate.now();
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}
	

}
