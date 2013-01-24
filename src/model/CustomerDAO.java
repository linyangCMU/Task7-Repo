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



import databeans.Customer;


public class CustomerDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;

	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName)
			throws MyDAOException {
		this.jdbcDriver = jdbcDriver;
		this.jdbcURL = jdbcURL;
		this.tableName = tableName;

		if (!tableExists())
			createTable();

	}

	private synchronized Connection getConnection() throws MyDAOException {
		if (connectionPool.size() > 0) {
			return connectionPool.remove(connectionPool.size() - 1);
		}
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			throw new MyDAOException(e);
		}
		try {
			return DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}

	private synchronized void releaseConnection(Connection con) {
		connectionPool.add(con);
	}

	public void create(Customer customer) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();

			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ " (username, password, firstname, lastname, addr_line1, add_line2, city, state, zip, cash) VALUES (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getFirstName());
			pstmt.setString(4, customer.getLastName());
			pstmt.setString(5, customer.getAddrL1());
			pstmt.setString(6, customer.getAddrL2());
			pstmt.setString(7, customer.getCity());
			pstmt.setString(8, customer.getState());
			pstmt.setString(9, customer.getZip());
			pstmt.setDouble(10, customer.getCash());
			int count = pstmt.executeUpdate();
			if (count != 1)
				throw new SQLException("Insert updated" + count + "rows");
			pstmt.close();
			releaseConnection(con);
		} catch (Exception e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				throw new MyDAOException(e);
			}
		}
	}

	public Customer lookup(String username) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE username=?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			Customer customer;
			if (!rs.next()) {
				customer = null;
			} else {
				customer = new Customer();
				customer.setCustomerID(rs.getInt("customer_id"));
				customer.setPassword(rs.getString("password"));
				customer.setUsername(rs.getString("username"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setAddr1(rs.getString("addr_line1"));
				customer.setAddr2(rs.getString("addr_line2"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setZip(rs.getString("zip"));
				customer.setCash(rs.getInt("cash"));
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return customer;

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
	
	public Customer lookup(int customerId) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE customer_id=?");
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            Customer customer;
            if (!rs.next()) {
                customer = null;
            } else {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("customer_id"));
                customer.setPassword(rs.getString("password"));
                customer.setUsername(rs.getString("username"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setAddr1(rs.getString("addr_line1"));
                customer.setAddr2(rs.getString("addr_line2"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZip(rs.getString("zip"));
                customer.setCash(rs.getInt("cash"));
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return customer;

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

	public ArrayList<Customer> search(String query) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM " + tableName + 
                         " WHERE username REGEXP ? " +
                         " OR firstname REGEXP ? " +
                         " OR lastname REGEXP ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, query);
            pstmt.setString(2, query);
            pstmt.setString(3, query);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Customer> customers = new ArrayList<Customer>();
            Customer customer;
            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setAddr1(rs.getString("addr_line1"));
                customer.setAddr2(rs.getString("addr_line2"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZip(rs.getString("zip"));
                customer.setCash(rs.getInt("cash"));
                
                customers.add(customer);
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return customers;

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
	
	public void updateCash(Customer customer) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            
            PreparedStatement pstmt = con.prepareStatement("UPDATE "  + tableName + " SET cash=? WHERE username=?");
            pstmt.setInt(1, (int)(customer.getCash()*100));
            pstmt.setString(2, customer.getUsername());
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
	
	/* 
	 * Get the cash balance by providing the customer's id
	 * Author: Yuan Cao
	 */
	public double getCash(int customerId) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            
            String sql = "SELECT cash FROM "  + tableName + " WHERE customer_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            double cash = 0;
            if (rs.next()) {
                cash = rs.getInt("cash") * 1.0 / 100;
            } else {
                cash = 0;
            }
            rs.close();
            pstmt.close();
            releaseConnection(con);
            
            return cash;
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
	
	public void setPassword(String username, String password) throws MyDAOException {
		Connection con = null;		
    	try {
        	con = getConnection();
        	
        	
            Statement stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("UPDATE "  + tableName + " SET password=? WHERE username=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);		
			pstmt.executeUpdate();
           
            stmt.close();
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

	
	public Customer[] getCustomers() throws MyDAOException {
		Connection con = null;
    	try {
        	con = getConnection();

            Statement stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " ");
        	
        	ResultSet rs = pstmt.executeQuery();
            
            List<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
            	Customer customer = new Customer();
            	customer.setCustomerID(rs.getInt("customer_id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setAddr1(rs.getString("addr_line1"));
				customer.setAddr2(rs.getString("addr_line2"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setZip(rs.getString("zip"));
				customer.setCash(rs.getInt("cash"));
            	
            	
            	list.add(customer);
            }
            stmt.close();
            releaseConnection(con);
            
            return list.toArray(new Customer[list.size()]);
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

	private boolean tableExists() throws MyDAOException {
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
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		}
	}

	private void createTable() throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE "
					+ tableName
					+ " (customer_id INT NOT NULL AUTO_INCREMENT, username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, firstname VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, addr_line1 VARCHAR(255), addr_line2 VARCHAR(255), city VARCHAR(255), state VARCHAR(255), zip INT, cash INT, PRIMARY KEY(customer_id))");
			stmt.close();

			releaseConnection(con);

		} catch (SQLException e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		}
	}
}