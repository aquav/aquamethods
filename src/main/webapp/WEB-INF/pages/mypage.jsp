<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>My Wardrobe</title>
</head>
<body>
<h2>My Wardrobe</h2>
    <c:if test="${not empty person}">       
        <div>
            <label for="firstName">First Name:</label>
            <div id="firstName">${person.firstName}</div>
        </div>
        <br/>
         <div>
            <label for="lastName">Last Name:</label>
            <div id="lastName">${person.lastName}</div>
        </div>
        <br/>
        <div>
            <label for="email">Email:</label>
            <div id="email">${person.email}</div>
        </div>
        <br/>
        <div>
            <label for="age">Age:</label>
            <div id="age">${person.age}</div>
        </div>
        <c:forEach items="${outfits}" var="outfit">
        	<tr>
        		<td>${outfit.outfitPicture}</td>
        	</tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty person}">No person found with this id.</c:if>
</body>
</html>

