package Classes;

public class Visa extends CreditCard {

	// Concrete
	Visa(String cardNumber, String name, String expDate, String security) {
		super(cardNumber, name, expDate, security);
	}
	
	@Override
	public String getType() {
		return "Visa";
	}
}
