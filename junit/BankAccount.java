package activity;

public class BankAccount {

	private Integer balance;
	
	public BankAccount(Integer initialBalance) {
		balance = initialBalance;
	}
	
	public Integer withdraw(Integer amount) {
		
		if(balance<amount) {
			throw new NotEnoughFundsException(amount, balance);
			
		}
		balance -= amount;
		return balance;
	}
		
	public class NotEnoughFundsException extends RuntimeException{
	    public NotEnoughFundsException(Integer amount, Integer balance) {
	    	
	        super("Attempted to withdraw " + amount + " with a balance of " + balance);
		
	    }		
	 
	}
}
