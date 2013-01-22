package databeans;

import java.sql.Date;

public class Transaction {
	
	private int transaction_id = 0;
	private int customer_id = 0;
	private int fund_id = 0;
	private double shares = 0.0;
	private Date execute_date = null;
	private String transaction_type = null;
	private int amount = 0;
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	public Date getExecute_date() {
		return execute_date;
	}
	public void setDate(Date execute_date) {
		this.execute_date = execute_date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
