<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">
	<form action="/add-product.se" method="post"
		enctype="multipart/form-data">

		<fieldset class="form-group">
			<label for="pxtName">Item Name:</label> <input class="form-control"
				type="text" name="pxtName" placeholder="Item Name">
		</fieldset>
		
		 
		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label>
			<input class="form-control" type="text" name="pxtCat" placeholder="user name">
		</fieldset>
	
	<!-- 
	<fieldset class="form-group">
	<label for="pxtCat">Item Category:</label>
		<select name=category>
			<c:forEach items="${pxtList}" var="pxt">
				<option class = "form-control" value="${pxt.pxtCategory}">${pxt.pxtCategory}</option>
			</c:forEach>
		</select>
	
	</fieldset>
	-->	

		<fieldset>
			<label for="pxtImage">Item Image:</label> <input type="file"
				name="pxtImage" value="Upload Image">
		</fieldset>

		<input class="btn btn-success" type="submit" name="add_item"
			value="Add Item">

	</form>
</div>

<%@ include file="../common/footer.jspf"%>