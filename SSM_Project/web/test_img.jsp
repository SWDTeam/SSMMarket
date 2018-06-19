<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <link href="admin_page/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="admin_page/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
    <link href="admin_page/css/paper-dashboard.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="admin_page/css/themify-icons.css" rel="stylesheet">
  </head>
  <body>
    <form action="AddImg" method="POST" enctype="multipart/form-data">
      <div style="width:200px; position:relative; height:200px; overflow:visible;">
        <div id="M-btn-Img" style="height: 200px; width: 200px; position: absolute;" class="btn">Image</div>
        <input type="file" name="picture" onchange="loadFile(event)" style=" width: 200px; top:0px; height:  200px;opacity:0;"/>
        <br/>   <br/>   <br/>
        <script>
          var loadFile = function (event) {
            var output = document.getElementById('M-btn-Img');
            output.style.backgroundImage = 'url("' + URL.createObjectURL(event.target.files[0]) + '")';
            output.style.backgroundSize = "contain";
            output.style.backgroundRepeat = "no-repeat";
          };
        </script>
        <button class="btn btn-danger">Submit</button>
      </div></form>
    <p>${requestScope.RESULT}</p>
  </body>
  <%@include file="layout/admin--script.jsp" %>
</html>
