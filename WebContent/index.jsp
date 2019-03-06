<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Home</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
  <span class="navbar-brand mb-0 h1" style="margin-left: 30%">Image Utility System</span>
</nav>
<div style="margin: 10% ">
	<form action="Login" method="post">
	  <div class="form-group">
	    <label for="userName">Username</label>
	    <input type="text" class="form-control" name="userName" id="userName" placeholder="Enter Username">
	  </div>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
	  </div>
	  <% if(request.getAttribute("invalidCredentials") != null) {%>
	  <p class="h6" style="color:red"> <%= request.getAttribute("invalidCredentials") %> </p>
	  <%} %>
	  <div style="text-decoration: underline; color: blue">
	  	 <a href="#" >Forgot Password?</a>	  	
	  </div><br>
	  <button type="submit" class="btn btn-primary float-right" style="padding:1% 5% 1% 5%">Login</button>
	</form>
</div>
</body>
</html>