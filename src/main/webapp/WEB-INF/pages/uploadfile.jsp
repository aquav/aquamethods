<%@ include file="/WEB-INF/pages/includes.jsp" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>

	<form:form modelAttribute="uploadOutfit" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<p>
				<legend>Upload Pics!!</legend>
			</p>
			<p>
				<form:label path="fileData">File</form:label>
				<br />
				<form:input path="fileData" type="file" />
			</p>

			<!-- Hidden value - need to set person id else it will go as null in form -->
			<input name="personId" type="hidden" value="${uploadOutfit.personId}"/>
			<p>
				<input type="submit" value="Upload" />
			</p>

		</fieldset>
	</form:form>
	   <spring:url value="/person/{personId}/outfit" var="editUrl">
        	<spring:param name="personId" value="${uploadOutfit.personId}" />
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}">My Wardrobe</a>
        
<%@ include file="/WEB-INF/pages/footer.jsp" %>