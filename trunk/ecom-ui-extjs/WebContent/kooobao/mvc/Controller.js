Ext.define('Kooobao.mvc.Binding', {
	listeners : new Array(),
	component : undefined,
	attribute : undefined,
	constructor : function(comp, attr) {
		this.setComponent(comp);
		this.attribute = attr;
	},
	setComponent : function(comp) {
		this.component = comp;
	},
	onChange : function(comp, oldval, newval) {
		for ( var i = 0; i < this.listeners.length(); i++) {
			this.listeners[i].valueChanged(this.attribute, oldval, newval);
		}
	},
	addListener : function(listener) {
		this.listeners.push(listener);
	},
	removeListener : function(listener) {
		var index = this.listeners.indexOf(listener);
		if (index != -1) {
			this.listeners.splice(index, 1);
		}
	}
});

Ext.define('Kooobao.mvc.TextBinding', {
	extend : 'Kooobao.mvc.Binding',
	setComponent : function(comp) {
		debugger;
		// Add Text Listener
		comp.on('change', this.onChange, this);
		this.superclass.setComponent(this, comp);
	}
});

Ext.define('Kooobao.mvc.Controller', {
	bindings : new Array(),
	addBinding : function(binding) {
		debugger;
		binding.addListener(this);
		this.bindings.push(binding);
	},
	add : function(component, attribute) {
		// TODO Not implemented
	},
	valueChanged : function(attribute, oldval, newval) {
		alert(this);
		// TODO Set Model Value
	}
});

Ext.define('Kooobao.mvc.Model', {

});