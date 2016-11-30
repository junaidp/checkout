package com.checkout.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

public class PayPalPanel extends Viewport {

	public PayPalPanel(HorizontalPanel panelButton){

		FramedPanel panel = new FramedPanel();
		panel.setHeading("PayPal Details: ");
		add(panel);
		TextField paypaleEmail = new TextField();
		CssFloatLayoutContainer columns = new CssFloatLayoutContainer();
		FieldLabel lblEmail = new FieldLabel(paypaleEmail);
		lblEmail.setText("PayPal Email-Adresse");
		lblEmail.setLabelAlign(LabelAlign.TOP);
		columns.add(lblEmail,new CssFloatData(1.0,new Margins(0,0,0,0)));
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		columns.add(panelButton,new CssFloatData(1.0,new Margins(0,1,0,5)));		
		container.add(columns, new VerticalLayoutData(1,-1,new Margins(15,15,0,5)));
		panel.add(container);

	}
}
