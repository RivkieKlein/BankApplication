package transactions;

import java.time.LocalDate;

public class Transaction {
	
	private static long lastTransactionID=0;
	protected String accountID;
	protected double transAmount;
	private LocalDate transDate;
	private long transID;
	protected TransType type;
	
	public Transaction() {
		transDate=LocalDate.now();
		transID=lastTransactionID+1;
		lastTransactionID=transID;
	}

	public String getAccountID() {
		return accountID;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public long getTransID() {
		return transID;
	}

	public TransType getType() {
		return type;
	}
	
	
	

}
