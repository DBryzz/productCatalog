<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navbar.jspf" %>

<div>
	<h1> Welcome to myCatalog </h1>
</div>

<div class="Container">
	body content
	
	<table class="table table-striped" >
		<thead>
			<th>Item Name</th>
			<th>Item Owner</th>
			<th>Item Category</th>
		</thead>
		<tbody>
			<c:forEach items="${firstList}" var="list">
				<tr>
					<td>${list.pxtName}</td>
					<td>${list.pxtOwner}</td>
					<td>${list.pxtCategory}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>

<%@ include file="../common/footer.jspf" %>