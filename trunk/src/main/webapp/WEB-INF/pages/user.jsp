<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Create profile</title>
</head>
<body>
<h2>Create your free profile!</h2>
<form:form method="post" modelAttribute="person">
 
    <table>
    <tr>
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" id ="firstName" /></td> 
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" id ="lastName" /></td>
    </tr>
    <tr>
        <td><form:label path="age">Age</form:label></td>
        <td><form:input path="age" id ="age" /></td>
    </tr>
        <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" id ="email" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Create"/>
        </td>
    </tr>
</table>  
     
</form:form>
</body>
</html>