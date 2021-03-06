package accounts;

import java.time.LocalDate;

import customer.Customer;
import interest.InterestRate;
import interest.Interval;
import transactions.InterestPosting;

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
	
	public void postInterest(double rate, Interval interval) {
		rate = rate/100;
		double earned;
		earned = currentBalance*rate;
		currentBalance+=earned;
		
		//the current balane right after interest was posted
		transactions.add(new InterestPosting(rate, currentBalance, interval));
		
	}
	
	@Override
	public void postInterest(InterestRate rate, Interval interval) {

		postInterest(rate.getRate(), interval);
		
	}
	@Override
	public Interval getInterval() {
		
		return interestInterval;
	}
	
	

}
