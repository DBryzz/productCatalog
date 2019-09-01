<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">


	<font color="red">${error_message}</font>

	<form action="/add-category.se" method="post">


		<fieldset class="form-group">
			<label for="catName">Title:</label> <input class="form-control"
				type="text" name="catName" placeholder="Category Title">
		</fieldset>

		<fieldset class="form-group">
			<label for="catDescription">Description:</label>
			<input class="form-control" type="text" name="catDescription"  placeholder="Category Description">
		</fieldset>

		<input class="btn btn-success" type="submit" name="add_item"
			value="Add Category">

	</form> <br><br>
	
	
	<table class="table table-striped">
	
		<caption>List of Categories</caption>
		
		<thead>
			<th>Title</th>
			<th>Description</th>
		</thead>
		<tbody>
			<c:forEach items="${catList}" var="list">
				<tr>
					<td>${list.catName}</td>
					<td>${list.description}</td>
					<td><a class="btn btn-danger" href="/delete-category.se?id=${list.catID}&name=${list.catName}&description=${list.description}&owner=${list.owner}">Delete</a></td>
					<td><a class="btn btn-primary" href="/update-category.se?id=${list.catID}&name=${list.catName}&description=${list.description}&owner=${list.owner}">Update</a></td>
										
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../common/footer.jspf"%>