Ext.define('Kooobao.ecom.LoginForm', {
	extend : 'Ext.panel.Panel',
	title : 'Kooobao Ecom Login',
	xtype : 'ecom-loginform',
	bodyPadding : 5,
	width : 350,

	listeners : {
		afterrender : function() {
			var useridText = Ext.getCmp('login_userid');
			this.controller.addBinding(Ext.create('Kooobao.mvc.TextBinding',
					useridText, 'userid'));
			var passwordText = Ext.getCmp('login_password');
			this.controller.addBinding(Ext.create('Kooobao.mvc.TextBinding',
					passwordText, 'password'));
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
		name : 'password',
		id : 'login_password'
	} ],

	// Reset and Submit buttons
	buttons : [
			{
				text : 'Reset',
				handler : function() {

				}
			},
			{
				text : 'Submit',
				handler : function() {
					var model = this.up('panel').controller.model;
					authenticateService.login('ecom', model.get('userid'),
							model.get('password'),
							this.up('panel').callbacks.onLoginDone);
				}
			} ],
	renderTo : Ext.getBody(),
	callbacks : {
		onLoginDone : function(token) {
			if (null == token) {
				// Display the error message
				Ext.MessageBox.alert('Login', 'Login Failed.');
			} else {
				// Save Token in LocalStorage
				Kooobao.state.LocalState.set('user_token', token);
				Ext.destroy(Kooobao.currentViewPort);
				Kooobao.currentViewPort = Ext.create('Kooobao.ecom.MainView');
			}
		}
	}
});