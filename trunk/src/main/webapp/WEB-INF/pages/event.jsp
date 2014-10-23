<%@ include file="/WEB-INF/pages/includes.jsp"%>
<script type="text/javascript">

function yesnoCheck() {
    if (document.getElementById('yesCheck').checked) {
        document.getElementById('ifYes').style.display = 'none';
    }
    else document.getElementById('ifYes').style.display = 'block';

}

</script>
<div id="eventform">
	<h3>Edit your Event</h3>
	<div id="masterevent">

		<form:form method="put" modelAttribute="event">
			<p>
				<form:label path="name">Specific Event?</form:label>
				<form:radiobuttons onclick="javascript:yesnoCheck();" cssClass="formtag" path="eventType" id="yesCheck" value="Y" />
				<form:radiobuttons onclick="javascript:yesnoCheck();" cssClass="formtag" path="eventType" id="noCheck" value="N" />
			</p>
			<p>
				<form:label path="name">Event Name</form:label>
				<form:input cssClass="formtag" path="name" id="name" />
<%-- 				<form:label path="master">Master Event ?</form:label>
				<form:checkbox cssClass="formtag" path="master" /> --%>
			</p>
			<p>
				<form:label path="description">Event Description</form:label>
				<form:textarea cssClass="formtag" path="description"
					id="description" />
			</p>
			<br>
			<div id="ifYes" style="display:none">
			<p>
				<form:label path="date">Date (mm/dd/yyyy)</form:label>
				<form:input cssClass="formtag" path="date" id="date" />
<%-- 				<form:label path="master">All Day Event ?</form:label>
				<form:checkbox cssClass="formtag" path="allDayEvent" /> --%>
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
			</div>
			<p>
				<input type="submit" value="Save Changes" />
			</p>
		</form:form>
	</div>
	<div>
			<spring:url
			value="/person/{personId}/getready/event/{eventId}"
			var="tagUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
			<spring:param name="eventId" value="${event.id}" />
		</spring:url>
		
		<form:form action="${fn:escapeXml(tagUrl)}" method="delete">
              Delete Event <input
				src="/fashbook/static/images/delete.png" title="Delete this Event"
				type="image"   />
		</form:form>
	</div>
	<div id="mastereventimage">
		<spring:url value="http://localhost:8080/{dbImagePath}" var="imageUrl">
			<spring:param name="dbImagePath"
				value="${event.eventOutfitImagePath }" />
		</spring:url>
		<img width="170" height="300" src="${fn:escapeXml(imageUrl)}" />

		<spring:url
			value="/person/{personId}/getready/event/{eventId}/outfit/{outfitId}"
			var="tagUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
			<spring:param name="eventId" value="${event.id}" />
			<spring:param name="outfitId" value="${event.eventOutfitId}" />
		</spring:url>
		<c:if test ="${event.eventOutfitId != 0 }">
		<form:form action="${fn:escapeXml(tagUrl)}" method="delete">
              remove outfit<input
				src="/fashbook/static/images/remove.png" title="Remove Outfit from this Event"
				type="image"   />
		</form:form>
		</c:if>
	</div>
</div>