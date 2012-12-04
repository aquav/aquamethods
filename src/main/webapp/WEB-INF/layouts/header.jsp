<%@ include file="/WEB-INF/pages/includes.jsp"%>
<c:url value="/logout" var="logoutUrl" />
<div id="headerimage">
	<h1>Fashbook</h1>
</div>

<div id="headertitle">
	Your virtual wardrobe!!
</div>

<c:if test="${not empty sessionScope['scopedTarget.userSessionData'].personFirstName}">
<div id="headernav">
	${sessionScope['scopedTarget.userSessionData'].personFirstName}
	${sessionScope['scopedTarget.userSessionData'].personLastName} &nbsp;
	<a	href="${logoutUrl}">Logout</a>
</div>
</c:if>