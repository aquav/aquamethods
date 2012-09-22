<%@ include file="/WEB-INF/pages/includes.jsp"%>
<%@ include file="/WEB-INF/pages/header.jsp"%>

<label for="welcome">Welcome:</label>

<h2>Outfit</h2>

<c:if test="${not empty outfit}">
	<div></div>
	<br />
	<div id="parent">

		<div class="image">

			<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
			<spring:url value="http://localhost:8080/{dbImagePath}"
				var="imageUrl">
				<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
			</spring:url>
			<img src="${fn:escapeXml(imageUrl)}" />
		</div>
		<div>
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

			</br>
			<c:forEach items="${outfit.tags}" var="tag">
						${tag.tag}
					</br>
			</c:forEach>

		</div>


	</div>

	<br>
	<br>
	<spring:url value="/person/{personId}/outfit" var="editUrl">
		<spring:param name="personId" value="${outfit.personId}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">My Wardrobe</a>
</c:if>
<%@ include file="/WEB-INF/pages/footer.jsp"%>
