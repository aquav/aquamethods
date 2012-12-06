<%@ include file="/WEB-INF/pages/includes.jsp"%>


<c:if test="${empty person}">No person found with this id.</c:if>
<%-- <label for="welcome">Welcome:</label>
${person.firstName} ${person.lastName}
<!-- <h2>My Wardrobe</h2> --> --%>
<c:if test="${not empty search}">
	<div id="search">
		<form:form method="get" modelAttribute="search">
					Tags
					<form:input path="searchString" id="searchString" />
					<input type="submit" value="Search Outfits" />
					<form:checkbox path="matchWordFlag"
							value="Match exact word" /> <form:label path="matchWordFlag">Match Exact Word</form:label>
		</form:form>

		<c:if test="${not empty search.searchString}">
			</br>
			<div>
				<h2>Filter Outfits - ${search.searchString}</h2>
			</div>
		</c:if>
	</div>

	<c:if test="${empty person.outfits}">

		<h3>
			Your wardrobe is empty!
			<c:if test="${not empty search.searchString}">
		for filter criteria - ${search.searchString}
	</c:if>
		</h3>
		<h2>Would you like to upload pics</h2>

		<spring:url value="/person/{personId}/outfit/upload" var="editUrl">
			<spring:param name="personId"
				value="${sessionScope['scopedTarget.userSessionData'].personId}" />
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}">Upload Pics</a>
	</c:if>
	<br>
</c:if>
<c:if test="${not empty person.outfits}">

	<c:forEach items="${person.outfits}" var="outfit">

		<div id="outfit" class="shadow">
			<div id="outimage">
				<spring:url value="http://localhost:8080/{dbImagePath}"
					var="imageUrl">
					<spring:param name="dbImagePath" value="${outfit.outfitPicture}" />
				</spring:url>
				<img width="150" height="250" src="${fn:escapeXml(imageUrl)}" />
			</div>
			<div id="tags">
				<c:forEach items="${outfit.tags}" var="tag">
						${tag.tag}
					</br>
				</c:forEach>
			</div>
			<spring:url value="/person/{personId}/outfit/{outfitId}"
				var="tagUrl">
				<spring:param name="personId" value="${person.id}" />
				<spring:param name="outfitId" value="${outfit.id}" />
			</spring:url>
			<a href="${fn:escapeXml(tagUrl)}"><span class="link-spanner"></span></a>
		</div>
	</c:forEach>

</c:if>
<!-- <script type="text/javascript"> 


//$(document).ready(function() {
	$("#dialog").dialog({autoOpen : false, modal : true, show : "blind", hide : "blind"});
	 function add(e) {
	     alert("Hello world!");
	    var url = $(e).attr('href');
	    
	    $("#dialog").load(url, function() {
	        $("#dialog").dialog("open");
	        
	    });
	    
	 }
//	 });

</script> -->

