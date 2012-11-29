<%@ include file="/WEB-INF/pages/includes.jsp"%>


<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><tiles:insertAttribute name="title" /></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/styles/fashbook.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/styles/jcrop/jquery.Jcrop.css" />" />

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>

</HEAD>
<body>
	<c:url value="/logout" var="logoutUrl" />
	<div id="layoutheader">
		<div id="headertitle">
			<h1>Fashbook</h1>
			Your virtual wardrobe!!
		</div>
		<div id="headernav">
			${sessionScope['scopedTarget.userSessionData'].personFirstName}
			${sessionScope['scopedTarget.userSessionData'].personLastName} <a
				href="${logoutUrl}"> Logout </a>
		</div>
	</div>

	<div id="layoutleftnav">
		<tiles:insertAttribute name="leftmenu" />
	</div>

	<div id="layoutcenter">
		<tiles:insertAttribute name="content" />
	</div>
	<div id="layoutrightnav">
		<tiles:insertAttribute name="rightmenu" />
	</div>
	<div id="layoutfooter">
		<HR />
		Copyright (c) 2010 AquaMethods| About
	</div>

</body>
</html>
