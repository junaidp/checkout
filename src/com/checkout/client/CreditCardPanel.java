package com.checkout.client;

import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class CreditCardPanel extends Viewport {

	public CreditCardPanel(HorizontalPanel panelButton){

		FramedPanel panel = new FramedPanel();
		panel.setHeading("Credit Card Details: ");
		add(panel);
		TextField karteninhaber = new TextField();
		TextField kartennummer = new TextField();
		TextField ccv = new TextField();

		SimpleComboBox<String> monthcombo = new SimpleComboBox<String>(new StringLabelProvider<String>());
		for(int i=1;i<=12;i++){
			if(i<10)
				monthcombo.add("0"+i);
			else
				monthcombo.add(""+i);
		}
		monthcombo.setEmptyText("MM");

		SimpleComboBox<String> yearcombo = new SimpleComboBox<String>(new StringLabelProvider<String>());
		yearcombo.add("2014");
		yearcombo.add("2015");
		yearcombo.add("2016");
		yearcombo.add("2017");
		yearcombo.setEmptyText("JJJJ");
		CssFloatLayoutContainer coloumns = new CssFloatLayoutContainer();
		//				coloumns.add(hpnlImages,new CssFloatData(0.3,new Margins(0,10,0,0)));
		coloumns.add(new FieldLabel(karteninhaber,"Karteninhaber"),new CssFloatData(1.0,new Margins(0,0,0,0)));
		coloumns.add(new FieldLabel(kartennummer,"Kartennummer"),new CssFloatData(0.6,new Margins(0,0,0,0)));
		coloumns.add(new FieldLabel(ccv,"CVC"),new CssFloatData(0.4,new Margins(0,1,0,20)));
		coloumns.add(new Label("gultig bis"),new CssFloatData(0.2,new Margins(4,1,0,30)));
		coloumns.add(monthcombo,new CssFloatData(0.4,new Margins(10,1,0,20)));
		coloumns.add(yearcombo,new CssFloatData(0.4,new Margins(10,1,0,20)));
		coloumns.add(panelButton,new CssFloatData(1.0,new Margins(20,1,0,5)));		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		container.add(coloumns, new VerticalLayoutData(1,-1,new Margins(15,15,0,5)));
		//				panel.addButton(Weiter);
		panel.add(container);


		List<FieldLabel> fieldLabels = FormPanelHelper.getFieldLabels(this);
		for(FieldLabel fieldlabel : fieldLabels){
			if(!fieldlabel.equals("Xulting bis")){
				fieldlabel.setLabelAlign(LabelAlign.TOP);
			}
		}
	}

}
