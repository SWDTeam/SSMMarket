<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AddImg" method="POST" enctype="multipart/form-data">
            <input type="text" name="productId" />
            <input type="file" name="picture" />
            <input type="submit" value="Submit"/>
        </form>
        <p>${requestScope.RESULT}</p>
    </body>
</html>
