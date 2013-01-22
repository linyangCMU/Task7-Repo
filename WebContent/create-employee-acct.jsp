<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left.jsp" />

<p style="font-size:medium">
	Create Employee Account
</p>
<p>
	<form method="post" action="create-employee-acct.do">
		<table>
			<tr>
				<td> User Name: </td>
				<td><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td> first Name: </td>
				<td><input type="text" name="firstName" value="${form.firstName}"/></td>
			</tr>
			<tr>
				<td> lastName: </td>
				<td><input type="text" name="lastName" value="${form.lastName}"/></td>
			</tr>
			<tr>
				<td> Password: </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td> Confirm Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Create Account"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
