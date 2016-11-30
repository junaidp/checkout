package com.checkout.client;

import java.util.ArrayList;

import com.checkout.shared.Order;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.DoubleSpinnerField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class BasketView extends VerticalLayoutContainer  {

	private Label lblSumNet = new Label();
	private Label lblSumGross = new Label();
	ArrayList<Order> orders = new ArrayList<Order>();
	private double taxPercent = 20;
	TextButton weiter = new TextButton();
	private TextField textCoupon = new TextField();

	public BasketView(){

		Label desc = new Label("Description");
		Label grossAmount = new Label("Brutto");
		Label quantity = new Label("Menge");
		Label netAmount = new Label("Netto");
		Label tax = new Label("Tax");

		desc.addStyleName("bold");
		grossAmount.addStyleName("bold");
		quantity.addStyleName("bold");
		netAmount.addStyleName("bold");
		tax.addStyleName("bold");
		lblSumNet.setStyleName("bold");
		lblSumGross.setStyleName("bold");

		FramedPanel panel = new FramedPanel();
		panel.setHeading("Your Order");

		add(panel);
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, desc);
		flex.setWidget(0, 1, grossAmount);
		flex.setWidget(0, 2, quantity);
		flex.setWidget(0, 3, netAmount);
		flex.setWidget(0, 4, tax);

		dummyData();

		HTMLTable.CellFormatter formatter = flex.getCellFormatter();
		formatter.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		int lastRow = 0;
		double sumGross = 0;
		double sumNet = 0;

		for(int i = 0; i<orders.size(); i++){
			final DoubleSpinnerField spinnerQuantity = createQuantitySpinner();
			final Order selectedOrder = orders.get(i);
			final Label lblNet = new Label("");
			final Label lblTax = new Label("");
			final Label lblGross = new Label("");
			spinnerQuantity.setValue((double) selectedOrder.getQuantity());

			updateValues(selectedOrder, lblNet, lblTax, lblGross, spinnerQuantity.getValue());

			flex.setWidget(i+1, 0, new Label(selectedOrder.getDescription()));
			flex.setWidget(i+1, 1, lblGross);
			flex.setWidget(i+1, 2, spinnerQuantity);
			flex.setWidget(i+1, 3, lblNet);
			flex.setWidget(i+1, 4, lblTax);
			setRowStyle(flex, formatter, i);
			
			sumGross = sumGross + selectedOrder.getGross();
			lblSumGross.setText(NumberFormat.getFormat(".00").format(sumGross));
			sumNet = sumNet + selectedOrder.getNetAmount();
			lblSumNet.setText(NumberFormat.getFormat(".00").format(sumNet));

			addValueChangeHandler(spinnerQuantity, selectedOrder, lblNet, lblTax, lblGross, sumGross);
			lastRow = i+1;
		}
		
		flex.setWidget(lastRow+1 , 1, lblSumGross);
		flex.setWidget(lastRow+1 , 3, lblSumNet);
		
		
		formatter.setHorizontalAlignment(lastRow+1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(lastRow+1, 3, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_CENTER);
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		VerticalLayoutData vd = new VerticalLayoutData(1,-1, new Margins(5));
		container.add(flex);
		HorizontalPanel hpnl = new HorizontalPanel();
		hpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hpnl.setWidth("100%");
		hpnl.add(new Label("Dieser Service verursacht wiederkehrende Kosten"));
		VBoxLayoutContainer vpnl = new VBoxLayoutContainer();
		vpnl.add(new Label("Haben Sie einen Coupon-Code?"));
		vpnl.add(textCoupon);
		hpnl.add(vpnl);
		textCoupon.setEmptyText("Ihr Coupon-Code");
		textCoupon.setWidth(130);
		hpnl.add(weiter);
		weiter.setText("Weiter");
		weiter.setWidth(100);
		container.add(hpnl);
		panel.add(container, vd);
		

	}

	private Order dummyData() {
		Order order = new Order();
		order.setDescription("This is first description: 360 Grad Finanzberatungssoftware fr die ganzheitliche Beratung "
				+ "in den Bereichen Vorsorge Gesundheit Sachversicherung"+
				"Levelnine ADVISE, Hauptlizenz, 1 Jahr Vertragslaufzeit, monatliche "
				+ "Zahlweise, automatische Verlngerung, falls nicht 3 Monate vor Ablauf gekndigt wurde ");
		order.setNetAmount(22.02);
		order.setQuantity(1);
		order.setGross(20.00);
		order.setItemPrice(12.32);

		Order order1 = new Order();
		order1.setDescription("This is first description: 360 Grad Finanzberatungssoftware fr die "
				+ "ganzheitliche Beratung in den Bereichen Vorsorge");
		order1.setNetAmount(12.02);
		order1.setQuantity(0);
		order1.setGross(0);
		order1.setItemPrice(100);

		Order order2 = new Order();
		order2.setDescription("This is first description: 360 Grad Finanzberatungssoftware fr die "
				+ "ganzheitliche Beratung in den Bereichen Vorsorge \n Gesundheit Sachversicherung"+
				"Levelnine ADVISE, Hauptlizenz, 1 Jahr Vertragslaufzeit, monatliche Zahlweise, "
				+ "automatische Verlngerung, falls nicht 3 Monate vor Ablauf gekndigt wurde ");
		order2.setNetAmount(42.02);
		order2.setQuantity(1);
		order2.setGross(50.00);
		order2.setTax(2.1);
		order2.setItemPrice(150.12);

		orders.add(order);
		orders.add(order1);
		orders.add(order2);
		return order;
	}

	private void addValueChangeHandler(final DoubleSpinnerField spinnerQuantity, final Order selectedOrder,
			final Label lblNet, final Label lblTax, final Label lblGross, double sumGross) {
		spinnerQuantity.addSelectionHandler(new SelectionHandler<Double>() {

			@Override
			public void onSelection(SelectionEvent<Double> event) {
				updateValues(selectedOrder, lblNet, lblTax, lblGross, event.getSelectedItem());
				updateSumValues();
			}


		});
		spinnerQuantity.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				updateValues(selectedOrder, lblNet, lblTax, lblGross, Double.parseDouble(spinnerQuantity.getText()));
				updateSumValues();
			}
		});
	}

	private void updateValues(final Order selectedOrder, final Label lblNet, final Label lblTax,
			final Label lblGross, Double quantity) {
		double net = quantity * selectedOrder.getItemPrice();
		double tax = taxPercent/100 * net; 
		double gross = net + tax;

		selectedOrder.setNetAmount(net);
		selectedOrder.setTax(tax);
		selectedOrder.setGross(gross);

		lblNet.setText(NumberFormat.getFormat(".00").format(net));
		lblTax.setText(NumberFormat.getFormat(".00").format(tax));
		lblGross.setText(NumberFormat.getFormat(".00").format(gross));
	}

	private void updateSumValues() {
		Double sumGross = (double) 0;
		Double sumNet = (double) 0;
		for(int i = 0; i<orders.size(); i++){
			Order selectedOrder = orders.get(i);
			sumGross = sumGross + selectedOrder.getGross();
			sumNet = sumNet + selectedOrder.getNetAmount();

		}
		lblSumGross.setText(NumberFormat.getFormat(".00").format(sumGross));
		lblSumNet.setText(NumberFormat.getFormat(".00").format(sumNet));

	}

	private DoubleSpinnerField createQuantitySpinner() {
		DoubleSpinnerField spinnerQuantity = new DoubleSpinnerField();
		spinnerQuantity.setIncrement(1d);
		spinnerQuantity.setMinValue(0);
		spinnerQuantity.setMaxValue(250d);
		spinnerQuantity.setAllowNegative(false);
		spinnerQuantity.setEmptyText("0.0");
		return spinnerQuantity;
	}

	private void setRowStyle(FlexTable flex, HTMLTable.CellFormatter formatter, int i) {
		flex.getRowFormatter().addStyleName(i+1, "line-separator");
		formatter.setHorizontalAlignment(i+1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(i+1, 2, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(i+1, 3, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.setHorizontalAlignment(i+1, 4, HasHorizontalAlignment.ALIGN_CENTER);
		formatter.addStyleName(i+1, 4, "spacing");
		formatter.addStyleName(i+1, 3, "spacing");
		formatter.setVerticalAlignment(i+1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	}

	public TextButton getWeiter() {
		return weiter;
	}

}
