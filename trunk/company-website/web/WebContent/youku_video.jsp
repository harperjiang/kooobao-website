<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Player</title>
</head>
<body>
	<div>
		<embed
			src=" http://static.youku.com/v1.0.0149/v/swf/qplayer_rtmp.swf?VideoIDS=<%=request.getParameter("video") %>&winType=adshow&isAutoPlay=true"
			quality="high" width="480" height="420" align="middle"
			allowScriptAccess="sameDomain" type="application/x-shockwave-flash"></embed>
	</div>
</body>
</html>