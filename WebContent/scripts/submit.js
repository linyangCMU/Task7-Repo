function submitFund(id) {
	var form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "researchfund.do");
    
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "id");
    hiddenField.setAttribute("value", id);

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}

function submitCustomer(id) {
	var form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "viewcustomeraccount.do");
    
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "id");
    hiddenField.setAttribute("value", id);

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}

