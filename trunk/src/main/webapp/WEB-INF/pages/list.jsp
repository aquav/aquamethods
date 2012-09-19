<%@ include file="/WEB-INF/pages/includes.jsp" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>
	<h1>Congratulations ${person.firstName} !!</h1>

	<h3>Your profile has been created -  ${person.id}</h3>
	<h3>Would you like to upload pics</h3>
	
	<h2><a href="../person/outfit">Upload Pics</a></h2>
	
<%@ include file="/WEB-INF/pages/footer.jsp" %>