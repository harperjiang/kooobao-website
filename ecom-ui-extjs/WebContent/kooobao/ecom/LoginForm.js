Ext.define('Kooobao.ecom.LoginForm', {
	extend : 'Ext.panel.Panel',
	title : 'Kooobao Ecom Login',
	xtype : 'ecom-loginform',
	bodyPadding : 5,
	width : 350,

	listeners : {
		afterrender : function() {
			var a = Ext.getCmp('login_userid');
			this.controller.addBinding(Ext.create('Kooobao.mvc.TextBinding', a,
					'userid'));
		}
	},

	// Fields will be arranged vertically, stretched to full width
	layout : 'form',
	defaults : {
		anchor : '100%'
	},
	// Controller
	controller : Ext.create('Kooobao.mvc.Controller', {}),
	// The fields
	defaultType : 'textfield',
	items : [ {
		fieldLabel : 'User ID',
		name : 'userid',
		id : 'login_userid'
	}, {
		fieldLabel : 'Password',
		inputType : 'password',
		name : 'password'
	} ],

	// Reset and Submit buttons
	buttons : [ {
		text : 'Reset',
		handler : function() {

		}
	}, {
		text : 'Submit',
		handler : function() {
			// Ext.destroy(Kooobao.currentViewPort);
			// Kooobao.currentViewPort = Ext.create('Kooobao.ecom.MainView');
		}
	} ],
	renderTo : Ext.getBody()
});