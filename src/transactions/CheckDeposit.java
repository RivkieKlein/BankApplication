package transactions;

public class CheckDeposit {
	private int checkNum;
	private String accountID;
	private String bankRoutingID;
	private CheckStatus checkStatus;
	
	public CheckDeposit(int checkNum, String accountID, String bankRoutingID, CheckStatus checkStatus) {
		
		this.checkNum=checkNum;
		this.accountID=accountID;
		this.bankRoutingID=bankRoutingID;
		this.checkStatus=checkStatus;
		
	}

	public int getCheckNum() {
		return checkNum;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getBankRoutingID() {
		return bankRoutingID;
	}

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

}
