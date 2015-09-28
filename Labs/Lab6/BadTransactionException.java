public class BadTransactionException extends Exception {
	public BadTransactionException() {
		super("Invalid amount!");
	}
}