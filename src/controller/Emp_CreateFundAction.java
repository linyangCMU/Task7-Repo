package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import databeans.Fund;
import formbeans.Emp_CreateFundForm;
import formbeans.Emp_RegisterForm;

import model.FundDAO;
import model.Model;
import model.MyDAOException;


public class Emp_CreateFundAction extends Action {   
    private FormBeanFactory<Emp_CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(Emp_CreateFundForm.class);
    private FundDAO fundDAO;
    
    public Emp_CreateFundAction(Model model) {
        fundDAO = model.getFundDAO();
    }
    
    public String getName() { return "createfund.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Emp_CreateFundForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
            
            Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "login-emp.jsp";
            }
            
            if (!form.isPresent()) {
                return "create-fund-emp.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "create-fund-emp.jsp";
            }
            
            // Create new fund
            Fund fund = new Fund();
            fund.setName(form.getFundName());
            fund.setSymbol(form.getFundSymbol());
            
            fundDAO.create(fund);
            
            request.setAttribute("message", "Fund has been created");
          
            return "create-fund-emp.jsp";
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
