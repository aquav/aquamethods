<%@ include file="/WEB-INF/pages/includes.jsp"%>


<c:if test="${empty person}">No person found with this id.</c:if>
<label for="welcome">Welcome:</label>
${person.firstName} ${person.lastName}
<!-- <h2>My Wardrobe</h2> -->

<div id="search">
	<form:form method="get" modelAttribute="search">
		<table>
			<tr>
				<td><form:label path="searchString">Tags</form:label></td>
				<td><form:input path="searchString" id="searchString" /></td>
				<td colspan="2"><input type="submit" value="Search Outfits" /></td>
				<td><form:checkbox path="matchWordFlag"
						value="Match exact word" /> <form:label path="matchWordFlag">Match Exact Word</form:label>
				</td>
			</tr>
			<tr>
				<td colspan="5"><spring:url
						value="/person/{personId}/outfit/search" var="editUrl">
						<spring:param name="personId" value="${person.id}" />
					</spring:url> <a href="${fn:escapeXml(editUrl)}">Advance Search</a></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${not empty search.searchString}">
		</br>
		<div>
			<h2>Filter Outfits - ${search.searchString}</h2>
		</div>
	</c:if>
</div>
<c:if test="${empty person.outfits}">

	<h3>Your wardrobe is empty!</h3>
	<c:if test="${not empty search.searchString}">
		<h3>For filter criteria - ${search.searchString}</h3>
	</c:if>
	<h2>Would you like to upload pics</h2>
</c:if>
<h2>
	<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
		<spring:param name="personId" value="${person.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>

</h2>

<c:if test="${not empty person.outfits}">


		<c:forEach items="${person.outfits}" var="outfit">
			<div id="outfit">
				<div id="outimage">

					<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
					<spring:url value="http://localhost:8080/{dbImagePath}"
						var="imageUrl">
						<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
					</spring:url>
					<img width="150" height="250" src="${fn:escapeXml(imageUrl)}" />
				</div>
				<div id="tags">

					<spring:url value="/person/{personId}/outfit/{outfitId}/tag"
						var="tagUrl">
						<spring:param name="personId" value="${person.id}" />
						<spring:param name="outfitId" value="${outfit.id}" />
					</spring:url>
					<a href="${fn:escapeXml(tagUrl)}">Add new Tag</a> <br />
					<c:forEach items="${outfit.tags}" var="tag">
						${tag.tag}
					</br>
					</c:forEach>

				</div>
			</div>
		</c:forEach>

</c:if>

