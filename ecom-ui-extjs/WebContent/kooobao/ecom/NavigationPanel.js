Ext.define('Kooobao.ecom.NavigationMenu', {
	xtype : 'ecom-navmenu',
	extend : 'Ext.panel.Panel',
	layout : {
		type : 'vbox',
		align : 'left'
	}
});

Ext.define('Kooobao.ecom.NavigationButton', {
	extend : 'Ext.Button',
	xtype : 'ecom-navbutton',
	textAlign : 'left',
	listeners : {
		click : function() {
			var tabClass = this.tabClass;
			if (undefined !== tabClass) {
				var newTab = Ext.create(tabClass);
				if (undefined !== newTab) {
					var mainTabPanel = Ext.getCmp('main_tabpanel');
					mainTabPanel.add(newTab);
					// Set it as the active one
					mainTabPanel.setActiveTab(mainTabPanel.items.length - 1);
				} else {
					alert('Failed to create tab from ' + tabClass);
				}
			} else {
				alert('Undefined Tab Class');
			}
		}
	}
});

Ext.define('Kooobao.ecom.NavigationPanel', {
	id : 'nav_panel',
	extend : 'Ext.panel.Panel',
	xtype : 'ecom-navpanel',
	title : 'Navigation',
	width : 300,
	layout : {
		// layout-specific configs go here
		type : 'accordion',
		titleCollapse : true,
		animate : true,
		activeOnTop : true
	},
	items : [ {
		title : 'User & Authority',
		xtype : 'ecom-navmenu',
		items : [ {
			xtype : 'ecom-navbutton',
			text : 'Manage User',
			width : '100%',
			tabClass : 'Kooobao.ecom.user.ManageUserPanel'
		}, {
			xtype : 'ecom-navbutton',
			text : 'Manage Role',
			width : '100%',
			tabClass : 'Kooobao.ecom.user.ManageRolePanel'
		}, {
			xtype : 'ecom-navbutton',
			text : 'View Authority',
			width : '100%',
			tabClass : 'Kooobao.ecom.user.ViewAuthorityPanel'
		} ]
	}, {
		title : 'Product',
		xtype : 'ecom-navmenu',
		items : [ {
			xtype : 'ecom-navbutton',
			text : 'Manage Product',
			width : '100%'
		}, {
			xtype : 'ecom-navbutton',
			text : 'Manage Product Set',
			width : '100%'
		} ]
	}, {
		title : 'CRM',
		xtype : 'ecom-navmenu',
		items : [ {
			xtype : 'ecom-navbutton',
			text : 'Manage User',
			width : '100%'
		}, {
			xtype : 'ecom-navbutton',
			text : 'Manage Role',
			width : '100%'
		}, {
			xtype : 'ecom-navbutton',
			text : 'View Authority',
			width : '100%'
		} ]
	} ]
});