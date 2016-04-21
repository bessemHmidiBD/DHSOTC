package com.adoa.azportal.model;

import java.util.List;

import com.adoa.azportal.domain.LicenseType;

public class FeeSummaryOutput {
	
	private LicenseType licenseType;
	private double totalFees;
	private String invoiceNumber;
	private List<LineItemModel> lineItems;
	public LicenseType getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}
	public double getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(double totalFees) {
		this.totalFees = totalFees;
	}

	public List<LineItemModel> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItemModel> lineItemModels) {
		this.lineItems = lineItemModels;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	

}
