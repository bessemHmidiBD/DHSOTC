package com.adoa.azportal.application.interfaces;

import java.util.List;

import com.adoa.azportal.domain.LicenseType;
import com.adoa.azportal.domain.Orders;
import com.adoa.azportal.domain.ProductCatalog;




public interface RenewalManager {

	public abstract String checkForm();
	public abstract void beginCheckout();
	public abstract String logout();
	public abstract void remove();


	public abstract long getFacilityId();
	public abstract void setFacilityId(long facilityId);

	public abstract LicenseType getLicenseType();
	public abstract void setLicenseType(LicenseType licenseType);
	
	public abstract List<LicenseType> getLicenseTypeList();
	
	
	public abstract List<ProductCatalog> getFeesList();
	public abstract void setFeesList(List<ProductCatalog> feesList);
	
	public abstract Double getTotalFees();
	public abstract void setTotalFees(Double totalFees);
	
	public abstract Orders getOrders();
	public abstract void setOrders(Orders orders);
	
	public Number getCopyFees();
	public void setCopyFees(Number copyFees);
	
	public Number getAmountpenalty();	
	public void setAmountpenalty(Number amountpenalty);
	void setUserId(String userId);
	
	
}