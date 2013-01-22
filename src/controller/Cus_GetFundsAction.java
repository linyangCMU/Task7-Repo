package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Fund;
import formbeans.Cus_FundSearchForm;

import model.Model;
import model.HistoryDAO;
import model.FundDAO;
import model.MyDAOException;


public class Cus_GetFundsAction extends Action {   
    private FormBeanFactory<Cus_FundSearchForm> formBeanFactory = FormBeanFactory.getInstance(Cus_FundSearchForm.class);
    private FundDAO fundDAO;
    private HistoryDAO historyDAO;
    
    public Cus_GetFundsAction(Model model) {
        fundDAO = model.getFundDAO();
        historyDAO = model.getHistoryDAO();
    }
    
    public String getName() { return "getfunds.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Cus_FundSearchForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            
            if (!form.isPresent()) {
                return "getfunds.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "getfunds.jsp";
            }
            
            // Look up the fund
            ArrayList<Fund> funds = fundDAO.lookup(form.getQuery());
            
            for (Fund fund:funds){
                Date date = new Date(0);
                String price = historyDAO.lookupLatestPriceAndDate(fund.getId(), date);
                fund.setDate(date);
                fund.setPrice(price);
            }
            
            // Attach (this copy of) the funds object to the session
            HttpSession session = request.getSession();
            session.setAttribute("funds",funds);
            
            String webapp = request.getContextPath();
            return webapp + "/getfunds.jsp";
        } catch (MyDAOException e) {
            System.out.println("DAO error");
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            System.out.println("FormBean error");
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
