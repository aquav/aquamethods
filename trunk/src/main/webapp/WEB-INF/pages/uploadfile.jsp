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

			<p>
				<input type="submit" value="Upload" />
			</p>

		</fieldset>
	</form:form>
	<h2><a href="../person/vishal">My Wardrobe</a></h2>
<%@ include file="/WEB-INF/pages/footer.jsp" %>