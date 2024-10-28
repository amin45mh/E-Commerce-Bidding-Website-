package Classes;

// Concrete
public class MasterCard extends CreditCard {

	// Concrete
	MasterCard(String cardNumber, String name, String expDate, String security) {
		super(cardNumber, name, expDate, security);
	}
	@Override
	public String getType() {
		return "MasterCard";
	}
	
}
