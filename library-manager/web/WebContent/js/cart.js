function setCookie(c_name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
	document.cookie = c_name + "=" + c_value;
}

function getCookie(c_name) {
	var i, x, y, ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++) {
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g, "");
		if (x == c_name) {
			return unescape(y);
		}
	}
	return "";
}

function add_to_cart_success(cart_id) {
	setCookie("lm_cart_id", cart_id, 100);
	window.alert("已添加到借书篮!");
}

function add_to_cart(book_oid) {
	cart_id = getCookie("lm_cart_id");
	cart_manager.addToCart(cart_id, book_oid, add_to_cart_success);
}

function add_to_fav_success(success) {
	if (success)
		window.alert("已添加到收藏夹!");
	else
		window.alert("添加到收藏夹失败，请先登录!");
}

function add_to_fav(book_oid) {
	fav_manager.addToFav(book_oid, add_to_fav_success);
}