<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div>
	<h1>${loginUserName} ${UserName}'s Catalog</h1>
</div>



<div class="Container">
	body content

	<h3>
		<font color="red"> <em> ${error_message} </em> </font> 
	</h3>
	<table class="table table-striped" >
		<thead>
			<th>Item Name</th>
			<th>Item Owner</th>
			<th>Item Category</th>
			<th>Image</th>
		</thead>
		<tbody>
			<c:forEach items="${pxtList}" var="pxt">
				<tr>
					<td>${pxt.pxtName}</td>
					<td>${pxt.pxtOwner}</td>
					<td>${pxt.pxtCategory}</td>
					<td><img src="data:image/jpg;Base64,${pxt.pxtImage}" width="40" height="50"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	Work in progress


</div>

<%@ include file="../common/footer.jspf"%>