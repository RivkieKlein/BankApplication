package interest;

public enum Interval {
	DAILY(365),
	MONTHLY(12),
	YEARLY(1);
	
	private int numTimes;
	
	private Interval(int numTimes){

	     this.numTimes = numTimes;
	}
	

	public int getTimes(){
		return numTimes;
	}
}
