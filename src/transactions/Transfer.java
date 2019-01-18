package transactions;

public class Transfer extends Transaction{
	
	private String toAccountID;
	
	public Transfer(String fromAccountID, String toAccountID, double amount) {
		transAmount=amount;
		this.toAccountID=toAccountID;
		accountID=fromAccountID;
		
	}
	
	public String getFromAccountID() {
		return accountID;
	}
	
	public String getToAccountID() {
		return toAccountID;
	}
	

}
