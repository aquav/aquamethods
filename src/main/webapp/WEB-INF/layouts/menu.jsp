<%@ include file="/WEB-INF/pages/includes.jsp"%>


<div id="leftmenu">
	<spring:url value="/person/{personId}/outfit" var="editUrl">
		<spring:param name="personId"
			value="${sessionScope['scopedTarget.userSessionData'].personId}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">My Wardrobe</a> 
	<br>
	
	<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
		<spring:param name="personId"
			value="${sessionScope['scopedTarget.userSessionData'].personId}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>
	<br>
	<a href="">Advance Search</a>
	<br>
	<a href="">Archive</a>
</div>
