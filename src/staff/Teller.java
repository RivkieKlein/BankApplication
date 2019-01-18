package staff;

public class Teller extends Employee{

	private String loginID;
	private String password;
	
	//initializes teller without login
	public Teller(String tellerID, String first, String last) {
		
		super(tellerID, first, last);
		
	}
	
	//initializes teller with login
	public Teller(String tellerID, String first, String last, String loginID, String password) {
		
		super(tellerID, first, last);
		
		this.loginID=loginID;
		this.password=password;
		
	}
	
	public String  getLoginID() {
		return loginID;
	}
	
	//will throw invalid data exception if current is not valid
	public void setLoginID(String current, String newID) {
		
		if(loginID.equals(current)) {
			loginID=newID;
		}
		else {
			throw new InvalidDataException("Current login ID doesnt match the one in our system. Cannot reset");
		}
		
	}
	
	//will throw invalid data exception if olds dont match
	//or if new is not valid
	public void resetPassword(String oldP, String newP) {
		if(password.equals(oldP)) {
			password=newP;
		}
		else {
			throw new InvalidDataException("Passwords do not match. Cannot reset");
		}
	}
}
