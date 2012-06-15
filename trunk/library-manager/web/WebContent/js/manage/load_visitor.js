function load_visitor(visitor_id) {
	profile_service.getVisitor(visitor_id, load_visitor_done);
}

function load_visitor_done(visitor) {
	document.getElementById('mask').style.display = 'block';
	if (visitor.typeText == '学校用户') {
		show_inst_dialog(visitor.instInfo);
	} else {
		show_person_dialog(visitor.info);
	}
}

function show_person_dialog(person_info) {

}

function show_inst_dialog(inst_info) {
	var dlg = document.getElementById("iinfo_dlg");
	dlg.style.display = 'block';
	dlg.style.left = (document.body.clientWidth - 500) / 2 + "px";
	dlg.style.top = (document.body.clientHeight - 400) / 2 + "px";
	document.getElementById('iinfo_name').value = inst_info.name;
	document.getElementById('iinfo_contact').value = inst_info.contact;
	document.getElementById('iinfo_address').value = inst_info.address;
	document.getElementById('iinfo_phone').value = inst_info.phone;
	document.getElementById('iinfo_url').value = inst_info.url;
	document.getElementById('iinfo_desc').value = inst_info.description;
}

function hide_visitor_dialogs() {
	document.getElementById('mask').style.display = 'none';
	if (document.getElementById('pinfo_dlg') != null)
		document.getElementById('pinfo_dlg').style.display = 'none';
	if (document.getElementById('iinfo_dlg') != null)
		document.getElementById('iinfo_dlg').style.display = 'none';
}