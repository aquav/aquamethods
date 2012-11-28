<%@ include file="/WEB-INF/pages/includes.jsp"%>

<c:if test="${not empty outfit}">
	<div id="outfitlarge" class="shadow">
		<div id="outimagelarge">

			<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
			<spring:url value="http://localhost:8080/{dbImagePath}"
				var="imageUrl">
				<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
			</spring:url>
			<img width="300" height="500" src="${fn:escapeXml(imageUrl)}" />
		</div>
		<div id="deleteOutfit">
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
		<div id="tagslarge">
			<c:if test="${not outfit.archived}">

				<form:form method="post"
					action="/fashbook/person/${outfit.personId}/outfit/${outfit.id}/tag"
					modelAttribute="tag">
					<table>
						<tr>
							<td><form:label path="tag">Tag</form:label></td>
							<td><form:input path="tag" id="tag" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Add" /></td>
						</tr>
					</table>
					<!-- Hidden value - need to set person id else it will go as null in form -->
					<input name="outfitId" type="hidden" value="${outfit.id}" />
				</form:form>

			</c:if>
			<hr>
			<c:forEach items="${outfit.tags}" var="tag">
				<p>${tag.tag}</p>
			</c:forEach>


		</div>
		<c:if test="${not outfit.archived}">
		<div id="futureevents">
			<c:if test="${not empty outfit.futureEvents}">
			<h3>You have planned event with no outfit assigned!!</h3>
			If you are planning to wear this outfit in one of these event. You can select by clicking button below.
			This will record it and keep it in history.
			<c:forEach items="${outfit.futureEvents}" var="futureEvent">

			<spring:url value="/person/{personId}/getready/event/{eventId}/outfit/{outfitId}"
				var="tagUrl">
				<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
				<spring:param name="outfitId" value="${outfit.id}" />
				<spring:param name="eventId" value="${futureEvent.id}" />
			</spring:url>
			<form:form action="${fn:escapeXml(tagUrl)}" method="post">
				<h3>${futureEvent.name}</h3>
			<input type="submit" value="Select this event"/>	
			</form:form>			
			</c:forEach>
			</c:if>
		</div>
		<div id="events">
		<c:forEach items="${outfit.outfitEvents}" var="outfitEvent">
			You wore or planning to wear this outfit for <h3>${outfitEvent.name}</h3>
		</c:forEach>
		</div>
		</c:if>
	</div>
</c:if>

