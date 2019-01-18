package staff;

public class Administrator extends Teller {
	
	private String adminPWord;
	
	public Administrator(String employeeID,String firstname, String lastname,String login, String password) {
		super(employeeID, firstname, lastname, login, password);
	}
	
	public Administrator(String employeeID,String firstname, String lastname,String login, String password, String adminPWord) {
		super(employeeID, firstname, lastname, login, password);
		this.adminPWord=adminPWord;
	}

}
