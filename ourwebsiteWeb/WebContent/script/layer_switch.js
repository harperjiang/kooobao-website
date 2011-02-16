function flash_object(id, width, height, movie) {
	this.id = id;
	this.width = width;
	this.height = height;
	this.movie = movie;

	this.write = function(elem) {
		var htmlText = "<object id=\"PK_Flash\" type=\"application/x-shockwave-flash\""
				+ "data=\"/common/player_flv_mini.swf\" width=\""
				+ width
				+ "\" height=\""
				+ height
				+ "\">"
				+ "<noscript><a href=\"http://www.dvdvideosoft.com\">free"
				+ "software</a></noscript>"
				+ "<param name=\"movie\" value=\"/common/player_flv_mini.swf\" />"
				+ "<param name=\"wmode\" value=\"opaque\" />"
				+ "<param name=\"allowScriptAccess\" value=\"sameDomain\" />"
				+ "<param name=\"quality\" value=\"high\" />"
				+ "<param name=\"menu\" value=\"true\" />"
				+ "<param name=\"autoplay\" value=\"false\" />"
				+ "<param name=\"autoload\" value=\"false\" />"
				+ "<param name=\"FlashVars\""
				+ "value=\"flv="
				+ movie
				+ "&amp;width="
				+ width
				+ "&amp;height="
				+ height
				+ "&amp;autoplay=0&amp;autoload=0&amp;buffer=5&amp;playercolor=464646"
				+ "&amp;loadingcolor=c9c9c9&amp;buttoncolor=ffffff&amp;slidercolor=ffffff;\" />"
				+ "</object>";
		document.getElementById(elem).innerHTML = htmlText;
	}
}

void function switchVideo(a) {
	for (i = 1; i <= 6; i++) {
		document.getElementById("pk_td_" + i).style.fontWeight = "normal";
	}
	document.getElementById("pk_td_" + a).style.fontWeight = "bold";

	new flash_object('PK_Flash', 480, 360, '/video/PK' + a + 'A.flv')
			.write('pk_video');
}