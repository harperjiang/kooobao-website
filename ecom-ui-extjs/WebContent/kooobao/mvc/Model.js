Ext.define('Kooobao.mvc.Model', {
	data : {},
	listeners : [],
	addListener : function(listener) {
		this.listeners.push(listener);
	},
	removeListener : function(listener) {
		var index = this.listeners.indexOf(listener);
		if (index != -1)
			this.listeners.splice(index, 1);
	},
	set : function(attr, newval) {
		if (this.data[attr] !== newval) {
			var old = this.data[attr];
			this.data[attr] = newval;
			for ( var i = 0; i < this.listeners.length; i++) {
				this.listeners.valueChanged(this, attr, old, newval);
			}
		}
	},
	get : function(attr) {
		return this.data[attr];
	}
});