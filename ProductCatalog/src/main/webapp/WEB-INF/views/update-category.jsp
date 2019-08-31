<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">
	<form action="/update-product.se" method="post">
		
		
		<fieldset class="form-group">
			<label for="pxtName">Item Name:</label> <input class="form-control"
				type="text" name="catName" value="${formerName}" placeholder="Item Name">
		</fieldset>
		
		 
		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label>
			<input class="form-control" type="text" name="catDescription" value="${formerDescription}" placeholder="user name">
		</fieldset>
		
		
		<input class="btn btn-success" type="submit" name="add_item"
			value="Save Changes">

	</form>
</div>

<%@ include file="../common/footer.jspf"%>