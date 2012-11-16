<%@ include file="/WEB-INF/pages/includes.jsp"%>


<script src="/fashbook/static/js/jquery.Jcrop.js"></script>

<script type="text/javascript">
/* $(function() {
    $( "#actualImage" ).resizable({ aspectRatio: .75 });
}); */

$(function () {
    $('#actualImage').Jcrop({
     setSelect: [0, 0, 300, 500],
     aspectRatio: 3/5,
     addClass: 'custom',
     bgColor: 'black',
     bgOpacity: .4,
     //sideHandles: true
     allowResize: true,
     allowSelect: false,
     onSelect: storeCoords
     
 });
});

function storeCoords(c) {

 jQuery('#X1').val(Math.round(c.x));
 jQuery('#Y1').val(Math.round(c.y)); 
 jQuery('#X2').val(Math.round(c.x2));
 jQuery('#Y2').val(Math.round(c.y2)); 
 jQuery('#W').val(Math.round(c.w));
 jQuery('#H').val(Math.round(c.h));
}


	function checkCoords() {
		if (parseInt($('#W').val()))
			return true;
		alert('Please select a crop region then press submit.');
		return false;
	};


</script>
	
<form:form method="post" action="/fashbook/person/${sessionScope['scopedTarget.userSessionData'].personId}/outfit/saveimage">
	<spring:url value="http://localhost:8080/resources/fashbook/images/person/{personId}/{personId}_temp.jpg" var="imageUrl">
		<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
	</spring:url>
	<%-- <img id = "actualImage" class="ui-widget-content" src="${fn:escapeXml(imageUrl)}" /> --%>
	<img id = "actualImage" src="${fn:escapeXml(imageUrl)}" /> 
	<p>
			<input type="submit" value="Crop Image" />
	</p>
      <input type="hidden" name="X1" id="X1" />
      <input type="hidden" name="Y1" id="Y1"/>
      <input type="hidden" name="X2" id="X2"/>
      <input type="hidden" name="Y2" id="Y2"/>
      <input type="hidden" name="W" id="W"/>
      <input type="hidden" name="H" id="H"/>
</form:form>
