Ext.define('Kooobao.ecom.TitlePanel', {
	extend : 'Ext.panel.Panel',
	xtype : 'ecom-titlepanel',
	height : 100,
	items : [ {
		id : 'userinfo_panel',
		align : "right",
		width : 100,
		height : 30,
		html : 'Please login',
		style : {
			float : "right"
		},
		listeners : {
			afterrender : function() {
				var token = Kooobao.state.LocalState.get('user_token');
				if (token !== undefined) {
					Ext.getCmp('userinfo_panel').update(token.userId);
				}
			}
		}
	} ]
});