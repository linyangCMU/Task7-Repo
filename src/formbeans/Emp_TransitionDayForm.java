package formbeans;

import java.util.*;

import javax.servlet.http.HttpServletRequest;;

public class Emp_TransitionDayForm {
    private ArrayList<String> fundIds;
    private ArrayList<String> prices;
    
    public Emp_TransitionDayForm(HttpServletRequest request){
        fundIds = new ArrayList<String>();
        prices = new ArrayList<String>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()) { 
            String id = parameterNames.nextElement();
            if (id.startsWith("fund_")) {
                fundIds.add(id);
                String price = request.getParameter(id);
                prices.add(price);
            }
        }
    }
    
    public ArrayList<String> getFundIds() {
        return fundIds;
    }
    
    public ArrayList<String> getPrices() {
        return prices;
    }
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        for (String id :fundIds) {
            try {  
                Integer.parseInt(id.substring(5));  
            }  
            catch(NumberFormatException nfe){  
                errors.add(id + " is not a valid fund_id");
                return errors;
            }  
        }
        for (String price :prices) {
            try{  
              Double.parseDouble(price);  
            }  
            catch(NumberFormatException nfe){  
              errors.add(price + " is not a number");
              return errors;
            }  
        }
        return errors;
    }
    
    public boolean isPresent() {
        if (fundIds.size()>0 && prices.size()>0 && fundIds.size() == prices.size())
            return true;
        return false;
    }
}
