package com.kooobao.ecom.ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Hyperlink;

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
		rootPanel.setStyleName("menuItem");

		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		rootPanel.add(splitLayoutPanel, 10, 10);
		splitLayoutPanel.setSize("430px", "280px");

		rootPanel.add(splitLayoutPanel);

		StackPanel menuStackPanel = new StackPanel();
		splitLayoutPanel.addWest(menuStackPanel, 200);
		menuStackPanel.setSize("100%", "800");
		
		VerticalPanel productPanel = new VerticalPanel();
		menuStackPanel.add(productPanel, "Product", false);
		productPanel.setWidth("100%");
		
		Hyperlink manageProductLink = new Hyperlink("Manage Product", false, "newHistoryToken");
		manageProductLink.setStyleName("menuItem");
		productPanel.add(manageProductLink);
		
		Hyperlink manageProductSetLink = new Hyperlink("Manage Product Set", false, "newHistoryToken");
		manageProductSetLink.setStyleName("menuItem");
		productPanel.add(manageProductSetLink);
		
		VerticalPanel purchasePanel = new VerticalPanel();
		menuStackPanel.add(purchasePanel, "Purchase", false);
		purchasePanel.setSize("100%", "");
		
		Hyperlink searchPurchaseLink = new Hyperlink("Search Purchase", false, "newHistoryToken");
		searchPurchaseLink.setStyleName("menuItem");
		purchasePanel.add(searchPurchaseLink);
		
		Hyperlink createPurchaseLink = new Hyperlink("Import Purchase", false, "newHistoryToken");
		createPurchaseLink.setHTML("Create Purchase");
		createPurchaseLink.setStyleName("menuItem");
		purchasePanel.add(createPurchaseLink);
		
		VerticalPanel deliveryPanel = new VerticalPanel();
		menuStackPanel.add(deliveryPanel, "Delivery", false);
		deliveryPanel.setSize("100%", "100%");
		
		Hyperlink searchDeliveryLink = new Hyperlink("New hyperlink", false, "newHistoryToken");
		searchDeliveryLink.setHTML("Search Delivery");
		searchDeliveryLink.setStyleName("menuItem");
		deliveryPanel.add(searchDeliveryLink);
		
		Hyperlink recordDeliveryLink = new Hyperlink("Record Delivery", false, "newHistoryToken");
		recordDeliveryLink.setStyleName("menuItem");
		deliveryPanel.add(recordDeliveryLink);
		
		VerticalPanel stockPanel = new VerticalPanel();
		menuStackPanel.add(stockPanel, "Stock", false);
		stockPanel.setSize("100%", "100%");
		
		VerticalPanel reportPanel = new VerticalPanel();
		menuStackPanel.add(reportPanel, "Report", false);
		reportPanel.setSize("100%", "100%");
		
		VerticalPanel userPanel = new VerticalPanel();
		menuStackPanel.add(userPanel, "User", false);
		userPanel.setSize("100%", "100%");
		
		VerticalPanel customerPanel = new VerticalPanel();
		menuStackPanel.add(customerPanel, "Customer", false);
		customerPanel.setSize("100%", "100%");
		
		VerticalPanel settingPanel = new VerticalPanel();
		menuStackPanel.add(settingPanel, "Setting", false);
		settingPanel.setSize("100%", "100%");
		
		VerticalPanel dataPanel = new VerticalPanel();
		menuStackPanel.add(dataPanel, "Data", false);
		dataPanel.setSize("100%", "100%");
	}
}
