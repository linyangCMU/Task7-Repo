package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Fund;
import databeans.Portfolio;
import databeans.Position;
import databeans.Transaction;
import formbeans.Cus_FundSearchForm;

import model.Model;

import model.HistoryDAO;
import model.FundDAO;
import model.PositionDAO;
import model.TransactionDAO;
import model.MyDAOException;


public class Cus_ViewPortfolioAction extends Action {   
    private FundDAO fundDAO;
    private HistoryDAO historyDAO;
    private PositionDAO positionDAO;
    private TransactionDAO transactionDAO;
    
    public Cus_ViewPortfolioAction(Model model) {
        fundDAO = model.getFundDAO();
        historyDAO = model.getHistoryDAO();
        positionDAO = model.getPositionDAO();
        transactionDAO = model.getTransactionDAO();
    }
    
    public String getName() { return "viewportfolio.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Customer customer = (Customer) request.getSession(false).getAttribute("customer");
            
            if(customer == null) {
                return "login-cus.jsp";
            }
            
            if (errors.size() != 0) {
                return "error.jsp";
            }
            
            int customerId = customer.getCustomerID();
            ArrayList<Position> positions = positionDAO.getPositionsByCustomerId(customerId);
            
            ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
            for (Position position : positions) {
                int fundId = position.getFund_id();
                double shares = position.getShares();
                Fund fund = fundDAO.lookup(fundId);
                String fundName = fund.getName();
                String fundSymbol = fund.getSymbol();
                Date date = new Date(0);
                double price = historyDAO.lookupLatestPriceAndDate(fundId, date);
                
                Portfolio portfolio = new Portfolio();
                portfolio.setFundName(fundName);
                portfolio.setShares(shares);
                portfolio.setPrice(price);
                portfolio.setTotal(shares * price);
                
                System.out.println(portfolio.toString());
                portfolios.add(portfolio);
            }
            
            Transaction transaction = transactionDAO.getLastTransaction(customerId);
            Date date;
            if (transaction!=null){
                date = transaction.getExecute_date();
            } else {
                date = new Date(0);
            }
            
            // Attach (this copy of) the funds object to the session
            request.setAttribute("portfolios",portfolios);
            request.setAttribute("lastExecuteDate",date);
            
            String webapp = request.getContextPath();
            return "view-portfolio-cus.jsp";
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
