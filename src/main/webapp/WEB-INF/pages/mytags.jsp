<%@ include file="/WEB-INF/pages/includes.jsp"%>


<label for="welcome">Welcome:</label>

<h2>Outfit</h2>
<h2>
	<spring:url value="/person/{personId}/outfit" var="editUrl">
		<spring:param name="personId" value="${outfit.personId}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">My Wardrobe</a>
</h2>
<c:if test="${not empty outfit}">
	<br />
	<div id="outfitlarge">
		<div id="outimagelarge">

			<%-- <img src="<spring:url value="http://localhost:8080/resources/DSC_0915.JPG" htmlEscape="true" />"/> --%>
			<spring:url value="http://localhost:8080/{dbImagePath}"
				var="imageUrl">
				<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
			</spring:url>
			<img width="300" height="500" src="${fn:escapeXml(imageUrl)}" />
		</div>
		<div id="deleteOutfit">
		     <form:form method="delete">
                  <input type="submit" alt="Delete Outfit" src="/fashbook/static/images/delete.png" title="Delete Outfit" type="image" value="Delete Outfit"/>
             </form:form>
		</div>
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
			</br>
			<div>

				<c:forEach items="${outfit.tags}" var="tag">
						${tag.tag}
					</br>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>

