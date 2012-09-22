<%@ include file="/WEB-INF/pages/includes.jsp"%>
<%@ include file="/WEB-INF/pages/header.jsp"%>
<c:if test="${empty person}">No person found with this id.</c:if>
<label for="welcome">Welcome:</label>
${person.firstName} ${person.lastName}
<h2>My Wardrobe</h2>

<c:if test="${not empty person.outfits}">
	<div>

		<br />
		<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
			<spring:param name="personId" value="${person.id}" />
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>
	</div>
	<br />
	<div id="parent">
		
		<c:forEach items="${person.outfits}" var="outfit">
			<div class="image">
				
					<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
					<spring:url value="http://localhost:8080/{dbImagePath}"
							var="imageUrl">
							<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
						</spring:url> <img src="${fn:escapeXml(imageUrl)}" />
			</div>
			<div class = "tag">
					Add new Tag <br /> 
					<c:forEach items="${outfit.tags}"
							var="tag">
						${tag.tag}
					</br>
					</c:forEach>
			
			</div>
		</c:forEach>
	

	</div>
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
