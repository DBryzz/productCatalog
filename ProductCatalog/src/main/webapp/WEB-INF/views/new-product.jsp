<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">


	

 	<font color="red">${error_message}</font>

	<form action="/add-product.se" method="post"
		enctype="multipart/form-data">
		
		<input type="hidden" name="MAX_FILE_SIZE" value="2097152" /> <!-- 8MB  8388608 4194304 -->


		<fieldset class="form-group">
			<label for="pxtName">Item Name:</label> <input class="form-control"
				type="text" name="pxtName" placeholder="Item Name">
		</fieldset>

		<!-- 
		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label>
			<input class="form-control" type="text" name="pxtCat"  placeholder="user name">
		</fieldset>
	 	-->

		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label> <br> <select name="category" class="select">
				<c:forEach items="${catList}" var="cat">
					<option class="option" value="${cat.catName}">${cat.catName}</option>
				</c:forEach>
			</select>
		</fieldset>


		<fieldset>
			<label for="pxtImage">Item Image:</label> <input type="file"
				name="pxtImage" value="Upload Image">
		</fieldset>

		<input class="btn btn-success" type="submit" name="add_item"
			value="Add Item">

	</form>
</div>

<%@ include file="../common/footer.jspf"%>