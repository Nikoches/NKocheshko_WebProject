
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Creation Form</title>
</head>
<body vlink="#FF9428">

<form name="loginForm" method="post" action="<%=request.getContextPath()%>/add" enctype="multipart/form-data">
    Username: <input type="text" name="name"/> <br/>
    <p>  </p>
    Login: <input type="text" name="login"/> <br/>
    <p>  </p>
    Email: <input type="text" name="email"/> <br/>
    <p>  </p>
        <div class="checkbox">
            <input type="file" name="file">
        </div>
    <p>  </p>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
</body>
</html>