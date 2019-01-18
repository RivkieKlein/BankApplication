package fees;

import java.time.LocalDate;

public class IncurredFee extends Fee{
	
	private String accountID;
	private LocalDate feeDate;
	
	public IncurredFee(String accountID, FeeType type, double amount) {
		
		super(type, amount);
		
		this.accountID=accountID;
		
		
		feeDate=LocalDate.now();
	}

	public String getAccountID() {
		return accountID;
	}

	public LocalDate getFeeDate() {
		return feeDate;
	}
	

}
