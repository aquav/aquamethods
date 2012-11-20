<%@ include file="/WEB-INF/pages/includes.jsp"%>
<c:if test="${empty event.name}">
	<div id="eventForm">
		<h3>Get Ready!</h3>
		<h4>Create an Event</h4>

		<form:form method="post" modelAttribute="event">
			<div id="masterEvent">
				<p>
					<form:label path="name">Event Name</form:label>
					<form:input cssClass="formtag" path="name" id="name" />
				</p>
				<p>
					<form:label path="description">Event Description</form:label>
					<form:textarea cssClass="formtag" path="description"
						id="description" />
				</p>
				<br>
				<p>
					<form:label path="date">Date</form:label>
					<form:input cssClass="formtag" path="date" id="date" type="date" />
				</p>
				<p>
					<form:label path="date">Time</form:label>
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
					<form:select cssClass="formtag" path="minutes">
						<form:option value="00" />
						<form:option value="15" />
						<form:option value="30" />
						<form:option value="45" />
					</form:select>
					<form:select cssClass="formtag" path="ampm">
						<form:option value="AM" />
						<form:option value="PM" />
					</form:select>
				</p>
				<p>
					<form:label path="master">Master</form:label>
					<form:checkbox cssClass="formtag" path="master" />
				</p>

			<p>
				<input type="submit" value="Create event" />
			</p>
			</div>
		</form:form>
	</div>
</c:if>
<c:if test="${not empty event.name}">

	<h3>
		You have created event
		<h2>${event.name}</h2>
		on
		<h2>${event.date} ${event.hour}:${event.minutes} ${event.ampm}</h2>
	</h3>

	<p>This event will appear in your wardrobe. You can select outfit
		for this event now!</p>
</c:if>