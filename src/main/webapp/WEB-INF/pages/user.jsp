<%@ include file="/WEB-INF/pages/includes.jsp"%>

<div id="loginForm">
	<h3>Create your free profile!</h3>
	<form:form method="post" modelAttribute="person">

		<p>
			<form:label path="firstName">First Name</form:label>
			<form:input path="firstName" id="firstName" />
		</p>
		<p>
			<form:label path="lastName">Last Name</form:label>
			<form:input path="lastName" id="lastName" />
		</p>
		<p>
			<form:label path="age">Age</form:label>
			<form:input path="age" id="age" />
		</p>
		<p>
			<form:label path="email">Email</form:label>
			<form:input path="email" id="email" />
		</p>
		<p>
			<form:label path="password">Password</form:label>
			<form:input path="password" id="password" type="password" />
		</p>
		<p>
			<input type="submit" value="Create my profile!" />
		</p>
	</form:form>
</div>