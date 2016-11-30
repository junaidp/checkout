package com.checkout.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class PaymentView extends Viewport{

	private Image imgDirectDebit = new Image();
	private Image imgPayPal = new Image();
	private Image imgCreditCard = new Image();
	private HorizontalPanel hpnlImages = new HorizontalPanel();
	private TextButton submit = new TextButton("Submit");
	private String selectedPaymentMode= "Credit Card";
	private CheckBox chkTerms = new CheckBox();

	public PaymentView(){

		setImages();
		Anchor ancTerms = new Anchor("Geschaftsbedingungen");
		ancTerms.setStyleName("anchorStyle");
		FlexTable  panelTerms = new FlexTable();
		panelTerms.setWidget(0, 0, chkTerms);
		panelTerms.setWidget(0, 1, ancTerms);
		chkTerms.setBoxLabel("Ich akzeptiere das");
		final FramedPanel panel= new FramedPanel();
		VBoxLayoutContainer vContainer = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
		VBoxLayoutContainer paymentContainer = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
		add(panel);
		panel.setHeading("Ihre Zahlungsdaten");
		vContainer.add(hpnlImages);
		vContainer.add(paymentContainer);
		panel.add(vContainer);
		HorizontalPanel buttonPanel = new HorizontalPanel();
		selectPaymentMethod(paymentContainer, buttonPanel);
		buttonPanel.setWidth("100%");
		buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		buttonPanel.add(panelTerms);
		buttonPanel.add(submit);
		paymentContainer.add(new CreditCardPanel(buttonPanel));
		submit.setText("Jetzt Zahlungsflitchtig Bestellen");
		submit.setWidth(200);
		submit.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if(chkTerms.getValue()){
				processPayment();
				}else{
					AlertMessageBox  alert = new AlertMessageBox("Payment Failed", "Please Accept Terms & Conditions");
					alert.show();
				}
			}
		});
		
		ancTerms.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.open("AGB.pdf", "_blank", null);
			}
		});
		
	}

	private void selectPaymentMethod(final VBoxLayoutContainer paymentContainer, final HorizontalPanel hpnlButton) {
		imgDirectDebit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				paymentContainer.clear();
				paymentContainer.add(new DirectDebitPanel(hpnlButton));
				selectedPaymentMode = "Direct Debit";
			}
		});

		imgCreditCard.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				paymentContainer.clear();
				paymentContainer.add(new CreditCardPanel(hpnlButton));
				selectedPaymentMode = "Credit Card";
			}
		});

		imgPayPal.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				paymentContainer.clear();
				paymentContainer.add(new PayPalPanel(hpnlButton));
				selectedPaymentMode = "PayPal";
			}
		});
	}

	private void setImages() {
		imgDirectDebit.setUrl("images/directDebit.png");
		imgPayPal.setUrl("images/PayPal.png");
		imgCreditCard.setUrl("images/Visa.png");

		imgDirectDebit.setHeight("70px");
		imgPayPal.setHeight("70px");
		imgCreditCard.setHeight("70px");

		imgDirectDebit.addStyleName("point");
		imgPayPal.addStyleName("point");
		imgCreditCard.addStyleName("point");
		hpnlImages.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		hpnlImages.add(imgCreditCard);
		hpnlImages.add(imgPayPal);
		hpnlImages.add(imgDirectDebit);
		hpnlImages.setSpacing(10);
	}

	private void processPayment(){
		final AutoProgressMessageBox messageBox = new AutoProgressMessageBox("Payment !");
		messageBox.setProgressText(selectedPaymentMode+" Payment Processing ...");
		messageBox.auto();
		messageBox.show();

		Timer timer = new Timer(){

			@Override
			public void run() {
				messageBox.hide();
			}

		};
		timer.schedule(4000);
	}

}
