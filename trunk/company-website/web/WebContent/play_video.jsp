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
<object id="PK1_Flash" type="application/x-shockwave-flash"
					data="/common/player_flv_mini.swf" width="480" height="360">
					<noscript><a href="http://www.dvdvideosoft.com">free
					software</a></noscript>
					<param name="movie" value="/common/player_flv_mini.swf" />
					<param name="wmode" value="opaque" />
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="quality" value="high" />
					<param name="menu" value="true" />
					<param name="autoplay" value="false" />
					<param name="autoload" value="false" />
					<param name="FlashVars"
						value="flv=/video/<%=request.getParameter("video") %>&amp;width=480&amp;height=360&amp;autoplay=0&amp;autoload=0&amp;buffer=5&amp;playercolor=464646
&amp;loadingcolor=c9c9c9&amp;buttoncolor=ffffff&amp;slidercolor=ffffff;" />
				</object>
</div>
</body>
</html>