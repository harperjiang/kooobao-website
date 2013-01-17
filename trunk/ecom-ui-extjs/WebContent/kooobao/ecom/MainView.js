Ext.define('Kooobao.ecom.MainView', {
	layout : 'border',
	extend : 'Ext.container.Viewport',
	items : [ {
		region : 'north',
		xtype : 'ecom-titlepanel'
	}, {
		region : 'west',
		xtype : 'ecom-navpanel'
	}, {
		region : 'south',
		title : 'South Panel',
		collapsible : true,
		html : 'Information goes here',
		split : true,
		height : 100,
		minHeight : 100
	}, {
		region : 'center',
		xtype : 'tabpanel',
		id : 'main_tabpanel',
		activeTab : 0
	} ]
});