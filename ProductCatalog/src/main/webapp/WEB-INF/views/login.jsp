<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navbar.jspf" %>

<div>
	<h1> Welcome to myCatalog </h1>
</div>

<div class="container">
	<form action="/login.act" method="post">
		
		<h3> <font color="red"> <em> ${error_message} </em> </font> </h3> 
		
		<fieldset class="form-group">
			<label>User Name:</label>
			<input class="form-control" type="text" name="userName" placeholder="user name">
		</fieldset>
		
		<fieldset class="form-group">
			<label>Password:</label>
			<input class="form-control" type="password"  name="password" placeholder="password">
		</fieldset>
		
		<input class="btn btn-success" type="submit" name="login_btn" value="LogIn">
		
	</form>
</div>

<%@ include file="../common/footer.jspf" %>