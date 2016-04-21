package com.adoa.azportal.model;

import com.adoa.azportal.domain.LicenseType;

public class RenewalInputs {

	/*
	 * { "empowerPack": false, "lateFee": false, "licenseType": { "id": 9,
	 * "description": "Enforcement civil Penaltie", "shortDesc": "DLSP" } ,
	 * "numberOfBeds": 0, "facilityId": 21, "copyFee": 5, "amount": 5 }
	 */
	
	private Boolean empowerPack;
	private Boolean lateFee;
	private String facilityId;
	private String userId;
	private Integer numberOfBeds;
	private Double copyFees;
	private Double amountPenality;
	private LicenseType licenseType;
	private Integer invoiceNumber;
	
	
	@Override
	public String toString() {
		return "RenewalInputs [empowerPack=" + empowerPack + ", lateFee=" + lateFee + ", facilityId=" + facilityId
				+ ", userId=" + userId + ", numberOfBeds=" + numberOfBeds + ", copyFees=" + copyFees
				+ ", amountPenality=" + amountPenality + ", licenseType=" + licenseType + "]";
	}



	public Boolean isEmpowerPack() {
		return empowerPack;
	}



	public void setEmpowerPack(Boolean empowerPack) {
		this.empowerPack = empowerPack;
	}



	public Boolean isLateFee() {
		return lateFee;
	}



	public void setLateFee(Boolean lateFee) {
		this.lateFee = lateFee;
	}



	



	



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}



	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}



	public Double getCopyFees() {
		return copyFees;
	}



	public void setCopyFees(Double copyFees) {
		this.copyFees = copyFees;
	}



	public Double getAmountPenality() {
		return amountPenality;
	}



	public void setAmountPenality(Double amountPenality) {
		this.amountPenality = amountPenality;
	}



	public LicenseType getLicenseType() {
		return licenseType;
	}



	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}



	public String getFacilityId() {
		return facilityId;
	}



	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}



	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}



	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	

	
	
}
