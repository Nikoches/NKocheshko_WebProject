<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Test Form</title>
</head>
<body vlink="#FF9428">

<form name="loginForm" method="post" action="<%=request.getContextPath()%>/test" enctype="multipart/form-data">
    <p>  </p>
    Field2: <input type="text" name="field2"/> <br/>
        <div class="checkbox">
            <input type="file" name="file">
        </div>
    <input type="text" name="login"/> <br/>
    <input type="submit" value="Create" />
</form>
</body>
</html>