<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Updating User</title>
</head>
<body>
<form name="loginForm" method="post" action="<%=request.getContextPath()%>/update">
    Username: <input type="text" name="name" value=${user.name}> <br/>
    <p>  </p>
    Login: <input type="text" name="login" value=${user.login}> <br/>
    <p>  </p>
    Email: <input type="text" name="email" value=${user.email}> <br/>
    <p>  </p>
    <input type="hidden" name="id" value=${user.id} /> <br/>
    <input type="hidden" name="key" value="update"/> <br/>
    <input type="submit" value="Update" />
    <p></p>
    <a href="all">All Users</a>
</form>
</body>
</html>
