function check_input_len(id, maxLength) {
	obj = document.findElementById(id);
	if (obj.value.length > maxlength) {
		obj.value = obj.value.substring(0, maxlength);
	}
}