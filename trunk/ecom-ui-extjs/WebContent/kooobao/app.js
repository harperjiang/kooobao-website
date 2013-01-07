Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'Kooobao' : 'kooobao'
	}
});

Ext.require([ 'Kooobao.mvc.Controller', 'Kooobao.ecom.LoginView',
		'Kooobao.ecom.LoginForm' ]);
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