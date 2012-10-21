package com.kooobao.ecom.ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kooobao.ecom.ui.client.nav.NavigationHandler;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ecom_ui implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get("mainContainer");
		rootPanel.setStyleName("rootPanel");

		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);
		mainPanel.setStyleName("gwt-DockLayoutPanel");
		rootPanel.add(mainPanel, -1, -1);
		mainPanel.setSize("100%", "100%");

		rootPanel.add(mainPanel, -1, -1);

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setStyleName("pageSection");
		mainPanel.addNorth(topPanel, 120.0);
		topPanel.setWidth("");
		
		Image image = new Image("res/icon.jpg");
		topPanel.add(image);
		image.setSize("100px", "100px");
		
		HTML logoHtml = new HTML("CRM");
		logoHtml.setStyleName("logo");
		topPanel.add(logoHtml);

		StackPanel menuStackPanel = new StackPanel();
		menuStackPanel.setStyleName("gwt-StackPanel pageSection");
		mainPanel.addWest(menuStackPanel, 200);
		menuStackPanel.setSize("100%", "800");

		VerticalPanel purchasePanel = new VerticalPanel();
		menuStackPanel.add(purchasePanel, "Purchase", false);
		purchasePanel.setSize("100%", "");

		Anchor searchPurchaseLink = new Anchor("Search Purchase", false);
		searchPurchaseLink.addClickHandler(new NavigationHandler());
		searchPurchaseLink.setStyleName("menuItem");
		purchasePanel.add(searchPurchaseLink);

		Anchor createPurchaseLink = new Anchor("Import Purchase", false);
		createPurchaseLink.setText("Create Purchase");
		createPurchaseLink.setStyleName("menuItem");
		purchasePanel.add(createPurchaseLink);

		VerticalPanel deliveryPanel = new VerticalPanel();
		menuStackPanel.add(deliveryPanel, "Delivery", false);
		deliveryPanel.setSize("100%", "100%");

		Anchor searchDeliveryLink = new Anchor("New Anchor", false);
		searchDeliveryLink.setHTML("Search Delivery");
		searchDeliveryLink.setStyleName("menuItem");
		deliveryPanel.add(searchDeliveryLink);

		Anchor recordDeliveryLink = new Anchor("Record Delivery", false);
		recordDeliveryLink.setStyleName("menuItem");
		deliveryPanel.add(recordDeliveryLink);

		VerticalPanel inventoryPanel = new VerticalPanel();
		menuStackPanel.add(inventoryPanel, "Inventory", false);
		inventoryPanel.setSize("100%", "100%");

		Anchor manageSiteLink = new Anchor("Manage Site", false);
		manageSiteLink.setStyleName("menuItem");
		inventoryPanel.add(manageSiteLink);

		Anchor stocktakeLink = new Anchor("Stocktake", false);
		stocktakeLink.setHTML("Stocktake");
		stocktakeLink.setStyleName("menuItem");
		inventoryPanel.add(stocktakeLink);

		VerticalPanel reportPanel = new VerticalPanel();
		menuStackPanel.add(reportPanel, "Report", false);
		reportPanel.setSize("100%", "100%");

		VerticalPanel productPanel = new VerticalPanel();
		menuStackPanel.add(productPanel, "Product", false);
		productPanel.setWidth("100%");

		Anchor manageProductLink = new Anchor("Manage Product", false);
		manageProductLink.setStyleName("menuItem");
		productPanel.add(manageProductLink);

		Anchor manageProductSetLink = new Anchor("Manage Product Set", false);
		manageProductSetLink.setStyleName("menuItem");
		productPanel.add(manageProductSetLink);

		VerticalPanel userPanel = new VerticalPanel();
		menuStackPanel.add(userPanel, "User", false);
		userPanel.setSize("100%", "100%");

		Anchor manageUserLink = new Anchor("Manage User", false);
		manageUserLink.setStyleName("menuItem");
		userPanel.add(manageUserLink);

		Anchor manageRoleLink = new Anchor("Manage Role", false);
		manageRoleLink.setStyleName("menuItem");
		userPanel.add(manageRoleLink);

		Anchor viewAuthLink = new Anchor("View Authority", false);
		viewAuthLink.setStyleName("menuItem");
		userPanel.add(viewAuthLink);

		VerticalPanel customerPanel = new VerticalPanel();
		menuStackPanel.add(customerPanel, "Customer", false);
		customerPanel.setSize("100%", "100%");

		Anchor addHintLink = new Anchor("Add Hint", false);
		addHintLink.setStyleName("menuItem");
		customerPanel.add(addHintLink);
		addHintLink.setHeight("100%");

		Anchor followupLink = new Anchor("Followup", false);
		followupLink.setStyleName("menuItem");
		customerPanel.add(followupLink);
		followupLink.setHeight("100%");

		Anchor manageMyHintLink = new Anchor("Manage My Hint", false);
		manageMyHintLink.setStyleName("menuItem");
		customerPanel.add(manageMyHintLink);

		VerticalPanel settingPanel = new VerticalPanel();
		menuStackPanel.add(settingPanel, "Setting", false);
		settingPanel.setSize("100%", "100%");

		VerticalPanel dataPanel = new VerticalPanel();
		menuStackPanel.add(dataPanel, "Data", false);
		dataPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setStyleName("pageSection");
		mainPanel.add(verticalPanel);
		verticalPanel.setSize("100%", "100%");
	}
}
