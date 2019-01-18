package fees;

public class Fee {
	
	private double feeAmount;
	private FeeType feeType;
	
	public Fee(FeeType feeType, double amount) {
		this.feeAmount=amount;
		this.feeType=feeType;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public FeeType getFeeType() {
		return feeType;
	}

}
