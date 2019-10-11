<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">
	<form action="/update-category.se" method="post">
		
		
		<fieldset class="form-group">
			<label for="catName">Title:</label> <input class="form-control"
				type="text" name="catName" value="${formerName}" placeholder="Category Title">
		</fieldset>
		
		 
		<fieldset class="form-group">
			<label for="catDescription">Description:</label>
			<input class="form-control" type="text" name="catDescription" value="${formerDescription}" placeholder="Category Description">
		</fieldset>
		
		
		<input class="btn btn-success" type="submit" name="add_item"
			value="Save Changes">

	</form>
</div>

<%@ include file="../common/footer.jspf"%>