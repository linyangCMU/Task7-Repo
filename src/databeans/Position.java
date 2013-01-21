package databeans;

public class Position {

	private int customer_id = 0;
	private int fund_id = 0;
	private double shares = 0;

	public int getCustomer_id() {
		return customer_id;
	}
	
	public int getFundID() {
		return fund_id;
	}
	
	public double getShares() {
		return shares;
	}


	public void setCustomerID(int x) {
		customer_id = x;
	}

	public void setFundID(int x) {
		fund_id = x;
	}
	
	public void setShares(double x) {
		shares = x;
	}
}
