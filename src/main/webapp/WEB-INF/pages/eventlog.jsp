<%@ include file="/WEB-INF/pages/includes.jsp"%>
<h3>Event Log</h3>

	<c:forEach items="${eventFormList}" var="event">

		<div id="eventlog" class="shadow">
			${event.name} on ${event.derivedDate}
		</div>
	</c:forEach>