Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'Kooobao' : 'kooobao'
	}
});

Ext.require([ 'Kooobao.mvc.Controller', 'Kooobao.state.UserState',
		'Kooobao.ecom.LoginForm', 'Kooobao.ecom.LoginView',
		'Kooobao.ecom.MainView', 'Kooobao.ecom.NavigationPanel',
		'Kooobao.ecom.TitlePanel', 'Kooobao.layout.Center' ]);

Ext.require([ 'Kooobao.ecom.user.ManageRolePanel',
		'Kooobao.ecom.user.ManageUserPanel',
		'Kooobao.ecom.user.ViewAuthorityPanel' ]);

Ext.require([ 'Ext.window.MessageBox' ]);

Ext.namespace('Kooobao');
Ext.application({
	name : 'Kooobao Ecom System',
	launch : function() {
		// var form = Ext.create('Kooobao.ecom.LoginForm');
		// form.getEl().center();
		// form.show();
		Kooobao.currentViewPort = Ext.create('Kooobao.ecom.LoginView');
	}
});