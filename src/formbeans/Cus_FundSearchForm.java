package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Cus_FundSearchForm extends FormBean{
    private int id = -1;
    private String query = "";
    
    public String getQuery()  { return query; }
    public int getId()        { return id;    }
    
    public void setQuery(String s)  { query = trimAndConvert(s,"<>>\"]"); }
    public void setId(int s)        { id = s; }
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
/*
        if (query == null || query.length() == 0) {
            errors.add("User Name is required");
        }
*/        
        return errors;
    }
}
