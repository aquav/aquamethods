<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>


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

</body>
</html>
