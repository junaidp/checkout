package com.checkout.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CheckOutPage implements EntryPoint {
	
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	
	public void onModuleLoad() {

		RootPanel.get().add(new TabView());
	}
}
