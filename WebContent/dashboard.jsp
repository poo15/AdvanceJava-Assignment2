<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.nagarro.java.Assignment3.UserService.*" %>
 <%@ page import="com.nagarro.java.Assignment3.Entity.*" %>
 <%@ page import="java.util.List" %>
 <% UserServiceImpl userService = new UserServiceImpl();
 		User user = (User)session.getAttribute("user");
 		int i=0;
 		List<Image> allBooks = userService.getAllBooks(user.getUser_Id());
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Welcome</title>
</head>
<body>

<!-- Navbar  -->
<nav class="navbar navbar-dark bg-dark">
  <span class="navbar-brand mb-0 h1" style="margin-left: 30%">Image Utility System</span>
</nav>

 <h1><%=session.getAttribute("user")%></h1> 

<!-- Image upload Input -->

<div style="margin:10% 10% 0% 10%">
<form action="ImageUpload" method="post" enctype="multipart/form-data">
	<p class="h6">Please select an image File to upload(Max size 10 MB)</h>
	<div class="input-group">
	  <div class="custom-file">
	    <input type="file" class="custom-file-input" name="file" id="inputFile" aria-describedby="inputGroupFileAddon04">
	    <label class="custom-file-label" for="inputFile">Choose file</label>
	  </div>
	  <div class="input-group-append">
	  		<button class="btn btn-outline-secondary" type="submit">Submit</button>
	  	
	  </div>
	  
	  <div class="input-group-append">
	    <button class="btn btn-outline-secondary" type="button" id="cancel" onclick="clearInput();">Cancel</button>
	  </div>
	 
	</div>
	</form>
	<% if(request.getAttribute("sizeExceed") != null) {%>
	  <p class="h6" style="color:red"> <%= request.getAttribute("sizeExceed") %> </p>
	  <%} %>
</div>

<!-- User Images View -->

<div style="margin:5% 10% 0% 10%">
	<p class="h6">Uploaded Images</p>
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">S.No</th>
	      <th scope="col">Name</th>
	      <th scope="col">Size</th>
	      <th scope="col">Preview</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
	     <% for(Image img: allBooks){%>
	 		<tr>
	 			<th scope="col"><%=i+1 %></th>
	 			<td><%=img.getName() %></td>
	 			<td><%=img.getSize() %></td>
	 			<td><img src="<%=img.getUrl()%>" class="img-fluid" alt="Book" height="100px" width="40%"></td>
	 			<td>
	 				<a href="EditBook?imageId=<%=img.getImageId()%>"><i  class="fas fa-pencil-alt" style="margin-right: 30%" id="<%=img.getImageId()%>"></i></a>
	 				<a href="DeleteBook?imageId=<%=img.getImageId()%>"><i class="fas fa-trash-alt"></i></a>
	 			</td>
	 		</tr>		
	 	<%i++;} %>
	  </tbody>
	</table>

</div>

 <script type="application/javascript">
    $('input[type="file"]').change(function(e){
        var fileName = e.target.files[0].name;
        console.log(fileName);
        var size = e.target.files[0].size;
        console.log(size);
        $('.custom-file-label').html(fileName);
    });

    function clearInput() {
    	$('.custom-file-label').html("Choose file");
    };
</script>



</body>
</html>