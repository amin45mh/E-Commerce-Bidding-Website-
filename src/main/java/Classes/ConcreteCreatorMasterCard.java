package Classes;

public class ConcreteCreatorMasterCard extends CardCreator {

	@Override
	public CreditCard factoryMethod(String cardNumber, String name, String expDate, String security) {
		return new MasterCard(cardNumber, name, expDate, security);
	}
}
