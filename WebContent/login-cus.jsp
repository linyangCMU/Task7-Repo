<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left-cus.jsp" />

<p style="font-size:medium">
    Customer Login
</p>
<p>
    <form method="post" action="customer-login.do">
        <table>
            <tr>
                <td> User Name: </td>
                <td><input type="text" name="userName" value="${form.userName}"/></td>
            </tr>
            <tr>
                <td> Password: </td>
                <td><input type="password" name="password" value=""/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="button" value="Login"/>
                </td>
            </tr>
        </table>
    </form>
</p>

<jsp:include page="template-bottom.jsp" />
