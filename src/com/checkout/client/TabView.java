package com.checkout.client;

import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class TabView extends Composite{

	private PlainTabPanel tab = new PlainTabPanel();
	
	public TabView(){
		final BasketView ordersHeader = new BasketView();
		final BillingView userDetailsPage = new BillingView();
		final PaymentView paymentCardView = new PaymentView();
		
		tab.add(ordersHeader   , "Basket");
		tab.add(userDetailsPage, "Billing Address");
		tab.add(paymentCardView, "Payment");
		
		ordersHeader.getWeiter().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				tab.setActiveWidget(userDetailsPage);
			}
		});
		
		userDetailsPage.getWeiter().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				tab.setActiveWidget(paymentCardView);

			}
		});

		initWidget(tab);
	}

}
