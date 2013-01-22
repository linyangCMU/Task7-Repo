<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left.jsp" />

<p style="font-size:medium">
	Deposit Check
</p>
<p>
	<form method="post" action="depositcheck.do">
		<table>
			<tr>
				<td> UserName: </td>
				<td><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td> Deposit Amount: </td>
				<td><input type="text" name="deposit" value=""/></td>
			</tr>
			<tr>  
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Deposit Check"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
