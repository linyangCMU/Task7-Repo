package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.SimpleAttributeSet;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Employee;
import databeans.Fund;
import databeans.History;
import databeans.Portfolio;
import databeans.Position;
import databeans.Transaction;
import formbeans.Emp_TransitionDayForm;
import formbeans.Emp_ViewCustomerForm;

import model.FundDAO;
import model.HistoryDAO;
import model.Model;
import model.CustomerDAO;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;


public class Emp_TransitionDayAction extends Action {   
    private FormBeanFactory<Emp_ViewCustomerForm> formBeanFactory = FormBeanFactory.getInstance(Emp_ViewCustomerForm.class);
    private CustomerDAO customerDAO;
    private PositionDAO positionDAO;
    private FundDAO fundDAO;
    private HistoryDAO historyDAO;
    private TransactionDAO transactionDAO;
    
    public Emp_TransitionDayAction(Model model) {
        customerDAO = model.getCustomerDAO();
        positionDAO = model.getPositionDAO();
        fundDAO = model.getFundDAO();
        historyDAO = model.getHistoryDAO();
        transactionDAO = model.getTransactionDAO();
    }
    
    public String getName() { return "transitionday.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            
            //If employee is not logged in, direct him/her to the employee login page.
            Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "login-emp.jsp";
            }
            
            //process request form
            Emp_TransitionDayForm form = new Emp_TransitionDayForm(request);
            request.setAttribute("form",form);
            
            // if the transition day form is not ready:
            // present the form
            if (!form.isPresent()) {
              //get fund info:
                ArrayList<Fund> funds = fundDAO.lookup(".");
                Date date = new Date(0);
                for (Fund fund : funds) {
                    fund.setPrice(historyDAO.lookupLatestPriceAndDate(fund.getId(), date));
                }
                date.setTime(date.getTime() + 86400000);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("date", format.format(date));
                request.setAttribute("funds", funds);
                
                return "transition-day-emp.jsp";
            }
            
            //Validate the form
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "transition-day-emp.jsp";
            }
            
            //get form info:
            Date transitionDay = new Date(form.getDate().getTime());
            ArrayList<Integer> fundIds = form.getFundIds();
            ArrayList<Double> prices = form.getPrices();
            
            //update the funds' prices with newly provided prices
            History history = new History();
            for (int i=0; i<fundIds.size(); i++) {
                int id = fundIds.get(i);
                double price = prices.get(i);
                history.setDate(transitionDay);
                history.setId(id);
                history.setPrice(price);
                historyDAO.create(history);
            }
            
            // Update Pending Transactions
            ArrayList<Transaction> transactions = transactionDAO.getPendingTransactions();
            for (Transaction transaction : transactions) {
                transaction.setDate(transitionDay);
                int customerId = transaction.getCustomer_id();
                int fundId = transaction.getFund_id();
                double amount = transaction.getAmount();
                //determine the type of the transaction
                String type = transaction.getTransaction_type();
                if (type.equalsIgnoreCase("BUY")) {
                    //update position table
                    //get the latest price for this fund, give fundId
                    double price = historyDAO.lookupLatestPriceAndDate(fundId, new Date(0));
                    double shares = amount / price;
                    //check if the customer's available balance is enough
                    Customer customer = customerDAO.lookup(customerId);
                    double balance = customer.getAvailableCash();
                    if(balance<amount){
                        transaction.setStatus("DENIED");
                        continue;
                    }
                    transaction.setStatus("APPROVED");
                    customer.setAvailableCash(balance-amount);
                    customer.setCash(balance-amount);
                    customerDAO.update(customer);
                    //check if the position exists
                    Position position = positionDAO.lookup(customerId, fundId);
                    if (position==null) {
                        position = new Position();
                        position.setCustomer_id(customerId);
                        position.setFund_id(fundId);
                        position.setShares(shares);
                        positionDAO.create(position);
                    } else {
                        position.setShares(position.getShares() + shares);
                        positionDAO.update(position);
                    }
                } else if (type.equalsIgnoreCase("SELL")) {
                    double price = historyDAO.lookupLatestPriceAndDate(fundId, new Date(0));
                    double shares = transaction.getShares();
                    amount = shares * price;
                    Customer customer = customerDAO.lookup(customerId);
                    double balance = customer.getAvailableCash();
                    //update position table
                    Position position = positionDAO.lookup(customerId, fundId);
                    double availableShares = position.getShares();
                    if(availableShares < shares + 0.001) {
                        // Sold out all shares
                        transaction.setStatus("APPROVED");
                        customer.setAvailableCash(balance+amount);
                        customer.setCash(balance+amount);
                        customerDAO.update(customer);
                        positionDAO.remove(position);
                    } else if(availableShares < shares) {
                        transaction.setStatus("DENIED");
                    } else {
                        transaction.setStatus("APPROVED");
                        customer.setAvailableCash(balance+amount);
                        customer.setCash(balance+amount);
                        customerDAO.update(customer);
                        position.setShares(availableShares-shares);
                        positionDAO.update(position);
                    }                    
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    Customer customer = customerDAO.lookup(customerId);
                    customer.setCash(customer.getAvailableCash());
                    customerDAO.update(customer);
                    transaction.setStatus("APPROVED");
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    //update the balance
                    Customer customer = customerDAO.lookup(customerId);
                    double balance = customer.getAvailableCash() + amount;
                    customer.setCash(balance);
                    customer.setAvailableCash(balance);
                    customerDAO.update(customer);
                    transaction.setStatus("APPROVED");
                } else {
                    System.out.println("Unknown Type of transaction");
                }
                transactionDAO.updateTransaction(transaction);
            }
            return "transition-day-emp.jsp";
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
