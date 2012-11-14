<%@ include file="/WEB-INF/pages/includes.jsp"%>


<div id="leftmenu">
	<div class="leftmenulink">
		<spring:url value="/person/{personId}/outfit" var="editUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
		</spring:url>

		<a href="${fn:escapeXml(editUrl)}">My Wardrobe</a>
	</div>
	<div class="leftmenulink">
		<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>
	</div>
	<div class="leftmenulink">
		<a href="">Advance Search</a>
	</div>
	<div class="leftmenulink">
		<a href="">Get Ready</a>
	</div>
	<div class="leftmenulink">
			<spring:url value="/person/{personId}/outfit/archived" var="editUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}">Archive</a>
	</div>
</div>
