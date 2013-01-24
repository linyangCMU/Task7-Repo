package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Employee;
import databeans.Fund;
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
            
            Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "login-emp.jsp";
            }
            
            //process request form
            Emp_TransitionDayForm form = new Emp_TransitionDayForm(request);
            request.setAttribute("form",form);
            
            //get fund info:
            ArrayList<Fund> funds = fundDAO.lookup(".");
            for (Fund fund : funds) {
                fund.setPrice(historyDAO.lookupLatestPriceAndDate(fund.getId(), new Date(0)));
            }
            
            request.setAttribute("funds", funds);
            
            
            if (!form.isPresent()) {
                
                return "transition-day-emp.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "transition-day-emp.jsp";
            }
            
            return "transition-day-emp.jsp";
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
