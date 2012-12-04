<%@ include file="/WEB-INF/pages/includes.jsp"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
<h3>My Events</h3>
<div id="futureevent">
	
	<c:forEach items="${eventFormList}" var="event">
		<%-- <c:if test="${now <  event.derivedDate }"> --%>
			<c:if test="${now <  event.derivedDate }">
				<c:set var="stylevalue" value="futureeventlog" />
			</c:if>
			<c:if test="${now >  event.derivedDate }">
				<c:set var="stylevalue" value="eventlog" />
			</c:if>
			<div id="${stylevalue}">
				<div id="eventlogtitle">
				${event.name}
				</div>
				<div id="eventlogdate">
		
					<fmt:formatDate type="date" value="${event.derivedDate}" />
				</div>
				<div id="imagethumbnail">
					<spring:url value="http://localhost:8080/{dbImagePath}"
						var="imageUrl">
						<spring:param name="dbImagePath"
							value="${event.eventOutfitImagePath }" />
					</spring:url>
					<img width="95" src="${fn:escapeXml(imageUrl)}" />
				</div>
			<spring:url value="/person/{personId}/getready/event/{eventId}/outfit/{outfitId}"
				var="tagUrl">
				<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
				<spring:param name="eventId" value="${event.id}" />
				<spring:param name="outfitId" value="${event.eventOutfitId}" />
			</spring:url>
			<a href="${fn:escapeXml(tagUrl)}"><span class="link-spanner"></span></a>
			</div>
			
		<%-- </c:if> --%>
	</c:forEach>
</div>

<%-- <div id="pastevent">
	<hr>
	<h3>Past Events</h3>
	<c:forEach items="${eventFormList}" var="event">
		<c:if test="${now > event.derivedDate }">
			<div id="eventlog" class="shadowevent">
				${event.name}
				<br>
					<fmt:formatDate type="date" value="${event.derivedDate}" />
				
				
				<p><fmt:formatDate type="date" dateStyle="medium" timeStyle="medium"
				value="${event.derivedDate}" /></p>
				<div id="imagethumbnail">
					<spring:url value="http://localhost:8080/{dbImagePath}"
						var="imageUrl">
						<spring:param name="dbImagePath"
							value="${event.eventOutfitImagePath }" />
					</spring:url>
					<img width="85" height="150" src="${fn:escapeXml(imageUrl)}" />
				</div>
				<spring:url value="/person/{personId}/getready/event/{eventId}/outfit/{outfitId}"
				var="tagUrl">
				<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
				<spring:param name="eventId" value="${event.id}" />
				<spring:param name="outfitId" value="${event.eventOutfitId}" />
			</spring:url>
			<a href="${fn:escapeXml(tagUrl)}"><span class="link-spanner"></span></a>
			</div>
		</c:if>
	</c:forEach>
</div> --%>