package accounts;

import java.util.ArrayList;

import customer.Customer;

public class CheckingAccount extends BankAccount {
	ArrayList<Check> checks;
	
	public CheckingAccount(String accountID, Double initialBalance, Customer customer){
		super(accountID, initialBalance, customer);
		
		checks= new ArrayList<Check>();
	}
	
	
	public void cashCheck(Check check) {
		checks.add(check);
	}

}
