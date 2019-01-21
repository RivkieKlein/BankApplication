package transactions;

public class Deposit extends Transaction {
	private DepositType type;
	
	public Deposit(String accountID, double totalAmount, CheckDeposit[] checks) {
		
	}
	
	public Deposit(String accountID, double cash) {
		this.type=DepositType.CASH;
		this.accountID=accountID;
		this.transAmount=cash;
		
		
	}

	public Deposit(int accountID, double totalAmount, CheckDeposit[] checks, double cash) {
	
	}
	
	

}
