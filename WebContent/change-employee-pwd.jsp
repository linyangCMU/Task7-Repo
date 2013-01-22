<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left.jsp" />

<p style="font-size:medium">
	Change Password
</p>
<p>
	
<p>
	<form method="POST" action="change-emoloyee-pwd.do">
		<table>
			<tr>
				<td> Current Password: </td>
				<td><input type="password" name="currentPassword" value=""/></td>
			</tr>
			<tr>
				<td> New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="action" value="Change Password"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
