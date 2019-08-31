<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navbar.jspf" %>

<div>
	<h1> List of Existing Categories </h1> 
</div>
<br><br><br>
<div class="Container">
	<table class="table table-striped">
	
		
		<thead>
			<th>Title</th>
			<th>Description</th>
			<th>Creator</th>
		</thead>
		<tbody>
			<c:forEach items="${catList}" var="list">
				<tr>
					<td>${list.catName}</td>
					<td>${list.description}</td>
					<td>${list.owner}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>

<%@ include file="../common/footer.jspf" %>