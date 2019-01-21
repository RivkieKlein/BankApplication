package checks;

import java.time.LocalDate;

public class Check {
	private String accountID;
	private double amount;
	private int checkNum;
	private String description;
	private LocalDate dateProcessed;
	
	public Check(String accountID, double amount, int checkNum, String description, LocalDate dateProcessed) {
		this.accountID=accountID;
		this.amount=amount;
		this.checkNum=checkNum;
		this.description = description;
		this.dateProcessed = dateProcessed;
	}

	public String getAccountID() {
		return accountID;
	}

	public double getAmount() {
		return amount;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDateProcessed() {
		return dateProcessed;
	}
	
	
}
