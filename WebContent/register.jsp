<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Register</title>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
  <span class="navbar-brand mb-0 h1" style="margin-left: 30%">Image Utility System</span>
</nav>

<div style="margin: 10% ">
	<form action="RegisterController" method="post">
	  <div class="form-group">
	    <label for="userName">Username</label>
	    <input type="text" class="form-control" name="userName" required="true" id="userName" placeholder="Enter Username">
	  </div>
	  <% if(request.getAttribute("invalidCredentials") != null) {%>
	  <p class="h6" style="color:red"> <%= request.getAttribute("invalidCredentials") %> </p>
	  <%} %>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" name="password" required="true"  id="password" placeholder="Password">
	  </div>
	  <div class="form-group">What is your childhood name?</label>
	    <input type="text" class="form-control" name="recoveryAns" required="true"  id="recoveryAns" placeholder="Enter Your Answer">
	  </div>
	  <div style="text-decoration: underline; color: blue">
	  	 <a href="./index.jsp" >Login?</a>	
	  </div><br>
	  <button type="submit" class="btn btn-primary float-right" style="padding:1% 5% 1% 5%">Register</button>
	</form>
</div>

</body>
</html>