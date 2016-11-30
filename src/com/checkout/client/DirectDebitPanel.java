package com.checkout.client;

import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

public class DirectDebitPanel extends Viewport {
	

		public DirectDebitPanel(HorizontalPanel panelButton) {
			
			FramedPanel panel = new FramedPanel();
			panel.setHeading("Direct Debit Details: ");
			
			add(panel);
						
			TextField kontoinhaber = new TextField();
			TextField kontonummer = new TextField();
			TextField bankleitzahl = new TextField();
			TextField iBAN = new TextField();
			TextField bIC = new TextField();
		
		
			
			CssFloatLayoutContainer columns = new CssFloatLayoutContainer();
			columns.add(new FieldLabel(kontoinhaber,"Firmenname"),new CssFloatData(1.0,new Margins(0,0,0,0)));
			columns.add(new FieldLabel(kontonummer,"Vorname"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(bankleitzahl,"Nachname"),new CssFloatData(0.5,new Margins(0,1,0,5)));
			columns.add(new FieldLabel(iBAN,"Strabe"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(bIC,"Hausnr"),new CssFloatData(0.5,new Margins(0,1,0,5)));
			columns.add(panelButton,new CssFloatData(1.0,new Margins(0,1,0,5)));		
			VerticalLayoutContainer container = new VerticalLayoutContainer();
			container.add(columns, new VerticalLayoutData(1,-1,new Margins(15,15,0,5)));
			panel.add(container);
//			panel.setResize(true);
//			container.setHeight("300px");
			List<FieldLabel> fieldLabels = FormPanelHelper.getFieldLabels(this);
			for(FieldLabel fieldlabel : fieldLabels){
					fieldlabel.setLabelAlign(LabelAlign.TOP);
				
			}
		
			
	}



	
}
