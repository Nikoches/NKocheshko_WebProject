<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script>
        function getCity() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/chapter_008_war/city',
                dataType: 'json'
            }).done(function(data) {
                html = "";
                for(var key in data) {
                    html += "<option value=" + key  + ">" +data[key] + "</option>"
                }
                document.getElementById("city").innerHTML = html;
                console.log(data)
            }).fail(function(err){
                console.log(err)
                alert("err");
            });
        }

    </script>
</head>
<body vlink="#FF9428" onload="getCity()">

<form name="loginForm" method="post" action="<%=request.getContextPath()%>/add" enctype="multipart/form-data">
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
    <select name="city" id="city">
    </select>
    <br><br>
    <div class="checkbox">
        <input type="file" name="file">
    </div>
    <p>  </p>
    <button type="submit"  class="btn btn-default">Submit</button>
</form>
</body>
</html>