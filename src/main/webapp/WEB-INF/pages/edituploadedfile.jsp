<%@ include file="/WEB-INF/pages/includes.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/static/styles/jcrop/jquery.Jcrop.css" />" />
<script src="/fashbook/static/js/jquery.Jcrop.js"></script>

<script type="text/javascript">
$(function () {
    $('#actualImage').Jcrop({
     setSelect: [0, 0, 300, 500],
     addClass: 'custom',
     bgColor: 'black',
     bgOpacity: .6,
     //sideHandles: true
     allowResize: false,
     allowSelect: false,
     onSelect: storeCoords
 });
});

function storeCoords(c) {

 jQuery('#X1').val(c.x);
 jQuery('#Y1').val(c.y); 
 jQuery('#X2').val(c.x2);
 jQuery('#Y2').val(c.y2); 
 jQuery('#W').val(c.w);
 jQuery('#H').val(c.h);
}


	function checkCoords() {
		if (parseInt($('#W').val()))
			return true;
		alert('Please select a crop region then press submit.');
		return false;
	};

	/* function cropPicture(){
	 $.ajax({
	 url: "cropPhoto.htm",
	 type: "POST", 
	 data :{
	 x : $('input#X1').val(),
	 y : $('input#Y1').val(),
	 x2 : $('input#X2').val(),
	 y2 : $('input#Y2').val(), 
	 w : $('input#W').val(),
	 h : $('input#H').val(),
	 imageName : $('input#imageName').val()
	 },
	 success: function (data) { 
	 window.location = 'photo.htm';
	 }
	 } */
</script>
	
<form:form method="post" action="/fashbook/person/${sessionScope['scopedTarget.userSessionData'].personId}/outfit/saveimage">
	<spring:url value="http://localhost:8080/resources/fashbook/images/person/{personId}/{personId}_temp.jpg" var="imageUrl">
		<spring:param name="personId" value="${sessionScope['scopedTarget.userSessionData'].personId}" />
	</spring:url>
	<img height="600" id = "actualImage" src="${fn:escapeXml(imageUrl)}" />
	
	<p>
			<input type="submit" value="Crop Image" />
	</p>
</form:form>
