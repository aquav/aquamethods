<%@ include file="/WEB-INF/pages/includes.jsp"%>

<c:if test="${not empty outfit}">
	<br />
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
		<c:if test="${not outfit.archived}">
			<div id="tagslarge">
				<form:form method="post" modelAttribute="tag">
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
		</br>
		<div  id="tags">
			<p><c:forEach items="${outfit.tags}" var="tag">
						${tag.tag}</c:forEach>
			</p>
		</div>
	</div>
</c:if>

