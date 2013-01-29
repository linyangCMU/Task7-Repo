package model;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
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

        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (customer_id, fund_id, execute_date, shares, transaction_type, amount, status) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, transaction.getCustomer_id());
			pstmt.setInt(2, transaction.getFund_id());
			pstmt.setDate(3, (Date) transaction.getExecute_date());
			pstmt.setInt(4, (int)transaction.getShares() * 1000);	
			pstmt.setString(5, transaction.getTransaction_type());
			pstmt.setInt(6, (int)(transaction.getAmount()*100));
			pstmt.setString(7, transaction.getStatus());
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
	
	public void createWithUpdate_Buy(Transaction transaction, int customer_id, double newAvailable) {
		Connection con = null;
		try{

			con = getConnection();
			con.setAutoCommit(false);

        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (customer_id, fund_id, execute_date, shares, transaction_type, amount, status) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, transaction.getCustomer_id());
			pstmt.setInt(2, transaction.getFund_id());
			pstmt.setDate(3, (Date) transaction.getExecute_date());
			pstmt.setInt(4, (int)transaction.getShares() * 1000);	
			pstmt.setString(5, transaction.getTransaction_type());
			pstmt.setInt(6, (int)(transaction.getAmount()*100));
			pstmt.setString(7, transaction.getStatus());
			int count = pstmt.executeUpdate();
			if(count != 1) throw new SQLException("Insert updated" + count + "rows");
			pstmt.close();

			PreparedStatement pstmt2 = con.prepareStatement("UPDATE task7_customer SET available_cash=? WHERE customer_id=?");
            pstmt2.setInt(1, (int)(newAvailable*100));
            pstmt2.setInt(2, customer_id);
            pstmt2.executeUpdate();     
            pstmt2.close();

            con.commit();
			releaseConnection(con);
		}catch(Exception e){
				try{
					if(con != null)
						con.close();
				}catch(SQLException e2){
					try {
						throw new MyDAOException(e);
					} catch (MyDAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
	}
	public void createWithUpdate_Sell(Transaction transaction, int customer_id, int fund_id, double newShares) {
		Connection con = null;
		try{

			con = getConnection();
			con.setAutoCommit(false);

        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (customer_id, fund_id, execute_date, shares, transaction_type, amount, status) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, transaction.getCustomer_id());
			pstmt.setInt(2, transaction.getFund_id());
			pstmt.setDate(3, (Date) transaction.getExecute_date());
			pstmt.setInt(4, (int)transaction.getShares() * 1000);	
			pstmt.setString(5, transaction.getTransaction_type());
			pstmt.setInt(6, (int)(transaction.getAmount()*100));
			pstmt.setString(7, transaction.getStatus());
			int count = pstmt.executeUpdate();
			if(count != 1) throw new SQLException("Insert updated" + count + "rows");
			pstmt.close();


			/*double newAvailable = available - sellAmount;
			
			PreparedStatement pstmt2 = con.prepareStatement("UPDATE task7_customer SET available_cash=? WHERE customer_id=?");
            pstmt2.setInt(1, (int)(newAvailable*100));
            pstmt2.setInt(2, customer_id);
            pstmt2.executeUpdate();     
            pstmt2.close();*/

            PreparedStatement pstmt2 = con.prepareStatement("UPDATE task7_position SET shares=? WHERE customer_id=? and fund_id=?");
            pstmt2.setInt(1, (int)(newShares*1000));
            pstmt2.setInt(2, customer_id);
			pstmt2.setInt(3, fund_id);
			pstmt2.executeUpdate(); 
		    pstmt2.close();

			con.commit();
			releaseConnection(con);
		}catch(Exception e){
			try{
				if(con != null)
					con.close();
			}catch(SQLException e2){
				try {
					throw new MyDAOException(e);
				} catch (MyDAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public ArrayList<Transaction> getTransactions(int customer_id) throws MyDAOException {
		Connection con = null;
    	try {
        	con = getConnection();

            Statement stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " where customer_id=?");
        	pstmt.setInt(1, customer_id);
            
        	ResultSet rs = pstmt.executeQuery();
            
        	ArrayList<Transaction> list = new ArrayList<Transaction>();
            while (rs.next()) {
            	Transaction transaction = new Transaction();
            	transaction = new Transaction();
				transaction.setTransaction_id(rs.getInt("transaction_id"));
				transaction.setCustomer_id(rs.getInt("customer_id"));
				transaction.setFund_id(rs.getInt("fund_id"));
				transaction.setDate(rs.getDate("execute_date"));
				transaction.setShares((double)rs.getInt("shares")/1000);
				transaction.setTransaction_type(rs.getString("transaction_type"));
				transaction.setAmount((double)rs.getInt("amount")/100);
            	transaction.setStatus(rs.getString("status"));
            	
            	list.add(transaction);
            }
            stmt.close();
            releaseConnection(con);
            
            return list;
    	} catch (SQLException e) {
            try { 
            	if (con != null) 
            		con.close(); 
            } 
            catch (SQLException e2) {
            	
            }
            throw new MyDAOException(e);
		}

	}

	public Transaction getLastTransaction(int customer_id) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            Statement stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("SELECT *, max(execute_date) FROM " + tableName + " where customer_id=?");
            pstmt.setInt(1, customer_id);
            
            ResultSet rs = pstmt.executeQuery();
            
            Transaction transaction;;
            if (!rs.next()) {
                transaction = null;
            } else {
                transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("transaction_id"));
                transaction.setCustomer_id(rs.getInt("customer_id"));
                transaction.setFund_id(rs.getInt("fund_id"));
                transaction.setDate(rs.getDate("execute_date"));
                transaction.setShares((double)rs.getInt("shares")/1000);
                transaction.setTransaction_type(rs.getString("transaction_type"));
                transaction.setAmount((double)rs.getInt("amount")/100);                
            }
            stmt.close();
            releaseConnection(con);
            
            return transaction;
        } catch (SQLException e) {
            try { 
                if (con != null) 
                    con.close(); 
            } 
            catch (SQLException e2) {
                
            }
            throw new MyDAOException(e);
        }
        
    }

	public ArrayList<Transaction> getPendingTransactions() throws MyDAOException{
	    Connection con = null;
        try {
            con = getConnection();

            Statement stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " where status=?");
            pstmt.setString(1, "PENDING");
            
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<Transaction> list = new ArrayList<Transaction>();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("transaction_id"));
                transaction.setCustomer_id(rs.getInt("customer_id"));
                transaction.setFund_id(rs.getInt("fund_id"));
                transaction.setDate(rs.getDate("execute_date"));
                transaction.setShares((double)rs.getInt("shares")/1000);
                transaction.setTransaction_type(rs.getString("transaction_type"));
                transaction.setAmount((double)rs.getInt("amount")/100);
                transaction.setStatus(rs.getString("status"));
                
                list.add(transaction);
            }
            stmt.close();
            releaseConnection(con);
            
            return list;
        } catch (SQLException e) {
            try { 
                if (con != null) 
                    con.close(); 
            } 
            catch (SQLException e2) {
                
            }
            throw new MyDAOException(e);
        }
    }

	public void updateTransaction(Transaction transaction) throws MyDAOException{
	    Connection con = null;
        try {
            con = getConnection();
            
            PreparedStatement pstmt = con.prepareStatement("UPDATE "  + tableName + " SET execute_date=?, status=? WHERE transaction_id=?");
            pstmt.setDate(1, new Date((transaction.getExecute_date().getTime())));
            pstmt.setString(2, transaction.getStatus());
            pstmt.setInt(3, transaction.getTransaction_id());
            pstmt.executeUpdate();
            
            pstmt.close();
            releaseConnection(con);
            
        } catch (SQLException e) {
            try { 
                if (con != null) 
                    con.close(); 
            } 
            catch (SQLException e2) {
                
            }
            throw new MyDAOException(e);
        }
        
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
            stmt.executeUpdate("CREATE TABLE " + tableName + " (transaction_id INT NOT NULL AUTO_INCREMENT, customer_id INT NOT NULL, fund_id INT NOT NULL, execute_date DATE, shares INT, transaction_type VARCHAR(255), amount INT, status VARCHAR(255), PRIMARY KEY(transaction_id))");           
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}

}