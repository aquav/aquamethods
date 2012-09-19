<%@ include file="/WEB-INF/pages/includes.jsp" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>

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
        </br>
        <c:forEach items="${person.outfits}" var="outfit">
        	<table border="1">
        	<tr>
        		<td>${outfit.outfitPicture}</td>
        		<td>
					<c:forEach items="${outfit.tags}" var="tag">
						
						${tag.tag}
						</br>
					</c:forEach>
				</td>
        	</tr>
        	</table>
        </c:forEach>
    </c:if>
    <c:if test="${empty person}">No person found with this id.</c:if>

<%@ include file="/WEB-INF/pages/footer.jsp" %>
