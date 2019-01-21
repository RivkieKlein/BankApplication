package accounts;

import java.time.LocalDate;

import interest.InterestRate;
import interest.Interval;

public interface Accruable {
	
	public double getInterestPosted(int month, int year);
	
	public void getInterestPosted(LocalDate startDate, LocalDate endDate);
	
	public void postInterest(InterestRate rate, Interval interval);
	
	public Interval getInterval();

}
