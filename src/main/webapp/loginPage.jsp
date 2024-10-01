<%
response.setContentType("text/html");
if(session.getAttribute("name")!=null){
	out.println("<script>");
	out.println("alert('Already logged in!!!')");
	out.println("window.open('Home_page.html','_self');");
	out.println("</script>");
	
}
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="loginstyle.css">
  </head>
  <body>
  <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
    <div class="center">
      <button id="back-btn">
        <i class="bx bx-arrow-back" id="icon"></i>
        <span>Back</span>
      </button>
      <img id="img1" src="logo.png">
      <h1>Admin Login</h1>
      <form action="login" method="get">
        <div class="txt_field">
          <input type="text" name="username" required>
          <span></span>
          <label>Username</label>
        </div>
        <div class="txt_field">
          <input type="password" name="password" required>
          <span></span>
          <label>Password</label>
        </div>
     
        <input type="submit" value="Login">
        
      </form>
    </div>

    <script>
        document.getElementById("back-btn").addEventListener("click", () => {
        	location.href = "http://localhost:8080/Project_Naac_6/Home_page.html";
        });
        var status = document.getElementById("status").value;
    	if(status=="failed"){
        	alert("Sorry, Invalid Username or Password")
        }
        if(status=="success"){
        	alert("Congrats, Logged in Successfully");
        	window.open('Home_page.html',"_self");
        }
        
        
    </script>

  </body>
</html>
