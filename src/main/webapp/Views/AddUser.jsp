
<html>
<head>
    <jsp:include page="Parts/HeadPart.jsp" />
    <title>Creation Form</title>
    <script>
        function validate() {
            if($('#email').val()==''||$('#password').val()==''||$('#login').val()==''||$('#name').val()==''){
                alert("Required fields empty!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body vlink="#FF9428">

<form name="loginForm" method="post" action="<%=request.getContextPath()%>/add" enctype="multipart/form-data">
<%--    <p>  </p>--%>
<%--    Username: <input type="text" name="name"/> <br/>--%>
<%--    <p>  </p>--%>
<%--    Login: <input type="text" name="login"/> <br/>--%>
<%--    <p>  </p>--%>
<%--    Email: <input type="text" name="email"/> <br/>--%>
<%--    <p>  </p>--%>
<%--    Password: <input type="password" name="password"/> <br/>--%>
<%--    <p>  </p>--%>

<%--    <select name="role">--%>
<%--        <option value="1">Administrator</option>--%>
<%--        <option value="2">User</option>--%>
<%--    </select>--%>
<%--    <p>  </p>--%>
<%--    <br><br>--%>
<%--        <div class="checkbox">--%>
<%--            <input type="file" name="file">--%>
<%--        </div>--%>
<%--    <p>  </p>--%>
<%--    <button type="submit" class="btn btn-default">Submit</button>--%>

        <label for="email"> Username:</label>
        <input type="text" class="form-control" id="name" name="name">
        <br>
        <label for="email"> Email:</label>
        <input type="text" class="form-control" id="email" name="email">
        <br>
        <label for="login"> Email:</label>
        <input type="text" class="form-control" id="login" name="login">
        <br>
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" name="password">
        <br>
    <select name="role">
        <option value="1">Administrator</option>
        <option value="2">User</option>
    </select>
    <p>  </p>
    <br><br>
    <div class="checkbox">
        <input type="file" name="file">
    </div>
    <p>  </p>
    <button type="submit" onclick="return validate();" class="btn btn-default">Submit</button>
</form>
</body>
</html>