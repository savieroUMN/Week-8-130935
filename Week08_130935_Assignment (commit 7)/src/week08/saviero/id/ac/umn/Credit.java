package week08.saviero.id.ac.umn;

public class Credit extends Payment {
	private int installment;
	private int maxInstallmentAmount;
	
	public Credit(Item item, int maxInstallmentAmount) {
		super(item);
		this.maxInstallmentAmount = maxInstallmentAmount;
		this.installment = 0;
	}
	
	public int pay() {
		if (isPaidOff) {
			return 0;
		}
		
		int installmentValue = item.getPrice() / maxInstallmentAmount;
		installment++;
		
		if (installment >= maxInstallmentAmount) {
			isPaidOff = true;
		}
		
		return installmentValue;
	}

	@Override
	public int getRemainingAmount() {
		if (isPaidOff) {
			return 0;
		}
		int paidAmount = (item.getPrice() / maxInstallmentAmount) * installment;
		return item.getPrice() - paidAmount;
	}
	
	public String getClassName() {
		return "CREDIT";
	}
	
	public int getInstallment() {
		return installment;
	}
	
	public int getMaxInstallmentAmount() {
		return maxInstallmentAmount;
	}
}
