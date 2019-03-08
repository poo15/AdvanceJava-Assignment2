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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>Welcome</title>
</head>
<style>
	a{
		color:white;
	}
	a:HOVER {
	color:black;
}
</style>
<body>

<!-- Navbar  -->
<nav class="navbar navbar-dark bg-dark">
  <span class="navbar-brand mb-0 h1" style="margin-left: 30%">Image Utility System</span>
</nav>

<!-- Image upload Input -->

<div style="margin:10% 10% 0% 10%">
<form action="ImageUpload" method="post" enctype="multipart/form-data">
	<div class="form-group">
	    <label for="bookName">Book Name</label>
	    <input type="text" class="form-control" name="bookName" id="bookName" placeholder="Enter Book Name">
	</div>
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
	 <% } %>
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
	 			<div class="row">
	 				<div class="col-6 m-2">
			 			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#<%=img.getImageId()%>Modal">
						  <i class="fas fa-pencil-alt" style="margin-right: 30%" id="<%=img.getImageId()%>"></i>
						</button>
	 				</div>
	 				<div class="col-6 m-2">
	 				<form action="DeleteBook" >		
			 			<button type="submit" name="bookId" value="<%=img.getImageId()%>" class="btn btn-primary">
						  <i class="fas fa-trash-alt"></i>
			 			</button>
			 		</form>
	 				</div>
	 			</div>
	 			<form action="DeleteBook">
	 			<div class="modal fade" id="<%=img.getImageId()%>Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Your Book Details</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				      	<div class="form-group">
						    <label for="bookName">Book Name</label>
						    <input type="text" class="form-control" name="bookName" id="bookName" value="<%=img.getName() %>">
						</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				        <button type="submit" name="submit" value="edit-<%=img.getImageId() %>" class="btn btn-primary">Save changes</button>
				      </div>
				    </div>
				  </div>
				</div>
				</form>	
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