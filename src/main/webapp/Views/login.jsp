<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <jsp:include page="Parts/HeadPart.jsp" />
    <title>Login Page</title>
    <script>
        function validate() {
            if($('#email').val()==''||$('#pwd').val()==''){
                alert("Required fields empty!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/LoginServlet" method="post">
    <span style="color: red; "> <c:out value="${red}"></c:out></span><br>
    <p>Credentials</p>
    <div class="form-group">
        <label for="email"> Username:</label>
        <input type="text" class="form-control" id="email" name="user">
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pwd" name="pwd">
    </div>
    <div class="checkbox">
        <label><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" onclick="return validate();" class="btn btn-default">Submit</button>
</form>
</body>
</html>