<%@ include file="/WEB-INF/pages/includes.jsp"%>
<c:if test="${empty event.name}">
	<div id="eventform">
		<h3>Get Ready! Create an Event</h3>
		<div id="masterevent">
		<form:form method="post" modelAttribute="event">
				<p>
					<form:label path="name">Event Name</form:label>
					<form:input cssClass="formtag" path="name" id="name" />
					<%-- <form:label path="master">Master Event ?</form:label>
					<form:checkbox cssClass="formtag" path="master" /> --%>
				</p>
				<p>
					<form:label path="description">Event Description</form:label>
					<form:textarea cssClass="formtag" path="description"
						id="description" />
				</p>
				<br>
				<p>
					<form:label path="date">Date (mm/dd/yyyy)</form:label>
					<form:input cssClass="formtag" path="date" id="date" />
					<%-- <form:label path="master">All Day Event ?</form:label>
					<form:checkbox cssClass="formtag" path="allDayEvent" /> --%>
				</p>
				<p>
					<form:label path="date">Start Time</form:label>
					<form:select cssClass="formtag" path="hour">
						<form:option value="01" />
						<form:option value="02" />
						<form:option value="03" />
						<form:option value="04" />
						<form:option value="05" />
						<form:option value="06" />
						<form:option value="07" />
						<form:option value="08" />
						<form:option value="09" />
						<form:option value="10" />
						<form:option value="11" />
						<form:option value="12" />
					</form:select>
					:
					<form:select cssClass="formtag" path="minute">
						<form:option value="00" />
						<form:option value="15" />
						<form:option value="30" />
						<form:option value="45" />
					</form:select>
					<form:select cssClass="formtag" path="ampm">
						<form:option value="am" />
						<form:option value="pm" />
					</form:select>
				</p>

			<p>
				<input type="submit" value="Create event" />
			</p>
		</form:form>
		</div>
	</div>
</c:if>
<c:if test="${not empty event.name}">

	<h3>
		You have created event
		<h2>${event.name}</h2>
		on
		<h2>${event.date} ${event.hour}:${event.minute} ${event.ampm}</h2>
	</h3>
			<spring:url value="/person/{personId}/outfit"
				var="tagUrl">
				<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
			</spring:url>
	<p>This event will appear in your wardrobe. Go to <a href="${fn:escapeXml(tagUrl)}">My Wardrobe</a> to select outfit
		for this event now!</p>
</c:if>