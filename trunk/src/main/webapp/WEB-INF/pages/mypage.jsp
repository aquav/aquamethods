<%@ include file="/WEB-INF/pages/includes.jsp"%>
<%@ include file="/WEB-INF/pages/header.jsp"%>
<c:if test="${empty person}">No person found with this id.</c:if>
<label for="welcome">Welcome:</label>
${person.firstName} ${person.lastName}
<h2>My Wardrobe</h2>

<c:if test="${not empty person.outfits}">
	<div>

	</div>

	<br />
	<spring:url value="{personId}/outfit" var="editUrl">
		<spring:param name="personId" value="${person.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>

	<br />
	<c:forEach items="${person.outfits}" var="outfit">
		<table border="1">
			<tr>
				<td>${outfit.outfitPicture}</td>
				<td><c:forEach items="${outfit.tags}" var="tag">
						
						${tag.tag}
						</br>
					</c:forEach></td>
			</tr>
		</table>
	</c:forEach>
</c:if>

<c:if test="${empty person.outfits}">

	<h3>Your wardrobe is empty!!</h3>
	<h3>Would you like to upload pics</h3>

	<h2>
		<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
		<spring:param name="personId" value="${person.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>

	</h2>

</c:if>

<%@ include file="/WEB-INF/pages/footer.jsp"%>
