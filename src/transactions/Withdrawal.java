package transactions;

public class Withdrawal extends Transaction {
	
	public Withdrawal(String accountID, double amount) {
		this.accountID=accountID;
		type = TransType.WITHDRAWAL;
		this.transAmount=amount;
		
	}

}
