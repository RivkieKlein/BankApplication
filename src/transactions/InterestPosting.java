package transactions;

import interest.Interval;

public class InterestPosting extends Transaction {
	private double interestRate;
	private double currentBalance;
	private Interval interval;
	
	public InterestPosting(double rate, double current, Interval interval){
		this.interestRate=rate;
		this.currentBalance=current;
		this.interval=interval;
		
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public Interval getInterval() {
		return interval;
	}

}
