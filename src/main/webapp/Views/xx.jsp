<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table of Users</title>
</head>
<body>
<h2>Users:</h2>
<a href="add">Add User</a>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>name</th>
        <th>login</th>
    </tr>
    <c:forEach items="${list}" var = "user">
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><a href="remove?id=${user.id}&key=delete">Delete</a></td>
        <td><a href="update?id=${user.id}&key=update">Update</a></td>
    </c:forEach>
</table>
</body>
</html>
