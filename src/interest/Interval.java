package interest;

public enum Interval {
	Daily(365),
	Monthly(12),
	Yearly(1);
	
	private int numTimes;
	
	private Interval(int numTimes){

	     this.numTimes = numTimes;
	}
	

	public int getTimes(){
		return numTimes;
	}
}
