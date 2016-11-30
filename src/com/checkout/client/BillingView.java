package com.checkout.client;

import java.util.ArrayList;
import java.util.List;
import com.checkout.shared.Land;
import com.checkout.shared.User;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;


public class BillingView extends Viewport {
	
	private FramedPanel panel = new FramedPanel();
	private TextButton weiter = new TextButton();
	
	public BillingView() {
		
			add(panel);
			panel.setHeading("Ihre Rechnungsdaten");
			User user = new User("DummyFirst","Dummy Last","Dummy Company","dummy@hotmail.com","dummyxy","XYZ","XYZ","XYZ",1);
			
			TextField vorname = new TextField();
			TextField nachname = new TextField();
			TextField firmenname = new TextField();
			TextField emailAdresse = new TextField();
			TextField strabe = new TextField();
			TextField hausnr = new TextField();
			TextField pLZ = new TextField();
			TextField oRT = new TextField();
			
			populateFields(user, vorname, nachname, firmenname, emailAdresse, strabe, hausnr, pLZ, oRT);
			
			LabelProvider<Land> labelProvider = new LabelProvider<Land>() {
				  @Override
				  public String getLabel(Land land) {
				    if (land.getName() == null) {
				      return "";
				    }
				    return land.getName();
				  }
				};
				
			ArrayList<Land> lands = new ArrayList<Land>();
			Land land = new Land(0, "Deutschland");
			lands.add(land);
			lands.add(new Land(1, "Costa Rics"));
			ListStore<Land> LandStore = new ListStore<Land>(new ModelKeyProvider<Land>(){
				 @Override
				  public String getKey(Land item) {
					 String id =  String.valueOf(item.getId());
				     return id;
				  }
			});
			LandStore.addAll(lands);
			
			ComboBox<Land> landCombo = new ComboBox<Land>(LandStore, labelProvider);
			landCombo.setValue(land);
			landCombo.setEnabled(false);
			
			weiter.setText("Weiter");
			weiter.setWidth(100);
						
			CssFloatLayoutContainer columns = new CssFloatLayoutContainer();
			columns.add(new FieldLabel(firmenname,"Firmenname"),new CssFloatData(1.0,new Margins(0,0,0,0)));
			columns.add(new FieldLabel(vorname,"Vorname"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(nachname,"Nachname"),new CssFloatData(0.5,new Margins(0,1,0,20)));
			columns.add(new FieldLabel(strabe,"Strabe"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(hausnr,"Hausnr"),new CssFloatData(0.5,new Margins(0,1,0,20)));
			columns.add(new FieldLabel(pLZ,"PLZ"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(oRT,"Ort"),new CssFloatData(0.5,new Margins(0,1,0,20)));
			columns.add(new FieldLabel(landCombo,"Land"),new CssFloatData(0.5,new Margins(0,1,0,0)));
			columns.add(new FieldLabel(emailAdresse,"Email-Adresse"),new CssFloatData(0.5,new Margins(0,1,0,20)));
			HorizontalPanel hpnl = new HorizontalPanel();
			hpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			hpnl.add(weiter);
			columns.add(hpnl,new CssFloatData(1.0,new Margins(0,0,0,0)));
			
			VerticalLayoutContainer container = new VerticalLayoutContainer();
			container.add(columns, new VerticalLayoutData(1,-1,new Margins(15,15,0,5)));
			panel.add(container);
			
			List<FieldLabel> fieldLabels = FormPanelHelper.getFieldLabels(panel);
			for(FieldLabel fieldlabel : fieldLabels){
				fieldlabel.setLabelAlign(LabelAlign.TOP);
			}
	}

	private void populateFields(User user, TextField vorname, TextField nachname, TextField firmenname,
			TextField emailAdresse, TextField strabe, TextField hausnr, TextField pLZ, TextField ORT) {
		vorname.setValue(user.getVorname());
		nachname.setValue(user.getNachname());
		firmenname.setValue(user.getFirmenname());
		emailAdresse.setValue(user.getEmailAdresse());
		strabe.setValue(user.getStrabe());
		hausnr.setValue(user.getHausnr());
		pLZ.setValue(user.getPLZ());
		ORT.setValue(user.getORT());
		firmenname.setEnabled(false);
		emailAdresse.setEnabled(false);
	}

	public TextButton getWeiter() {
		return weiter;
	}
}
