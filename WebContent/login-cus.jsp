<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
    <div id="content-container">
        <div id="section-navigation">
            <p align="left">
                <c:choose>
	                <c:when test="${ (empty user) }">
                        <span class="menu-item"><a href="customer-login.do">Login</a></span><br/>
	                </c:when>
	                <c:otherwise>
		                <span class="menu-head">${user.firstName} ${user.lastName}</span><br/>
						<span class="menu-item"><a href="viewPortafolio.do">View Account</a></span><br/>
						<span class="menu-item"><a href="ChangePwdA.do">Change password</a></span><br/>
						<span class="menu-item"><a href="BuyFund.do">Buy Fund</a></span><br/>
						<span class="menu-item"><a href="SellFund.do">Sell Fund</a></span><br/>
						<span class="menu-item"><a href="RequestCheck.do">Request Check</a></span><br/>
						<span class="menu-item"><a href="transactionhistory.do">Transaction History</a></span><br/>
						<span class="menu-item"><a href="researchFund.do">Research Fund</a></span><br/>
						<span class="menu-item"><a href="Index.do">Logout</a></span><br/>
					</c:otherwise>
				</c:choose>
			</p>
        </div>
    
        <div id="content">
            <h2> Customer Login </h2>
            <form method="post" action="customer-login.do">
                <table>
                    <tr>
                        <td> Username: </td>
                        <td><input type="text" name="userName" value=""/></td>
                    </tr>
                    <tr>
                        <td> Password: </td>
                        <td><input type="password" name="password" value=""/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" name="button" value="Login"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>