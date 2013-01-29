package formbeans;

import java.util.*;

import javax.servlet.http.HttpServletRequest;;

public class Emp_TransitionDayForm {
    private ArrayList<Integer> fundIds;
    private ArrayList<Double> prices;
    private String date;
    private Date formatDate;
    
    public Emp_TransitionDayForm(HttpServletRequest request){
        fundIds = new ArrayList<Integer>();
        prices = new ArrayList<Double>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()) { 
            String id = parameterNames.nextElement();
            if (id.startsWith("fund_")) {
                try {  
                    fundIds.add(Integer.parseInt(id.substring(5)));  
                    prices.add(Double.parseDouble(request.getParameter(id)));
                }
                catch(NumberFormatException nfe){  
                    fundIds = null;
                    prices = null;
                    break;
                }
            }
        }
        date = request.getParameter("date");
        if(date==null) return;
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            formatDate = format.parse(date);
        } catch (ParseException e) {
            formatDate = null;
        }
    }
    
    public ArrayList<Integer> getFundIds() {
        return fundIds;
    }
    
    public ArrayList<Double> getPrices() {
        return prices;
    }
    
    public Date getDate() {
        return formatDate;
    }
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if (fundIds == null || prices == null) {
            errors.add("There are errors in the form");
        }
        
        if (formatDate == null) {
            errors.add("Invalid Date Format. Must be MM/DD/YYYY");
        }
        
        return errors;
    }
    
    public boolean isPresent() {
        if (fundIds.size()>0 && prices.size()>0 && fundIds.size() == prices.size())
            return true;
        return false;
    }
}
