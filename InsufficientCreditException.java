package p2;

public class InsufficientCreditException extends Exception {
	public InsufficientCreditException(){
		
	}
	
	public InsufficientCreditException(String message){
		super(message);
	}
}
