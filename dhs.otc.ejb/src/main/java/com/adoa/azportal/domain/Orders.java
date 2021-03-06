package com.adoa.azportal.domain;

// Generated Dec 24, 2009 6:29:00 PM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Type;
import org.jboss.logging.Logger;


//import com.nicusa.az.dhs.otc.validator.CharValidator;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "ORDERS")
public class Orders implements java.io.Serializable {

	private static Logger logger = Logger.getLogger(Orders.class.getName());
	
	private static final long serialVersionUID = 1L;

	private long id;
	private String orderStatus;
	private Date paymentDate;
	private String userId;
	private String facilityId;
	private Integer numberBedsKids;
	private Double miscellaneousFee;
	private Double penaltyAmount;
	private Double calculatedAmount;
	private Double actualAmountPaid;
	private Long tpeOrderNumber;
	private String creditCardType;
	private String partialCC;
	private Boolean flagUseEmpowerPack;
	private Boolean flagLateFee;
	private String checkoutKey;
	private String invoiceNumber;
	private LicenseType licenseType;
	private List<LineItem> lineItems = new ArrayList<LineItem>();

	public Orders() {
	}

	public Orders(long id, String orderStatus, Date paymentDate, String userId,
			String facilityId, Integer numberBedsKids, Double miscellaneousFee, Double penaltyAmount,
			Double calculatedAmount, Double actualAmountPaid,
			LicenseType licenseType,String invoiceNumber) {
		this.id = id;
		this.orderStatus = orderStatus;
		this.paymentDate = paymentDate;
		this.userId = userId;
		this.facilityId = facilityId;
		this.numberBedsKids = numberBedsKids;
		this.miscellaneousFee = miscellaneousFee;
		this.penaltyAmount = penaltyAmount;
		this.calculatedAmount = calculatedAmount;
		this.actualAmountPaid = actualAmountPaid;
		this.licenseType = licenseType;
		this.invoiceNumber = invoiceNumber;
	}

	public Orders(long id, String orderStatus, Date paymentDate, String userId,
			String facilityId, Integer numberBedsKids, Double miscellaneousFee, Double penaltyAmount,
			Double calculatedAmount, Double actualAmountPaid,
			Long tpeOrderNumber, String creditCardType, String partialCC,
			Boolean flagUseEmpowerPack, Boolean flagLateFee,
			LicenseType licenseType, List<LineItem> lineItems, String invoiceNumber) {
		this.id = id;
		this.orderStatus = orderStatus;
		this.paymentDate = paymentDate;
		this.userId = userId;
		this.facilityId = facilityId;
		this.numberBedsKids = numberBedsKids;
		this.miscellaneousFee = miscellaneousFee;
		this.penaltyAmount = penaltyAmount;
		this.calculatedAmount = calculatedAmount;
		this.actualAmountPaid = actualAmountPaid;
		this.tpeOrderNumber = tpeOrderNumber;
		this.creditCardType = creditCardType;
		this.partialCC = partialCC;
		this.flagUseEmpowerPack = flagUseEmpowerPack;
		this.flagLateFee = flagLateFee;
		this.licenseType = licenseType;
		this.lineItems = lineItems;
		this.invoiceNumber = invoiceNumber;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "ORDER_STATUS")
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PAYMENT_DATE", nullable = false, length = 10)
	@NotNull
	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "USER_ID", nullable = false, length = 30)
	@NotNull
	@Size(max = 30)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		if(userId.length() > 29)
		{
			logger.error("User ID is being truncated.");
			userId = userId.substring(0, 30);
		}
		this.userId = userId;
	}

	@Column(name = "FACILITY_ID", nullable = false)
	
	//@CharValidator(message = "You must enter a valid Facility Number. You may enter letters, numbers and a dash between numbers and letters if needed.")
	//TODO update to regex of bean validation
	
	@Size(max = 16)
	public String getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	@Column(name = "NUMBER_BEDS_KIDS", nullable = false)
	// @NumberValidator(message="bob.")

	
	@Min(value=0,message = "You must enter the number of beds in the facility. Valid values are 0 � 10000.")
	@Max(value=10000,message = "You must enter the number of beds in the facility. Valid values are 0 � 10000.")
	public Integer getNumberBedsKids() {
		return this.numberBedsKids;
	}

	public void setNumberBedsKids(Integer numberBedsKids) {
		this.numberBedsKids = numberBedsKids;
	}

	@Column(name = "MISCELLANEOUS_FEE", nullable = false, precision = 53, scale = 0)
	// @NumberValidator(message="Copy Fee field is required.  Only enter numbers or a decimal. If no fees apply, enter 0.00.")
	public Double getMiscellaneousFee() {
		return this.miscellaneousFee;
	}

	public void setMiscellaneousFee(Double miscellaneousFee) {
		this.miscellaneousFee = miscellaneousFee;
	}
	
	

	/**
	 * @return the penaltyAmount
	 */
	@Column(name = "PENALTY_AMOUNT", nullable = false, precision = 53, scale = 0)
	public Double getPenaltyAmount() {
		return penaltyAmount;
	}

	/**
	 * @param penaltyAmount the penaltyAmount to set
	 */
	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	@Column(name = "CALCULATED_AMOUNT", nullable = false, precision = 53, scale = 0)
	public Double getCalculatedAmount() {
		return this.calculatedAmount;
	}

	public void setCalculatedAmount(Double calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

	@Column(name = "ACTUAL_AMOUNT_PAID", nullable = false, precision = 53, scale = 0)
	public Double getActualAmountPaid() {
		return this.actualAmountPaid;
	}

	public void setActualAmountPaid(Double actualAmountPaid) {
		this.actualAmountPaid = actualAmountPaid;
	}

	@Column(name = "TPE_ORDER_NUMBER")
	public Long getTpeOrderNumber() {
		return this.tpeOrderNumber;
	}

	public void setTpeOrderNumber(Long tpeOrderNumber) {
		this.tpeOrderNumber = tpeOrderNumber;
	}

	@Column(name = "CREDIT_CARD_TYPE", length = 15)
	@Size(max = 15)
	public String getCreditCardType() {
		return this.creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	@Column(name = "PARTIAL_CC", length = 15)
	@Size(max = 15)
	public String getPartialCC() {
		return this.partialCC;
	}

	public void setPartialCC(String partialCC) {
		this.partialCC = partialCC;
	}

	@Column(name = "FLAG_USE_EMPOWER_PACK")
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getFlagUseEmpowerPack() {
		return this.flagUseEmpowerPack;
	}

	public void setFlagUseEmpowerPack(Boolean flagUseEmpowerPack) {
		this.flagUseEmpowerPack = flagUseEmpowerPack;
	}

	@Column(name = "FLAG_LATE_FEE")
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getFlagLateFee() {
		return this.flagLateFee;
	}

	public void setFlagLateFee(Boolean flagLateFee) {
		this.flagLateFee = flagLateFee;
	}

	@Column(name = "CHECKOUT_KEY", length = 20)
	@Size(max = 20)
	public String getCheckoutKey() {
		return checkoutKey;
	}

	public void setCheckoutKey(String checkoutKey) {
		this.checkoutKey = checkoutKey;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LICENSE_REF_ID", nullable = false)
	@NotNull
	public LicenseType getLicenseType() {
		return this.licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orders", cascade = CascadeType.ALL)
	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	// @JoinColumn(name="ID")
	public List<LineItem> getLineItems() {
		return this.lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	@Column(name = "INVOICE_NUMBER")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String toString(Orders record) {

		// Declare & initialize.
		StringBuffer retval = new StringBuffer();

		retval.append("\n" + "Id:                    " + record.getId() + "\n");
		retval.append("OrderStatus:           " + record.getOrderStatus()
				+ "\n");
		retval.append("PaymentDate:           " + record.getPaymentDate()
				+ "\n");
		retval.append("UserId:                " + record.getUserId() + "\n");
		retval.append("FacilityId:            " + record.getFacilityId() + "\n");
		retval.append("NumberBeds:            " + record.getNumberBedsKids()
				+ "\n");
		retval.append("MiscellaneousFee:      " + record.getMiscellaneousFee()
				+ "\n");
		retval.append("CalculatedAmount:      " + record.getCalculatedAmount()
				+ "\n");
		retval.append("ActualAmountPaid:      " + record.getActualAmountPaid()
				+ "\n");
		retval.append("TPEOrderNumber:        " + record.getTpeOrderNumber()
				+ "\n");
		retval.append("CreditCardType:        " + record.getCreditCardType()
				+ "\n");
		retval.append("PartialCC:             " + record.getPartialCC() + "\n");
		retval.append("FlagUseEmpower:        "
				+ record.getFlagUseEmpowerPack() + "\n");
		retval.append("FlagLateFee:           " + record.getFlagLateFee()
				+ "\n");
		retval.append("CheckoutKey:           " + record.getCheckoutKey()
				+ "\n");
		retval.append("LicenseType:           "
				+ record.getLicenseType().getDescription() + "\n");
		retval.append("INvoice Number:           "
				+ record.getInvoiceNumber() + "\n");

		return retval.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
