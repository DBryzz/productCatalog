<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div class="container">


	body content

	<table class="table table-striped">
		<caption>List of Categories</caption>
		<thead>
			<th>Category Title</th>
			<th>Category Description</th>

		</thead>
		<tbody>
			<c:forEach items="${firstList}" var="list">
				<tr>
					<td>${list.pxtName}</td>
					<td>${list.pxtOwner}</td>
					<td>${list.pxtCategory}</td>
					<td><img src="data:image/jpg;Base64,${list.pxtImage}"
						width="40" height="50" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<form action="/add-product.se" method="post"
		enctype="multipart/form-data">


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
			<label for="pxtCat">Item Category:</label> <select name="category">
				<c:forEach items="${catList}" var="cat">
					<option class="form-control" value="${cat.catName}">${cat.catName}</option>
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