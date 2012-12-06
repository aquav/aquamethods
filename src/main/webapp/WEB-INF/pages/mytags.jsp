<%@ include file="/WEB-INF/pages/includes.jsp"%>

<c:if test="${not empty outfit}">
		<div id="outfitlarge">
		<div id="deleteoutfit">
			<form:form
				action="/fashbook/person/${outfit.personId}/outfit/${outfit.id}"
				method="delete">
              delete <input alt="Delete Outfit"
					src="/fashbook/static/images/delete.png" title="Delete Outfit"
					type="image" value="Delete Outfit" />
			</form:form>
			<c:if test="${not outfit.archived}">
				<form:form
					action="/fashbook/person/${outfit.personId}/outfit/${outfit.id}/archive"
					method="post">
              archive <input alt="Delete Outfit"
						src="/fashbook/static/images/archive.png" title="Archive Outfit"
						type="image" value="Archive Outfit" />
				</form:form>
			</c:if>
		</div>
		<div id="outimagelarge" class="shadow">

			<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
			<spring:url value="http://localhost:8080/{dbImagePath}"
				var="imageUrl">
				<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
			</spring:url>
			<img width="360" height="600" src="${fn:escapeXml(imageUrl)}" />
		</div>



		<div id="tagslarge">
			<c:if test="${not outfit.archived}">

				<form:form method="post"
					action="/fashbook/person/${outfit.personId}/outfit/${outfit.id}/tag"
					modelAttribute="tag">
							<form:label path="tag">Tag</form:label>
							<form:input path="tag" id="tag" />
						<input type="submit" value="Add" />

					<!-- Hidden value - need to set person id else it will go as null in form -->
					<input name="outfitId" type="hidden" value="${outfit.id}" />
				</form:form>

			</c:if>
			<hr>
			<c:forEach items="${outfit.tags}" var="tag">
				<div id= "tagtext">${tag.tag}</div>
			</c:forEach>


		</div>
		
		<div id="events">
		<table>
		<thead><tr><th>Outfit log</th></tr></thead>
		
		<c:forEach items="${outfit.outfitEvents}" var="outfitEvent">
		<tr><td>	 ${outfitEvent.name} </td></tr>
		</c:forEach>
		</table>
		</div>
		

	<c:if test="${not outfit.archived}">
			<div id="unassignedevents">
			<c:if test="${not empty outfit.futureEvents}">
			<table>
			<thead><tr><th colspan="2">You have event with no outfit assigned!!<br>
			<!-- Click Select if you are planning to wear this outfit on this event --></th></tr></thead>
<!-- 			<tr><td colspan="2">If you are planning to wear this outfit in one of these event. You can select by clicking button below.
			This will record it and keep it in history.</td></tr> -->
			<c:forEach items="${outfit.futureEvents}" var="futureEvent">

			<spring:url value="/person/{personId}/outfit/{outfitId}/event/{eventId}"
				var="tagUrl">
				<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
				<spring:param name="outfitId" value="${outfit.id}" />
				<spring:param name="eventId" value="${futureEvent.id}" />
			</spring:url>
			<form:form action="${fn:escapeXml(tagUrl)}" method="post">
				<tr><td>${futureEvent.name} <br>on <fmt:formatDate type="date" value="${futureEvent.derivedDate}" /></td>
				<td><input type="submit" value="Select"/>	</td></tr>
			</form:form>			
			</c:forEach>
			</table>
			</c:if>
		</div>
	</c:if>	
	</div>
</c:if>

