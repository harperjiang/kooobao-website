function loadjs(jsurl) {
	var request = new XMLHttpRequest();
	request.open("GET", jsurl, false);
	request.send();
	if (request.status == 200) {
		eval(request.responseText);
	}
}
loadjs('dwr/engine.js');
loadjs('dwr/interface/authenticateService.js');