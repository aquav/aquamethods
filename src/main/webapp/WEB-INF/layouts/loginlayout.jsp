<%@ include file="/WEB-INF/pages/includes.jsp"%>


<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>
		<tiles:insertAttribute name="title" />
	</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/styles/fashbook.css" />" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
</HEAD>
<body>

<div id="container">
		<div id="header">
			<h1>Fashbook</h1>
			Your virtual wardrobe!!
		</div>
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
		
		<div id="footer">
			Copyright (c) 2010 AquaMethods| About
		</div>
	</div>

</body>
</html>
