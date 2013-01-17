package databeans;

import java.util.Date;

public class History {
	
	private int id; //fund_id
	private Date date; //price date
	private double price; //daily price
	
	public int getId()       { return id;    }
	public Date getDate()    { return date;  }
	public double getPrice() { return price; }
	
	public void setId(int a)       { id = a;       }
	public void setDate(Date a)    { date = a;     }
	public void setPrice(double a) { price = a;    }
	
}
