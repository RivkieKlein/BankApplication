package staff;


public class InputDataException extends RuntimeException {
	public InputDataException(String s) {
		super(s);
	}
	
	public InputDataException() {
		super("Wrong data inputed");
	}

}
