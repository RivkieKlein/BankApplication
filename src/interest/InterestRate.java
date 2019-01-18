package interest;

import java.time.LocalDate;

public class InterestRate {
	
	private double rate;
	private LocalDate rateSetDate;
	private Interval interval;
	
	private InterestRate(double rate, LocalDate rateSetDate) {
		this.rate=rate;
		this.rateSetDate=rateSetDate;
	}

	public double getRate() {
		return rate;
	}

	public LocalDate getRateSetDate() {
		return rateSetDate;
	}

}
