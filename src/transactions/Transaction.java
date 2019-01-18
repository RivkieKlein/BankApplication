package transactions;

import java.time.LocalDate;

public class Transaction {
	
	private long lastTransactionID;
	private String accountID;
	private double transAmount;
	private LocalDate transDate;
	private long transID;
	private TransType type;
	
	public Transaction() {
		
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
