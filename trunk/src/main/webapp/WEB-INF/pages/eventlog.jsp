<%@ include file="/WEB-INF/pages/includes.jsp"%>
<h3>Event Log</h3>

<c:set var="now" value="<%=new java.util.Date()%>" />

<div id="futureevent">
	<h3>Future Events</h3>
	<c:forEach items="${eventFormList}" var="event">
		<c:if test="${now <  event.derivedDate }">
			<div id="eventlog" class="shadowevent">
				${event.name}
				<p>
					<fmt:formatDate type="date" value="${event.derivedDate}" />
				</p>
				<div id="imagethumbnail">
					<spring:url value="http://localhost:8080/{dbImagePath}"
						var="imageUrl">
						<spring:param name="dbImagePath"
							value="${event.eventOutfitImagePath }" />
					</spring:url>
					<img width="85" height="150" src="${fn:escapeXml(imageUrl)}" />
				</div>
			</div>
		</c:if>
	</c:forEach>
</div>

<div id="pastevent">
	<hr>
	<h3>Past Events</h3>
	<c:forEach items="${eventFormList}" var="event">
		<c:if test="${now > event.derivedDate }">
			<div id="eventlog" class="shadowevent">
				${event.name}
				<p>
					<fmt:formatDate type="date" value="${event.derivedDate}" />
				</p>
				<%-- 
				<p><fmt:formatDate type="date" dateStyle="medium" timeStyle="medium"
				value="${event.derivedDate}" /></p> --%>
				<div id="imagethumbnail">
					<spring:url value="http://localhost:8080/{dbImagePath}"
						var="imageUrl">
						<spring:param name="dbImagePath"
							value="${event.eventOutfitImagePath }" />
					</spring:url>
					<img width="85" height="150" src="${fn:escapeXml(imageUrl)}" />
				</div>
			</div>
		</c:if>
	</c:forEach>
</div>