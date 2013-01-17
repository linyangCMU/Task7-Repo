package model;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import databeans.Transaction;

public class TransactionDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();	
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	
	public TransactionDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException{
		this.jdbcDriver = jdbcDriver;
		this.jdbcURL = jdbcURL;
		this.tableName = tableName;
		
		if(!tableExists())
			createTable();
			
	}
	
	private synchronized Connection getConnection() throws MyDAOException{
		if(connectionPool.size() > 0){
			return connectionPool.remove(connectionPool.size() - 1);
		}
		try{
			Class.forName(jdbcDriver);
		}catch(ClassNotFoundException e){
			throw new MyDAOException(e);
		}
		try{
			return DriverManager.getConnection(jdbcURL);
		}catch(SQLException e){
			throw new MyDAOException(e);
		}
	}
	
	private synchronized void releaseConnection(Connection con){
		connectionPool.add(con);
	}
	
	public void create(Transaction transaction) throws MyDAOException{
		Connection con = null;
		try{
			con = getConnection();
			
        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (customer_id, fund_id, execute_date, lastname, shares, transaction_type, amount) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, transaction.getCustomer_id());
			pstmt.setInt(2, transaction.getFund_id());
			pstmt.setDate(3, transaction.getExecute_date());
			pstmt.setInt(4, transaction.getShares());
			pstmt.setString(5, transaction.getTransaction_type());
			pstmt.setInt(6, transaction.getAmount());
			int count = pstmt.executeUpdate();
			if(count != 1) throw new SQLException("Insert updated" + count + "rows");
			pstmt.close();
			releaseConnection(con);
		}catch(Exception e){
			try{
				if(con != null)
					con.close();
			}catch(SQLException e2){
				throw new MyDAOException(e);
			}
		}
	}
	public Transaction lookup(int customer_id) throws MyDAOException{
		//lookup method
		
		
	}
	public Transaction[] getTransactions(int customer_id) throws MyDAOException {
		// get Transaction Collection
		
	}
	
	private boolean tableExists() throws MyDAOException{
		Connection con = null;
        try {
        	con = getConnection();
        	DatabaseMetaData metaData = con.getMetaData();
        	ResultSet rs = metaData.getTables(null, null, tableName, null);
        	
        	boolean answer = rs.next();
        	
        	rs.close();
        	releaseConnection(con);
        	
        	return answer;

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	private void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName + " (transaction_id INT NOT NULL AUTO_INCREMENT, customer_id INT NOT NULL, fund_id INT NOT NULL, execute_date DATE, shares INT, transaction_type VARCHAR(255), amount INT, PRIMARY KEY(transaction_id))");           
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
}
