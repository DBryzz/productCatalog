<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navbar.jspf" %>

<div>
	<h1> SignUp and Acquire Some Privileges </h1>
</div>

<div class = "container">
	<form action="/signup.act" method="post">
	
		<fieldset class="form-group">
			
			
			
			<label>Full Names:</label>
			<input class="form-control" type="text" name="name" placeholder="user name">
		</fieldset>
		
		<fieldset class="form-group">
			<label>User Name:</label>
			<input class="form-control" type="text" name="userName" placeholder="user name">
		</fieldset>
		
		<fieldset class="form-group">
			<label>Password:</label>
			<input class="form-control" type="password"  name="password" placeholder="password">
		</fieldset>
		
		<input class="btn btn-success" type="submit" name="signup_btn" value="SignUp">
		
	</form>
</div>

<%@ include file="../common/footer.jspf" %>