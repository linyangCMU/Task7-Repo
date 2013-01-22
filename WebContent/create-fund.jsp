<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left.jsp" />

<p style="font-size:medium">
	Create Fund
</p>
<p>
	<form method="post" action="create-fund.do">
		<table>
			<tr>
				<td> Fund Name: </td>
				<td><input type="text" name="userName" value="${fund.name}"/></td>
			</tr>
			<tr>
				<td> Ticker: </td>
				<td><input type="text" name="deposit" value="${fund.symbol})"/></td>
			</tr>
			<tr>  
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Create Fund"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
