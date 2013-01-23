package databeans;

public class Portfolio {
    
    private String fundName = "";
    private double shares = 0.0;
    private double price = 0.0;
    private double total = 0.0;
    
    public String getFundName() {
        return fundName;
    }
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
    public double getShares() {
        return shares;
    }
    public void setShares(double shares) {
        this.shares = shares;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Portfolio [fundName=" + fundName + ", shares=" + shares
                + ", price=" + price + ", total=" + total + "]";
    }
    
    
}
