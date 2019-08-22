<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navbar.jspf" %>

<div>
	<h1> Welcome to myCatalog </h1>
</div>

<div>
	<form>
		<fieldset class="form-group">
			<label>User Name:</label>
			<input type="text" name="name" placeholder="user name">
		</fieldset>
		
		<fieldset class="form-group">
			<label>User Name:</label>
			<input type="password" class="form-control" name="password" placeholder="password">
		</fieldset>
		
		<input class="btn btn-success" type="submit" name="Login">
		
	</form>
</div>

<%@ include file="../common/footer.jspf" %>