package Classes;

public abstract class CreditCard {
	private String cardNumber;
	private String name;
	private String expDate;
	private String security;
	
	protected CreditCard(String cardNumber, String name, String expDate, String security) {
		this.cardNumber = cardNumber;
		this.name = name;
		this.expDate = expDate;
		this.security = security;
	}
	
	public abstract String getType();
}
