Ext.define('Kooobao.layout.Center', {
	alias : 'layout.kooobao.center',
	extend : 'Ext.ux.layout.Center',
	setItemHeight : function(itemContext, info) {
		var height = this.getSize(itemContext.target, info, 'height');
		itemContext.setHeight(height);
	},
});