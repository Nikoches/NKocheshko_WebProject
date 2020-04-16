<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Table of Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Users:</h2>
<button onclick="location.href = 'add';" class="btn btn-default" >Add User</button>
<button onclick="location.href = 'remove';" class="btn btn-default" >Remove All</button>
<div class=="container">
    <table class="table">
    <thead>
    <tr>
        <th>Download Link</th>
        <th>Picture</th>
        <th>id</th>
        <th>email</th>
        <th>name</th>
        <th>login</th>
    </tr>
    </thead>
        <tbody>
    <c:forEach items="${list}" var = "user">
        <tr>
            <td><a href="${pageContext.servletContext.contextPath}/download?name=${user.imageName}">Download</a></td>
            <td>
                <img src="${pageContext.servletContext.contextPath}/download?name=${user.imageName}" width="100px" height="100px"/>
            </td>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><a href="remove?id=${user.id}&key=delete">Delete</a></td>
        <td><a href="update?id=${user.id}&key=update">Update</a></td>
        </tr>
    </c:forEach>
        </tbody>
</table>
</div>
</body>
</html>
