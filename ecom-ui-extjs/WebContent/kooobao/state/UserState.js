Ext.define('Kooobao.state.UserState', {
	getProvider : function() {
		if (this.provider === undefined)
			if (Ext.supports.LocalStorage === true) {
				this.provider = Ext.create('Ext.state.LocalStorageProvider');
			} else {
				this.provider = Ext.create('Ext.state.CookieProvider');
			}
		return this.provider;
	},
	get : function(name) {
		return this.getProvider().get(name);
	},
	set : function(name, val) {
		this.getProvider().set(name, val);
	}
});

Ext.namespace('Kooobao.state');

Kooobao.state.LocalState = Ext.create('Kooobao.state.UserState');