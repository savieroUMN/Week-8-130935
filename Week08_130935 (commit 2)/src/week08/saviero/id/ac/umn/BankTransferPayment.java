package week08.saviero.id.ac.umn;

public class BankTransferPayment extends Payment {
	private String bankAccount;
	
	public BankTransferPayment (double amount, String bankAccount) {
		super(amount);
		this.bankAccount = bankAccount;
	}
	
	@Override
	void processPayment() {
		System.out.println("Processing bank transfer payment of $" + amount + " for bank account " + bankAccount);
	}
}
