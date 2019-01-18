package accounts;

import java.time.LocalDate;

import customer.Customer;
import interest.InterestRate;
import interest.Interval;

public class CDAccount extends SavingsAccount {
	private LocalDate maturityDate;
	private InterestRate interestRate;
	
	public CDAccount(String accountID, Double initialBalance, Customer customer) {
		super(accountID, initialBalance, customer);
		// TODO Auto-generated constructor stub
	}

	

}
