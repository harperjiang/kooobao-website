function category_switch(category_oid) {
	var obj = document.getElementById('category_content_'+category_oid);
	if(obj.style.display != 'none')
		obj.style.display = 'none';
	else 
		obj.style.display = 'block';
}