<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">
	<form action="/update-product.se" method="post"
		enctype="multipart/form-data">
	<input type="hidden" name="MAX_FILE_SIZE" value="8388608" />												<!-- 				value="${newlist.cat}"  -->
		
		
		<fieldset class="form-group">
			<label for="pxtName">Item Name:</label> <input class="form-control"
				type="text" name="pxtName" value="${formerName}" placeholder="Item Name">
		</fieldset>
		
		   
		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label>
			<input class="form-control" type="text" name="pxtCat" value="${formerCat}" placeholder="user name">
		</fieldset>
		
		
		<!-- Tried implementing a select. It reads value of category names in category_tbl from database but does not store them as expected into the product_tbl  -->
		<!-- 
		<fieldset class="form-group">
			<label for="pxtCat">Item Category:</label> <select name="pxtCat">
				<c:forEach items="${catList}" var="cat">
					<option class="form-control" value="${cat.catName}">${cat.catName}</option>
				</c:forEach>
			</select>
		</fieldset>
		 -->
		

		<fieldset>
			<label for="pxtImage">Item Image:</label> <input type="file"
				name="pxtImage" value="Upload Image">
		</fieldset>

		<input class="btn btn-success" type="submit" name="add_item"
			value="Save Changes">

	</form>
</div>

<%@ include file="../common/footer.jspf"%>