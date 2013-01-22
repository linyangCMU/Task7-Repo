<!--zhenzhex, Dec 5,08-600  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${errors}">
	<div style="font-size:medium; color:red; "> ${error} </div>
</c:forEach>

