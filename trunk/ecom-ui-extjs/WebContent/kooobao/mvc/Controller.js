Ext.define('Kooobao.mvc.Binding', {
	listeners : new Array(),
	component : undefined,
	attribute : undefined,
	value : undefined,
	constructor : function(comp, attr) {
		this.setComponent(comp);
		this.attribute = attr;
	},
	setComponent : function(comp) {
		this.component = comp;
	},
	onChange : function(comp, newval, oldval) {
		this.value = newval;
		for ( var i = 0; i < this.listeners.length; i++) {
			this.listeners[i]
					.valueChanged(this, this.attribute, oldval, newval);
		}
	},
	setValue : function(newval) {
		this.value = newval;
		// Change the component value without listening to it
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
		// Add Text Listener
		comp.on('change', this.onChange, this);
		this.superclass.setComponent(comp);
	},
	setValue : function(newval) {
		this.superclass.setValue(newval);
		// Change the component value without listening to it
		this.component.removeListener('change', this.onChange);
		this.component.setValue(newval);
		this.component.on('change', this.onChange, this);
	}
});

Ext.define('Kooobao.mvc.Controller', {
	bindings : {},
	model : Ext.create('Kooobao.mvc.Model', {
		listeners : [ this ]
	}),
	addBinding : function(binding) {
		binding.addListener(this);
		this.bindings[binding.attribute] = binding;
	},
	add : function(component, attribute) {
		if (component instanceof Ext.form.field.Text)
			this.addBinding(Ext.create('Kooobao.mvc.TextBinding', component,
					attribute));
		// TODO add more
	},
	valueChanged : function(source, attribute, oldval, newval) {
		if (source instanceof Kooobao.mvc.Binding) {
			// Set Model Value
			this.model.removeListener(this);
			this.model.set(attribute, newval);
			this.model.addListener(this);
		} else if (source instanceof Kooobao.mvc.Model) {
			// Set Component Value
			var binding = this.bindings[attribute];
			if (binding !== undefined) {
				binding.removeListener(this);
				binding.setValue(newval);
				binding.addListener(this);
			}
		}
	}
});
