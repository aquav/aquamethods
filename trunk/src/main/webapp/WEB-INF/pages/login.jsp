<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<div id="loginForm">
	<form action="j_spring_security_check" method="post" >
		<h3>Log in to Fashbook</h3>

			<p>
			<label for="j_username">Username</label>
			<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
			</p>

			<p>
			<label for="j_password">Password</label>
			<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
			</p>
			
			<c:if test="${param.error == 'failed'}">
				<p><span class="loginError">Login failed, check your input for error.</span></p>
			</c:if>
			
			<p><input type="submit" value="Login"/></p>
			
	</form>
	<hr>
	<h3>OR</h3> 
	<h2>
	<a  href="<c:url value="/person/newuser" />" >Create New Profile</a>
	</h2>			
	<%-- <p class="message">${message}</p> --%>
</div>	
