package databeans;

public class Fund {
	private int id = -1; // fund_id, primary key
	private String name = null; // the full name for the fund
	private String symbol = null; // the short name for the fund
	
	public int compareTo(Fund other) {
		// Order first by lastName and then by firstName
		int c = name.compareTo(other.name);
		if (c != 0) return c;
		return symbol.compareTo(other.symbol);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Fund) {
			Fund other = (Fund) obj;
			return name.equals(other.name) || symbol.equals(other.symbol);
		}
		return false;
	}
	
	public String getName()      { return name;       }
	public String getSymbol()    { return symbol;     }
	public int    getId()        { return id;         }
	
	public void setName(String a)    { name = a;   }
	public void setSymbol(String a)  { symbol = a; }
	public void setId(int a)         { id = a;     }
	
	public String toString() {
		return "Fund("+getName()+","+getSymbol()+")";
	}
}
