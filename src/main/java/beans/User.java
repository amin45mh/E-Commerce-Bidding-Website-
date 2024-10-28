package beans;

public class User {
		private String firstName;
		private String lastName;
		private String address;
		private String userName;
		private String passWord;
		private String postalCode;
		private String country;
		private String city;
		private int streetNumber;
		
		public User() {
			
		}

		public User(String firstName, String lastName, String address, String userName, String passWord,
				String postalCode, String country, String city, int streetNumber) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.userName = userName;
			this.passWord = passWord;
			this.postalCode = postalCode;
			this.country = country;
			this.city = city;
			this.streetNumber = streetNumber;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getStreetNumber() {
			return streetNumber;
		}

		public void setStreetNumber(int streetNumber) {
			this.streetNumber = streetNumber;
		}
}
