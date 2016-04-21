package com.adoa.azportal.application.impl;

import javax.ejb.Local;

import com.adoa.azportal.domain.Orders;

@Local
public interface CheckoutEndManager {
	
	public void remove();
	
	public void receiveCheckout();
	
	public String generateReceipt();
	
	public Orders getOrders();
	public void setOrders(Orders orders);
	
}
