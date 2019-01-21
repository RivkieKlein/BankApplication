

public class MissingDataException extends RuntimeException{
	
	public MissingDataException(String s) {
		super(s);
	}
	
	public MissingDataException() {
		super("Missing Data");
	}
	
	

}
