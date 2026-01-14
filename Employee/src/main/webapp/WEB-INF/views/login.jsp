<!DOCTYPE html>
<html>
<body>

<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username" placeholder="Username" /><br><br>
    <input type="password" name="password" placeholder="Password" /><br><br>
    <button type="submit">Login</button>
</form>

<c:if test="${param.error != null}">
    <p style="color:red;">Invalid username or password</p>
</c:if>

<c:if test="${param.logout != null}">
    <p style="color:green;">Logged out successfully</p>
</c:if>

</body>
</html>
