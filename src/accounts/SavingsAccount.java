package accounts;

import java.time.LocalDate;

import customer.Customer;
import interest.Interval;

public class SavingsAccount extends BankAccount implements Accruable {
	private Interval interestInterval;
	private double interestEarned;
	
	public SavingsAccount(String accountID, Double initialBalance, Customer customer) {
		super(accountID, initialBalance, customer);
		
	}
	@Override
	public double getInterestPosted(int month, int year) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void getInterestPosted(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postInterest(double rate, Interval interval) {
		// TODO Auto-generated method stub
		
	}
	
	

}
