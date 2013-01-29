package databeans;

public class Customer implements Comparable<Customer> {

	private int customerID = 0;
	private String username = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	private String addr_line1 = null;
	private String addr_line2 = null;
	private String city = null;
	private String state = null;
	private String zip = null;
	private double cash = 0;
	private double available = 0;
	
	public String getAddr_line1() {
		return addr_line1;
	}

	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = addr_line2;
	}

	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public boolean checkPassword(String password) {
		return password.equals(getPassword());
	}

	public int compareTo(Customer other) {
		int c = lastName.compareTo(other.lastName);
		if (c != 0)
			return c;
		c = firstName.compareTo(other.firstName);
		if (c != 0)
			return c;
		return String.valueOf(customerID).compareTo(String.valueOf(other.customerID));
	}

	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			Customer other = (Customer) obj;
			return username.equals(other.username);
		}
		return false;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getAddrL1() {
		return addr_line1;
	}
	
	public String getAddrL2() {
		return addr_line2;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public double getCash() {
		return cash;
	}

	public void setPassword(String s) {
		password = s;
	}

	public void setCustomerID(int x) {
		customerID = x;
	}

	public void setFirstName(String s) {
		firstName = s;
	}

	public void setLastName(String s) {
		lastName = s;
	}

	public void setUsername(String s) {
		username = s;
	}
	
	public void setAddr1(String s) {
		addr_line1 = s;
	}
	
	public void setAddr2(String s) {
		addr_line2 = s;
	}
	
	public void setCity(String s) {
		city = s;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public void setZip(String s) {
		zip = s;
	}
	
	public void setCash(double s) {
		cash = s;
	}
	
	public String toString() {
		return "Customer(" + getCustomerID() + ")";
	}
}