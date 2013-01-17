package databeans;

public class Position {

	private int customer_id = 0;
	private int fund_id = 0;
	private int shares = 0;

	public int getCustomer_id() {
		return customer_id;
	}
	
	public int getFundID() {
		return fund_id;
	}
	
	public int getShares() {
		return shares;
	}


	public void setCustomID(int x) {
		customer_id = x;
	}

	public void setFundID(int x) {
		fund_id = x;
	}
	
	public void setShares(int x) {
		shares = x;
	}
}
