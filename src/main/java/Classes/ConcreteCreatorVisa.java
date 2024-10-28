package Classes;

public class ConcreteCreatorVisa extends CardCreator {
	@Override
	public CreditCard factoryMethod(String cardNumber, String name, String expDate, String security) {
		return new Visa(cardNumber, name, expDate, security);
	}

}
