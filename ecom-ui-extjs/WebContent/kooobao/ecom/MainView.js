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
		activeTab : 0,
		items : {
			title : 'Default Tab',
			bodyPadding : 10,
			html : 'The first tab\'s content. Others may be added dynamically'
		}
	} ]
});