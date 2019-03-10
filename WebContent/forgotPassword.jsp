<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Forgot Password</title>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
  <span class="navbar-brand mb-0 h1" style="margin-left: 30%">Image Utility System</span>
</nav>

<div style="margin:10% 10% 0% 10%">
<form action="PasswordRecoveryController" method="post">
	<div class="input-group flex-nowrap">
	  <input type="text" class="form-control" required="true" placeholder="Enter Your Username" name="userName" id="userName"
	  <%if(request.getAttribute("userName")!=null){ %>
	  value="<%=request.getAttribute("userName") %>"<%} %> >
	  <div class="input-group-append">
	  		<button class="btn btn-outline-secondary" type="submit" name="submit" value="search">Search</button>
	  </div><br>
	  <% if(request.getAttribute("invalidCredentials") != null) {%>
	  <p class="h6" style="color:red"> <%= request.getAttribute("invalidCredentials") %> </p></div>
	  <%}if((request.getAttribute("userName") != null && request.getAttribute("userPassword")==null) && request.getAttribute("invalidUserName") == null){ %>
	  		</div><br>
	  		<p class="h6">What is your childhood name?</p>
	  		<div class="input-group flex-nowrap">
		  		<input type="text" class="form-control" required="true" placeholder="Enter Your Recovery Answer" name="recoveryAns" id="recoveryAns">
	 			 <div class="input-group-append">
			  		<button class="btn btn-outline-secondary" type="submit" name="submit" value="submit">Submit</button>
			  	</div>
	 		</div>
	  <%}%>
	</form>
	</div>
	<div style="margin:10% 10% 0% 10%">
		 <%if(request.getAttribute("userName") != null && request.getAttribute("password")!=null){ %>
		  		<div class="alert alert-success" role="alert">
				  Your Password is<b> <%= request.getAttribute("password") %></b> <a href="./index.jsp" class="alert-link">Login?</a>.
				</div> 		
		  <%} 
		 	if(request.getAttribute("userName") != null && request.getAttribute("password")==null && request.getAttribute("recoveryAns")!=null){%>
		 		 <p class="h6" style="color:red"> <%= request.getAttribute("recoveryAns") %> </p></div>
		 	<%}%>
	</div>


</body>
</html>